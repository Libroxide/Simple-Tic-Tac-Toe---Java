import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
				              {'-', '+', '-', '+', '-'},
				              {' ', '|', ' ', '|', ' '},
				              {'-', '+', '-', '+', '-'},
				              {' ', '|', ' ', '|', ' '}};
		
		outputGameBoard(gameBoard);
		
		
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter your placement (1 - 9): ");
			int playerPos = sc.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position has already been taken! Choose a different one");
				playerPos = sc.nextInt();
			}
				
			
			placeSymbol(gameBoard, playerPos,"player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placeSymbol(gameBoard, cpuPos, "cpu");
			
			outputGameBoard(gameBoard);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
		}
		
		
		

	}
	
	public static void outputGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		
	}
	
	public static void placeSymbol(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
	    default:
	    	break;
			
		
		}
		
	}
	
	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diagRight = Arrays.asList(1, 5, 9);
		List diagLeft = Arrays.asList(3, 5, 7);
		
		List<List> winningCon = new ArrayList<List>();
		winningCon.add(topRow);
		winningCon.add(midRow);
		winningCon.add(bottomRow);
		winningCon.add(leftCol);
		winningCon.add(midCol);
		winningCon.add(rightCol);
		winningCon.add(diagRight);
		winningCon.add(diagLeft);
		
		for(List l : winningCon) {
			if(playerPositions.containsAll(l)) {
				
				return "Well Done YOU WON!";
				
			}else if(cpuPositions.containsAll(l)) {
				
				return "You Lose :( CPU WINS";
			}else if((playerPositions.size() + cpuPositions.size()) == 9) {
				
				return "CAT";
			}
			
		}
		return "";
	}

}
