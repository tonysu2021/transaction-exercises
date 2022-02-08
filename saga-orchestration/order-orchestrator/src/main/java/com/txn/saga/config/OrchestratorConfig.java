package com.txn.saga.config;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.txn.dto.OrchestratorRequestDTO;
import com.txn.dto.OrchestratorResponseDTO;
import com.txn.saga.service.OrchestratorService;

import reactor.core.publisher.Flux;

@Configuration
public class OrchestratorConfig {

	private static Logger logger = LoggerFactory.getLogger(OrchestratorConfig.class);

	@Bean
	public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processor(
			OrchestratorService orchestratorService) {
		return flux -> flux.flatMap(orchestratorService::orderProduct)
				.doOnNext(dto -> logger.info("Status : {}", dto.getStatus()));
	}

}
