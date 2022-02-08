package com.txn.inventory.config;

import com.txn.events.inventory.InventoryEvent;
import com.txn.events.order.OrderEvent;
import com.txn.events.order.OrderStatus;
import com.txn.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class InventoryConfig {

    @Autowired
    private InventoryService service;

    /**
     * 接收OrderEvent，處理完畢在發送InventoryEvent
     * 
     * */
    @Bean
    public Function<Flux<OrderEvent>, Flux<InventoryEvent>> inventoryProcessor() {
        return flux -> flux.flatMap(this::processInventory);
    }

    private Mono<InventoryEvent> processInventory(OrderEvent event){
        if(OrderStatus.ORDER_CREATED == event.getOrderStatus()){
            return Mono.fromSupplier(() -> this.service.newOrderInventory(event));
        }
        return Mono.fromRunnable(() -> this.service.cancelOrderInventory(event));
    }

}

