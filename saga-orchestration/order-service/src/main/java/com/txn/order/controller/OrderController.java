package com.txn.order.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txn.dto.OrderRequestDTO;
import com.txn.dto.OrderResponseDTO;
import com.txn.order.entity.PurchaseOrder;
import com.txn.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping("/create")
	public PurchaseOrder createOrder(@RequestBody OrderRequestDTO requestDTO) {
		requestDTO.setOrderId(UUID.randomUUID());
		return this.service.createOrder(requestDTO);
	}

	@GetMapping("/all")
	public List<OrderResponseDTO> getOrders() {
		return this.service.getAll();
	}

}
