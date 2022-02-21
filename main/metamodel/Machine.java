package main.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Machine {
	private List<State> states = new ArrayList<>();
	private Map<String,Integer> variables = new HashMap<>();
	private State initialState;
	public Machine(Collection<State> states, State initialState, Map<String,Integer> variables) {
		super();
		this.states.addAll(states);
		this.initialState = initialState;
		this.variables = variables;
	}
	
	public Integer getVarVal(String variable) {
		return variables.get(variable);
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		return states.stream().filter(s -> s.getName().equals(string)).findAny().orElse(null);
	}

	public int numberOfIntegers() {
		return variables.size();
	}

	public boolean hasInteger(String string) {
		return variables.containsKey(string);
	}
	
	public void executeOperation(Operation operation) {
		if(variables.containsKey(operation.getTarget())) {
			if(operation.getOperationtype().equals(Operation.types.SET)) {
				variables.put(operation.getTarget(), operation.getValue());
			}
			if(operation.getOperationtype().equals(Operation.types.INCREMENT)) {
				variables.put(operation.getTarget(), variables.get(operation.getTarget()) + 1);
			}
			if(operation.getOperationtype().equals(Operation.types.DECREMENT)) {
				variables.put(operation.getTarget(), variables.get(operation.getTarget()) - 1);
			}
		}
	}
	
	public boolean evaluateConditions(Condition condition) {
		if(variables.containsKey(condition.getTarget())) {
			if(condition.getConditionType().equals(Condition.types.EQUAL)) {
				return condition.getValue().equals(variables.get(condition.getTarget()));
			}
			if(condition.getConditionType().equals(Condition.types.GREATERTHAN)) {
				return condition.getValue() < (variables.get(condition.getTarget()));
			}
			if(condition.getConditionType().equals(Condition.types.LESSTHAN)) {
				return condition.getValue() > (variables.get(condition.getTarget()));
			}	
		}
		return false;
	}

}
