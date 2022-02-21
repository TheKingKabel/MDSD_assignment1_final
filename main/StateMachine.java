package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.metamodel.Machine;
import main.metamodel.Operation;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.Condition;

public class StateMachine {
	protected class MachineBuilder {
		private Map<String,State> states = new HashMap<>();
		private Map<String,Integer> variables = new HashMap<>();
		private State current;
		private State initial;
		private String currentEvent;
		
		private State getState(String name) {
			if(!states.containsKey(name)) states.put(name, new State(name));
			return states.get(name);
		}
		public MachineBuilder state(String name) { 
			current = getState(name);
			return this;
		}
		public MachineBuilder initial() { 
			initial = current;
			return this;
		}
		public MachineBuilder when(String event) { 
			currentEvent = event;
			return this; 
		}
		public MachineBuilder target(String state, Runnable effect) { 
			Transition t = new Transition(currentEvent,getState(state),effect);
			current.addTransition(t);
			return this;
		}
		public Machine build() { 
			return new Machine(states.values(),initial,variables); 
		} 
		
		public MachineBuilder set(Operation.types type, String variable, Integer i) {
			current.getTransitions().get(current.getTransitions().size()-1).setOperation(new Operation(type, variable, i));
			return this;
		}
		
		public MachineBuilder cond(Condition.types type, String variable, Integer i) {
			current.getTransitions().get(current.getTransitions().size()-1).setCondition(new Condition(type, variable, i));
			return this;
		}
		
		
		public Map<String,Integer> getVariables() {
			return this.variables;
		}
	}
	
	private MachineBuilder machinebuilder;
	
	public StateMachine() {
		this.machinebuilder = new MachineBuilder();
	}
	
	
	public Machine build() {
		return machinebuilder.build();
	}

	public StateMachine state(String name) {
		this.machinebuilder.state(name);
		return this;
	}

	public StateMachine initial() {
		this.machinebuilder.initial();
		return this;
	}

	public StateMachine when(String string) {
		this.machinebuilder.when(string);
		return this;
	}

	public StateMachine to(String string) {
		this.machinebuilder.target(string, () -> {});
		return this;
	}

	public StateMachine integer(String string) {
		this.machinebuilder.getVariables().put(string, 0);
		return this;
	}

	public StateMachine set(String varName, int i) {
		this.machinebuilder.set(Operation.types.SET, varName, i);
		return this;
	}

	public StateMachine increment(String string) {
		this.machinebuilder.set(Operation.types.INCREMENT, string, 1);
		return this;
	}

	public StateMachine decrement(String string) {
		this.machinebuilder.set(Operation.types.DECREMENT, string, 1);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		this.machinebuilder.cond(Condition.types.EQUAL, string, i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		this.machinebuilder.cond(Condition.types.GREATERTHAN, string, i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		this.machinebuilder.cond(Condition.types.LESSTHAN, string, i);
		return this;
	}

}
