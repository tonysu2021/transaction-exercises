package com.txn.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txn.dto.InventoryRequestDTO;
import com.txn.dto.InventoryResponseDTO;
import com.txn.inventory.service.InventoryService;

@RestController
@RequestMapping("inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	/**
	 * 扣除商品
	 * 
	 */
	@PostMapping("/deduct")
	public InventoryResponseDTO deduct(@RequestBody final InventoryRequestDTO requestDTO) {
		return this.service.deductInventory(requestDTO);
	}

	/**
	 * 增加商品
	 * 
	 */
	@PostMapping("/add")
	public void add(@RequestBody final InventoryRequestDTO requestDTO) {
		this.service.addInventory(requestDTO);
	}

}
