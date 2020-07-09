package valentina.roulettetestapi.domain;

import java.io.Serializable;

public class Roulette implements Serializable {
	public final static int OPEN = 1;
	public final static int CLOSED = 0;
	public final static int CREATED = -1;

	private static final long serialVersionUID = 1L;
	private int state = CREATED;
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
