package com.txn.saga.flows;

import java.util.List;

import com.txn.saga.steps.WorkflowStep;

public class OrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;

    public OrderWorkflow(List<WorkflowStep> steps) {
        this.steps = steps;
    }

    @Override
    public List<WorkflowStep> getSteps() {
        return this.steps;
    }

}
