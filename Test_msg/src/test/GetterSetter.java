package test;

public class GetterSetter {
	String color, cheertext;

	public GetterSetter(String color, String cheertext) {
		super();
		this.color = color;
		this.cheertext = cheertext;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCheertext() {
		return cheertext;
	}

	public void setCheertext(String cheertext) {
		this.cheertext = cheertext;
	}
	
}
