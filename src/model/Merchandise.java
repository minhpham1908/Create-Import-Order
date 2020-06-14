package model;

public class Merchandise {
	String code;
	String name;
	String unit;

	public Merchandise() {
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
