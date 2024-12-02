import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public ArrayList<Account> accountList;
	public Grid gameGrid;
	public Warrior myWarrior;
	public static Enemy currentEnemy;

	public Game() {
		Random rd = new Random();
		int randomWidth = rd.nextInt(7) + 4;
		int randomHeight = rd.nextInt(7) + 4;
		this.myWarrior = new Warrior("Hero", 50, 3, 20, 45, 35);
		this.myWarrior.setCurrentHealth(myWarrior.maxHealth);
		this.gameGrid = Grid.createTheGrid(randomWidth, randomHeight, myWarrior);
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		String command;
		try {
			while (true) {
				gameGrid.printGrid();
				if (Grid.printable) {
					printOptions();
				}
				System.out.print("Enter your choice: ");
				if (scanner.hasNextLine()) {
					command = scanner.nextLine();
					if (!command.isEmpty()) {
						try {
							executeCommand(command);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("No input provided. Please enter a valid command.");
					}
				} else {
					System.out.println("No input available. Exiting...");
					break;
				}
				if (currentEnemy != null && currentEnemy.getCurrentHealth() <= 0) {
					System.out.println("Enemy defeated!!");
					currentEnemy = null;
				}
				if (myWarrior.getCurrentHealth() <= 0) {
					System.out.println("YOU LOST!");
					System.out.println("YOU HAVE NO HEALTH LEFT");
					break;
				}
			}
		} finally {
			scanner.close();
		}
	}

	public void printOptions() {
		Cell currentCell = gameGrid.getCurrentCell();
		System.out.println("You are currently at cell [" + currentCell.getOx() + ", " + currentCell.getOy() + "]");
		System.out.println("Available options:");
		System.out.println("W. Go North");
		System.out.println("S. Go South");
		System.out.println("D. Go East");
		System.out.println("A. Go West");
		System.out.println("Q. Exit");
	}

	public void executeCommand(String command) throws Exception {
		switch (command) {
			case "w":
				if (gameGrid.goNorth() == true) {
					gameGrid.battle(currentEnemy, myWarrior);
				}
				break;
			case "s":
				if (gameGrid.goSouth() == true) {
					gameGrid.battle(currentEnemy, myWarrior);
				}
				break;
			case "d":
				if (gameGrid.goEast() == true) {
					gameGrid.battle(currentEnemy, myWarrior);
				}
				break;
			case "a":
				if (gameGrid.goWest() == true) {
					gameGrid.battle(currentEnemy, myWarrior);
				}
				break;
			case "q":
				System.out.println("Exiting the game.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
		}
	}

	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.run();
	}
}
