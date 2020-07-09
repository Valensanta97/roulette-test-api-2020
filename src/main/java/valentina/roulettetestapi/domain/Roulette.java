package valentina.roulettetestapi.domain;

import java.io.Serializable;

public class Roulette implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int state = 1;
	private String name;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
