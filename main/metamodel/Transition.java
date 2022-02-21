package main.metamodel;

import java.util.ArrayList;

public class Transition {
	private String event;
	private State target;
	private Runnable effect;
	private Operation operation = null;
	private Condition condition = null;
	public Transition(String event, State target, Runnable effect) {
		super();
		this.event = event;
		this.target = target;
		this.effect = effect;
	}
	
	public Condition getCondition() {
		return this.condition;
	}
	
	public Operation getOperation() {
		return this.operation;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getEvent() {
		return event;
	}

	public State getTarget() {
		return target;
	}

	public boolean hasSetOperation() {
		return this.operation!=null && this.operation.getOperationtype().equals(Operation.types.SET);
	}
	
	public void effect() {
		this.effect.run();
	}

	public boolean hasIncrementOperation() {
		return this.operation!=null && this.operation.getOperationtype().equals(Operation.types.INCREMENT);
	}

	public boolean hasDecrementOperation() {
		return this.operation!=null && this.operation.getOperationtype().equals(Operation.types.DECREMENT);
	}

	public String getOperationVariableName() {
		return this.operation.getTarget();
	}

	public boolean isConditional() {
		return this.condition!=null;
	}

	public String getConditionVariableName() {
		return this.condition.getTarget();
	}

	public Integer getConditionComparedValue() {
		return this.condition.getValue();
	}

	public boolean isConditionEqual() {
		return this.condition.getConditionType().equals(Condition.types.EQUAL);
	}

	public boolean isConditionGreaterThan() {
		return this.condition.getConditionType().equals(Condition.types.GREATERTHAN);
	}

	public boolean isConditionLessThan() {
		return this.condition.getConditionType().equals(Condition.types.LESSTHAN);
	}

	public boolean hasOperation() {
		return this.operation != null;
	}

}
