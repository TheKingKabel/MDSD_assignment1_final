package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {

	private Machine machine;
	private State currentState;

	public MachineInterpreter(Machine machine) {
		this.machine = machine;
		currentState = machine.getInitialState();
	}

	public MachineInterpreter() {

	}

	public void reset() {
		currentState = machine.getInitialState();
	}

	public void run(Machine m) {
		this.machine = m;
		currentState = m.getInitialState();
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public void processEvent(String string) {
		for (Transition t : currentState.getTransitions()) {
			if (t.getEvent().equals(string)) {
				if (t.getCondition() != null) {
					if(machine.evaluateConditions(t.getCondition())) {
						t.effect();
						if (t.getOperation() != null) {
							machine.executeOperation(t.getOperation());
						}
						currentState = t.getTarget();
						return;
					}
				}
				else if(t.getCondition()==null){
					t.effect();
					if (t.getOperation() != null) {
						machine.executeOperation(t.getOperation());
					}
					currentState = t.getTarget();
					return;
				}
			}
		}
		System.err.println("Unhandled event " + string);
	}

	public int getInteger(String string) {
		return machine.getVarVal(string);
	}

}
