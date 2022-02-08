package com.txn.order.service;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txn.events.inventory.InventoryStatus;
import com.txn.events.order.OrderStatus;
import com.txn.events.payment.PaymentStatus;
import com.txn.order.entity.PurchaseOrder;
import com.txn.order.repository.PurchaseOrderRepository;

@Service
public class OrderStatusUpdateService {

	private static Logger logger = LoggerFactory.getLogger(OrderStatusUpdateService.class);

	@Autowired
	private PurchaseOrderRepository repository;

	@Autowired
	private OrderStatusPublisher publisher;

	/**
	 * 更新訂單狀態。
	 * 
	 * @param id 訂單編號
	 * @param consumer 更新庫存或支付狀態的 method。
	 * 
	 * */
	@Transactional
	public void updateOrder(final UUID id, Consumer<PurchaseOrder> consumer) {
		this.repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));

	}

	private void updateOrder(PurchaseOrder purchaseOrder) {
		if (Objects.isNull(purchaseOrder.getInventoryStatus()) || Objects.isNull(purchaseOrder.getPaymentStatus())) {
			logger.info("1. order : {}", purchaseOrder);
			return;
		}
		logger.info("2. order : {}", purchaseOrder);
		var isComplete = PaymentStatus.RESERVED == purchaseOrder.getPaymentStatus()
				&& InventoryStatus.RESERVED == purchaseOrder.getInventoryStatus();
		var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
		purchaseOrder.setOrderStatus(orderStatus);
		if (!isComplete) {
			logger.info("3. order status is cancelled ");
			this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
		}
	}

}
