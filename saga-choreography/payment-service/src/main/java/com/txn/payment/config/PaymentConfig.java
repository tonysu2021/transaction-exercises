package com.txn.payment.config;

import com.txn.events.order.OrderEvent;
import com.txn.events.order.OrderStatus;
import com.txn.events.payment.PaymentEvent;
import com.txn.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConfig {

    @Autowired
    private PaymentService service;

    /**
     * 接收OrderEvent，處理完畢在發送PaymentEvent
     * 
     * */
    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return flux -> flux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent event){
        if(OrderStatus.ORDER_CREATED == event.getOrderStatus()){
            return Mono.fromSupplier(() -> this.service.newOrderEvent(event));
        }else{
            return Mono.fromRunnable(() -> this.service.cancelOrderEvent(event));
        }
    }

}
