package com.spencer.roulette;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import com.spencer.pojos.*;
import com.spencer.services.RouletteServices;
import com.spencer.threads.PlayerInputThread;
import com.spencer.threads.RouletteTimerThread;

public class RouletteGame {
	PlayerInputThread inputThread;
	public void playGame()  {
		
			RouletteServices rouletteService = new RouletteServices();
			Vector<Player> playerList = rouletteService.createPlayers();
			playerList = rouletteService.getChoiceAndBet(playerList);
			RouletteTimerThread timeThread = new RouletteTimerThread();
			timeThread.start();
			PlayerInputThread inputThread = new PlayerInputThread(playerList, rouletteService.getScanner());
			inputThread.start();
			while(timeThread.isAlive()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			inputThread.interrupt();
			RouletteResult myresult = rouletteService.getResults(playerList);
			System.out.println("\n---------------------Results-------------------------");
			System.out.println("Number: " + myresult.getRouletteNumber());
			System.out.println("Player:       Choice        Outcome      Winnings");
			System.out.println("-------");
			for( Map.Entry<Player, PlayerResult> entry : myresult.getResults().entrySet() ){
			    System.out.println(entry.getKey().getName() + "       " + entry.getKey().getChoice() +  "       " +  entry.getValue().getResult() +  "       " + entry.getValue().getWinnings());
			}
			System.out.println("\n----------------------------------------------------");
			
			try {
				Thread.sleep(2000);
				List<PlayerHistory> myList =  rouletteService.getFinalResultsAndHistory(myresult);
				System.out.println("\n\n----------------Player History---------------------");
				System.out.println("Player:       Total Bet       Total Winnings");
				System.out.println("-------");
				for(PlayerHistory item: myList) {
					System.out.println(item.getName() + "     " +  item.getBet() + "      "  + item.getWinnings());
				}
				System.out.println("----------------------------------------------------");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
	
	public static void main(String [] args) {
		RouletteGame game = new RouletteGame();
		game.playGame();
	}
}
