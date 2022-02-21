package main.metamodel;

public class Operation {

	private types Operationtype;
	private String target;
	private Integer value;
	
	public Operation(types type, String target, Integer value) {
		this.Operationtype = type;
		this.target = target;
		this.value = value;
	}
	
	public types getOperationtype() {
		return Operationtype;
	}
	public void setOperationtype(types operationtype) {
		Operationtype = operationtype;
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
	
	public enum types { SET, INCREMENT, DECREMENT };
	
}