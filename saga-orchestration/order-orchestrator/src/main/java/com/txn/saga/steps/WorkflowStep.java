package com.txn.saga.steps;

import com.txn.saga.constant.WorkflowStepStatus;

import reactor.core.publisher.Mono;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();
    Mono<Boolean> process();
    Mono<Boolean> revert();

}
