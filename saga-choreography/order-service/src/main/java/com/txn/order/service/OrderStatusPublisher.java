package com.txn.order.service;

import com.txn.dto.PurchaseOrderDto;
import com.txn.events.order.OrderEvent;
import com.txn.events.order.OrderStatus;
import com.txn.order.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSink;

    /**
     * 提出訂單事件
     * 
     * @param purchaseOrder 
     * @param orderStatus 目前訂單狀態
     * 
     * */
    public void raiseOrderEvent(final PurchaseOrder purchaseOrder, OrderStatus orderStatus){
        var dto = PurchaseOrderDto.of(
                purchaseOrder.getId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getPrice(),
                purchaseOrder.getUserId()
        );
        var orderEvent = new OrderEvent(dto, orderStatus);
        this.orderSink.tryEmitNext(orderEvent);
    }

}
