package com.txn.order.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.txn.dto.OrchestratorRequestDTO;
import com.txn.dto.OrchestratorResponseDTO;
import com.txn.order.service.OrderEventUpdateService;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

@Configuration
public class OrderEventHandler {

	/**
	* 定義Stream的生產者。
	* 
	* */
	@Bean
	public Supplier<Flux<OrchestratorRequestDTO>> supplier(DirectProcessor<OrchestratorRequestDTO> publisher) {
		return () -> Flux.from(publisher);
	}

	/**
	* 定義Stream的消費者。
	* 
	* */
	@Bean
	public Consumer<Flux<OrchestratorResponseDTO>> consumer(OrderEventUpdateService service) {
		return (Flux<OrchestratorResponseDTO> flux) -> flux.subscribe(service::updateOrder);
	}

}
