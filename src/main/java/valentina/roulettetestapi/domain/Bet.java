package valentina.roulettetestapi.domain;

import java.io.Serializable;

public class Bet implements Serializable {

	private static final long serialVersionUID = 1L;
	private String color;
	private double amount;
	private Integer number;
	private String idUser;
	private String idRoulette;
	private boolean winner;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdRoulette() {
		return idRoulette;
	}

	public void setIdRoulette(String idRoulette) {
		this.idRoulette = idRoulette;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
