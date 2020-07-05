package com.spencer.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import com.spencer.pojos.PlayerHistory;
import com.spencer.pojos.Player;
import com.spencer.pojos.PlayerResult;
import com.spencer.pojos.RouletteResult;

public class RouletteServices {
	File myObj = new File("src\\resources\\playerFile.txt");
	Scanner scan = new Scanner(System.in);

	public Vector<Player> createPlayers() {
		Vector<Player> playerList = new Vector<Player>();
		try {
			double bet = 0.00;
			FileReader myReader = new FileReader(myObj);
			BufferedReader br = new BufferedReader(myReader);
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String[] myArr = line.split(",");
					Player player = new Player(myArr[0], "", bet);
					playerList.add(player);
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return playerList;
	}

	public Vector<Player> getChoiceAndBet(Vector<Player> playerList) {
		String playerInfo = "";
		try {
			for (Player player : playerList) {
				System.out.println(player.getName() + " Enter your Choice and bet" + "(Even, Odd or a value from 1 -36)"
						+ "on a single line with the values seperated by a space");
				if (scan.hasNext()) {
					playerInfo = scan.nextLine();
				}
				String[] infoArr = playerInfo.split(" ");
				while (!choiceCheck(infoArr[0]) || !isValidBet(infoArr[1])) {
					System.out.println("Invalid Input Entered!");
					System.out.println("----------------------------------------------");
					System.out.println(
							player.getName() + " Enter your Choice and bet" + "(Even, Odd or a value from 1 -36) "
									+ "on a single line with the values seperated by a space");
					if (scan.hasNext()) {
						playerInfo = scan.nextLine();
					}
					infoArr = playerInfo.split(" ");
				}
				player.setChoice(infoArr[0]);
				try {
					player.setBet(Double.parseDouble(infoArr[1]));
				} catch (Exception e) {
					player.setBet(0.00);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return playerList;
	}

	public RouletteResult getResults(Vector<Player> playerList) {
		int rouletteSpin = (int) (Math.random() * 37 + 1);
		HashMap<Player, PlayerResult> results = new HashMap<Player, PlayerResult>();
		for (Player player : playerList) {
			if (!player.getChoice().equalsIgnoreCase("Even") && !player.getChoice().equalsIgnoreCase("Odd")) {
				int playerChoice = Integer.parseInt(player.getChoice());
				if (playerChoice == rouletteSpin) {
					results.put(player, new PlayerResult("Won", player.getBet() * 36));
				}
			}
			if (rouletteSpin % 2 == 0 && player.getChoice().equalsIgnoreCase("Even")) {
				results.put(player, new PlayerResult("Won", player.getBet() * 2));
			} else if (rouletteSpin % 2 != 0 && player.getChoice().equalsIgnoreCase("Odd")) {
				results.put(player, new PlayerResult("Won", player.getBet() * 2));
			} else if (rouletteSpin % 2 != 0 && player.getChoice().equalsIgnoreCase("Even")) {
				results.put(player, new PlayerResult("Loss", 0.00));
			} else {
				results.put(player, new PlayerResult("Loss", 0.00));
			}
		}
		return new RouletteResult(rouletteSpin, results);

	}

	public boolean isValidBet(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean choiceCheck(String s) {
		try {
			Integer.parseInt(s);
			if (Integer.parseInt(s) < 37) {
				return true;
			}
		} catch (NumberFormatException e) {
			if (s.equalsIgnoreCase("Even") || s.equalsIgnoreCase("Odd")) {
				return true;
			}
			return false;
		}
		return false;
	}

	public Scanner getScanner() {
		return this.scan;
	}

	public List<PlayerHistory> getPlayerHistoryFromfile() {
		String[] myArr;
		PlayerHistory historyObj;
		List<PlayerHistory> myList = new ArrayList<PlayerHistory>();
		try {

			Scanner scanner = new Scanner(myObj);
			while (scanner.hasNext()) {
				String data = scanner.nextLine();
				myArr = data.split(",");
				if (myArr.length < 2) {
					String arr[] = new String[3];
					arr[1] = "0";
					arr[2] = "0";
					historyObj = new PlayerHistory(myArr[0], Double.parseDouble(arr[2]), Double.parseDouble(arr[1]));
					myList.add(historyObj);
				} else {
					historyObj = new PlayerHistory(myArr[0], Double.parseDouble(myArr[2]),
							Double.parseDouble(myArr[1]));
					myList.add(historyObj);
				}

			}
			scanner.close();
			return myList;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return myList;
	}

	public List<PlayerHistory> getFinalResultsAndHistory(RouletteResult result) {
		List<PlayerHistory> myList = null;
		try {
			myList = getPlayerHistoryFromfile();
			FileWriter fileWriter = new FileWriter(myObj);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			List<PlayerHistory> resultList = new ArrayList<PlayerHistory>();
			for (Map.Entry<Player, PlayerResult> entry : result.getResults().entrySet()) {
				resultList.add(new PlayerHistory(entry.getKey().getName(), entry.getKey().getBet(),
						entry.getValue().getWinnings()));
			}

			for (int i = 0; i < myList.size(); i++) {
				myList.get(i).setBet(myList.get(i).getBet() + resultList.get(i).getBet());
				myList.get(i).setWinnings(myList.get(i).getWinnings() + resultList.get(i).getWinnings());
			}

			for (PlayerHistory item : myList) {
				if (item.getBet() != 0) {
					printWriter.println(item.getName() + "," + item.getWinnings() + "," + item.getBet());
				} else {
					printWriter.println(item.getName());
				}
			}
			printWriter.close();
			fileWriter.close();
			return myList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}
}
