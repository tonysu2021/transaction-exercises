package com.txn.saga.steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.txn.dto.InventoryRequestDTO;
import com.txn.dto.InventoryResponseDTO;
import com.txn.enums.InventoryStatus;
import com.txn.saga.constant.WorkflowStepStatus;

import reactor.core.publisher.Mono;

public class InventoryStep implements WorkflowStep {

	private final WebClient webClient;
	private final InventoryRequestDTO requestDTO;
	private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

	public InventoryStep(WebClient webClient, InventoryRequestDTO requestDTO) {
		this.webClient = webClient;
		this.requestDTO = requestDTO;
	}

	@Override
	public WorkflowStepStatus getStatus() {
		return this.stepStatus;
	}

	/**
	 * 扣除商品
	 * 
	 */
	@Override
	public Mono<Boolean> process() {
		return this.webClient
				.post()
				.uri("/inventory/deduct")
				.body(BodyInserters.fromValue(this.requestDTO))
				.retrieve()
				.bodyToMono(InventoryResponseDTO.class)
				.map(r -> r.getStatus().equals(InventoryStatus.AVAILABLE))
				.doOnNext((Boolean b) -> this.stepStatus = Boolean.TRUE.equals(b) ? WorkflowStepStatus.COMPLETE
						: WorkflowStepStatus.FAILED);
	}

	/**
	 * 增加商品
	 * 
	 */
	@Override
	public Mono<Boolean> revert() {
		return this.webClient
				.post()
				.uri("/inventory/add")
				.body(BodyInserters.fromValue(this.requestDTO))
				.retrieve()
				.bodyToMono(Void.class)
				.map(r -> true)
				.onErrorReturn(false);
	}
}
