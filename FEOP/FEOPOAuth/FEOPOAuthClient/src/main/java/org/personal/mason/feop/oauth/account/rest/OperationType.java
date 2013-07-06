package org.personal.mason.feop.oauth.account.rest;

public enum OperationType {

	InvalidOperation(-1), NotFoundTarget(0), CorrectFinish(1);

	private int operationResult;

	private OperationType(int operationResult) {
		this.operationResult = operationResult;
	}

	public int getOperationResult() {
		return operationResult;
	}
}
