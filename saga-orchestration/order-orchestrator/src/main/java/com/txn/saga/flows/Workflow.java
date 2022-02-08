package com.txn.saga.flows;

import java.util.List;

import com.txn.saga.steps.WorkflowStep;

public interface Workflow {

    List<WorkflowStep> getSteps();

}
