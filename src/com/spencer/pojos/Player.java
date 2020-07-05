package com.spencer.pojos;

public class Player {
	private String name;
	private String choice;
	private double bet;

	public Player(String name, String choice, double bet) {
		this.name = name;
		this.choice = choice;
		this.bet = bet;
	}

	public Player() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", choice=" + choice + ", bet=" + bet + "]";
	}
	
}
