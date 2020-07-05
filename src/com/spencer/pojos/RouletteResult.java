package com.spencer.pojos;

import java.util.HashMap;

public class RouletteResult {

	int rouletteNumber;
	private HashMap<Player, PlayerResult> results;

	public RouletteResult(int rouletteNumber, HashMap<Player, PlayerResult> results) {
		super();
		this.rouletteNumber = rouletteNumber;
		this.results = results;
	}

	public int getRouletteNumber() {
		return rouletteNumber;
	}

	public void setRouletteNumber(int rouletteNumber) {
		this.rouletteNumber = rouletteNumber;
	}

	public HashMap<Player, PlayerResult> getResults() {
		return results;
	}

	public void setResults(HashMap<Player, PlayerResult> results) {
		this.results = results;
	}

}
