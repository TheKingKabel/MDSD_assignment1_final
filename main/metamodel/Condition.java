package main.metamodel;

public class Condition {

	public types ConditionType;
	public String target;
	public Integer value;
	
	public Condition(types type, String target, Integer value) {
		this.ConditionType = type;
		this.target = target;
		this.value = value;
	}
	
	public types getConditionType() {
		return ConditionType;
	}

	public void setConditionType(types conditionType) {
		ConditionType = conditionType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public enum types { EQUAL, GREATERTHAN, LESSTHAN };
}
