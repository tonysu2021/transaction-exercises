package com.txn.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txn.dto.PaymentRequestDTO;
import com.txn.dto.PaymentResponseDTO;
import com.txn.payment.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	private PaymentService service;

    /** 
    * 扣款
    * 
    */
	@PostMapping("/debit")
	public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO) {
		return this.service.debit(requestDTO);
	}

    /** 
    * 歸還
    * 
    */
	@PostMapping("/credit")
	public void credit(@RequestBody PaymentRequestDTO requestDTO) {
		this.service.credit(requestDTO);
	}

}
