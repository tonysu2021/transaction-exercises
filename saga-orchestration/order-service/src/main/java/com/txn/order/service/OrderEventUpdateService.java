package com.txn.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.txn.dto.OrchestratorResponseDTO;
import com.txn.order.repository.PurchaseOrderRepository;

@Service
public class OrderEventUpdateService {

	@Autowired
	private PurchaseOrderRepository repository;

	@Transactional(rollbackFor = Exception.class)
	public void updateOrder(final OrchestratorResponseDTO responseDTO) {
		this.repository.findById(responseDTO.getOrderId()).ifPresent(po -> {
			po.setStatus(responseDTO.getStatus());
			this.repository.save(po);
		});
	}

}
