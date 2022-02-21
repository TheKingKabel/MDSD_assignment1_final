package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	private String name;
	private List<Transition> trans = new ArrayList<>();
	public State(String name) {
		super();
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return trans;
	}
	
	public void addTransition(Transition t) {
		this.trans.add(t);
	}

	public Transition getTransitionByEvent(String string) {
		return trans.stream().filter(s -> s.getEvent().equals(string)).findAny().orElse(null);
	}

}
