package com.spencer.pojos;

public class PlayerResult {
	private String result;
	private double winnings;
	

	public PlayerResult(String result, double winnings) {
		super();
		this.result = result;
		this.winnings = winnings;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public double getWinnings() {
		return winnings;
	}

	public void setWinnings(double winnings) {
		this.winnings = winnings;
	}
}
