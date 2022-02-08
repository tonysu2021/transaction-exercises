package com.txn.saga.exception;

public class WorkflowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WorkflowException(String message) {
        super(message);
    }

}
