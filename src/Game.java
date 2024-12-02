import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	public static ArrayList<Account> createAccounts() {
		ArrayList<Account> accounts = new ArrayList<>();

		// Exemplu de date pentru conturi
		ArrayList<Character> characters1 = new ArrayList<>();
		characters1.add(new Character("Odysseus Prisco", "Warrior", 1, 15));
		characters1.add(new Character("Kameron Neppl", "Mage", 1, 10));
		characters1.add(new Character("Chlarimonde Markert", "Rogue", 1, 35));
		Information info1 = new Information(new Credentials("marcel@yahoo.com", "6K7GUxjsAc"), "Marcel", "Romania",
				List.of("Metin", "4Story"), 15);
		accounts.add(new Account(characters1, 0, info1));

		ArrayList<Character> characters2 = new ArrayList<>();
		characters2.add(new Character("Brisco Schaab", "Warrior", 4, 21));
		characters2.add(new Character("Scarlett Gardon", "Mage", 7, 50));
		characters2.add(new Character("Miyoko Fei", "Rogue", 15, 40));
		Information info2 = new Information(new Credentials("genna1951@hotmail.red", "123456"), "Nawra Ortwin",
				"Turkey", List.of("World of Warcraft", "Metin2", "Need for Speed"), 3);
		accounts.add(new Account(characters2, 0, info2));

		// Adaugă restul conturilor în mod similar...

		return accounts;
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
			//scanner.close();
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

	public static boolean logIn(ArrayList<Account> accounts) {
		boolean loggedIn = false;
		String email, password;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter email: ");
			email = scanner.nextLine();
			for (Account account : accounts) {
				if (email.equals(account.getInformation().getCredentials().getEmail())) {
					System.out.print("Enter password: ");
					password = scanner.nextLine();
					if (password.equals(account.getInformation().getCredentials().getPassword())) {
						loggedIn = true;
						System.out.println("Login successful!");
						break;
					} else {
						System.out.println("Incorrect password. Please try again.");
					}
				}
			}
			if (loggedIn) {
				break;
			} else {
				System.out.println("Email not found. Please try again.");
			}
		}
		return loggedIn;
	}

	public static void main(String[] args) {
		Game newGame = new Game();
		ArrayList<Account> accounts = createAccounts();
		boolean youReIn = logIn(accounts);
		if (youReIn) {
			newGame.run();
		}
	}
}
