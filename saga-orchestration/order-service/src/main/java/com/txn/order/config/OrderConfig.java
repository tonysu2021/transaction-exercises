package com.txn.order.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.txn.dto.OrchestratorRequestDTO;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;

@Configuration
@AutoConfigureAfter(OrderEventHandler.class)
public class OrderConfig {

	/**
	 * 作為發佈者。
	 * 
	 * */
	@Bean
	public DirectProcessor<OrchestratorRequestDTO> publisher() {
		return DirectProcessor.create();
	}

	@Bean
	@ConditionalOnBean(DirectProcessor.class)
	public FluxSink<OrchestratorRequestDTO> sink(DirectProcessor<OrchestratorRequestDTO> publisher) {
		return publisher.sink();
	}

}
