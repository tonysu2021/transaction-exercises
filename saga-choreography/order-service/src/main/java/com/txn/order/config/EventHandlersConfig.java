package com.txn.order.config;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.txn.events.inventory.InventoryEvent;
import com.txn.events.payment.PaymentEvent;
import com.txn.order.service.OrderStatusUpdateService;

/**
 * 定義 MQ Stream 的消費者。
 * 
 * */
@Configuration
public class EventHandlersConfig {
	
	private static Logger logger = LoggerFactory.getLogger(EventHandlersConfig.class);

    @Autowired
    private OrderStatusUpdateService orderStatusUpdateService;

    /**
     * 接受支付事件，確認是否成功。
     * 
     * */
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return (PaymentEvent event) -> {
        	logger.info("PaymentEvent occurred. order id : {}", event.getPayment().getOrderId());
        	orderStatusUpdateService.updateOrder(event.getPayment().getOrderId(), po -> 
                po.setPaymentStatus(event.getPaymentStatus())
            );
        };
    }

    /**
     * 接受庫存事件，確認是否成功。
     * 
     * */
    @Bean
    public Consumer<InventoryEvent> inventoryEventConsumer(){
        return (InventoryEvent event) -> {
        	logger.info("InventoryEvent occurred. order id : {}", event.getInventory().getOrderId());
        	orderStatusUpdateService.updateOrder(event.getInventory().getOrderId(), po -> 
                po.setInventoryStatus(event.getStatus())
            );
        };
    }

}
