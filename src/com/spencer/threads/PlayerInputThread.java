package com.spencer.threads;
import java.util.Scanner;
import java.util.Vector;
import com.spencer.pojos.Player;

public class PlayerInputThread extends Thread implements Runnable {
	private Vector<Player> playerList;
	private Scanner sc;

	public PlayerInputThread(Vector<Player> playerList, Scanner sc) {
		this.playerList = playerList;
		this.sc = sc;
	}

	public void run() {
		while (true) {
			this.getPlayerBets(playerList);
		}
	}

	public void getPlayerBets(Vector<Player> playerList) {
		for (Player player : playerList) {
			System.out.println(player.getName() + " Would you like to add to your bet? If not type 0.00");
			if (sc.hasNext()) {
				String bet = sc.nextLine();
				while (!isValidBet(bet)) {
					System.out.println(player.getName() + " Would you like to add to your bet? If not type 0.00");
					bet = sc.nextLine();
				}
				player.setBet(player.getBet() + Double.parseDouble(bet));
			}
		}
	}

	public boolean isValidBet(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
