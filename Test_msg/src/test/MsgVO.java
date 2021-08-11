package test;

public class MsgVO {
	String color, cheertext;
	
	/*
	public MsgVO(String color, String cheertext) {
		super();
		this.color = color;
		this.cheertext = cheertext;
	}*/  // 이 부분이 필요 없음.
	
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
