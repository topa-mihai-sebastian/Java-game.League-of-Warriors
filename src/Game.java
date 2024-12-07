import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public static Warrior myWarrior;
	public static Mage myMage;
	public static Rogue myRogue;

	public ArrayList<Account> accountList;
	public Grid gameGrid;
	public static Character currentCharacter;
	public static Enemy currentEnemy;
	public static boolean onSanctuary;

	public Game() {
		Random rd = new Random();
		int randomWidth = rd.nextInt(7) + 4;
		int randomHeight = rd.nextInt(7) + 4;
		Game.myWarrior = new Warrior("Hero", 50, 3, 20, 45, 35);
		Game.myWarrior.setCurrentHealth(myWarrior.maxHealth);

		Game.myMage = new Mage("Hero", 50, 3, 20, 45, 35);
		Game.myMage.setCurrentHealth(myWarrior.maxHealth);

		Game.myRogue = new Rogue("Hero", 50, 3, 20, 45, 35);
		Game.myRogue.setCurrentHealth(myWarrior.maxHealth);

		this.gameGrid = Grid.createTheGrid(randomWidth, randomHeight, myWarrior);
	}

	public static ArrayList<Account> createAccounts() {
		ArrayList<Account> accounts = new ArrayList<>();

		// Exemplu de date pentru conturi
		ArrayList<Character> characters1 = new ArrayList<>();
		characters1.add(new Character("Odysseus Prisco", "Warrior", 1, 15));
		characters1.add(new Character("Kameron Neppl", "Mage", 1, 10));
		characters1.add(new Character("Chlarimonde Markert", "Rogue", 1, 35));
		Information info1 = new Information(new Credentials("marcel@yahoo.com", "1234"), "Marcel", "Romania",
				Arrays.asList("Metin", "4Story"), 15);
		accounts.add(new Account(characters1, 0, info1));

		ArrayList<Character> characters2 = new ArrayList<>();
		characters2.add(new Character("Brisco Schaab", "Warrior", 4, 21));
		characters2.add(new Character("Scarlett Gardon", "Mage", 7, 50));
		characters2.add(new Character("Miyoko Fei", "Rogue", 15, 40));
		Information info2 = new Information(new Credentials("genna1951@hotmail.red", "123456"), "Nawra Ortwin",
				"Turkey", Arrays.asList("World of Warcraft", "Metin2", "Need for Speed"), 3);
		accounts.add(new Account(characters2, 0, info2));

		ArrayList<Character> characters3 = new ArrayList<>();
		characters3.add(new Character("Fujio Takeshita", "Warrior", 12, 15));
		characters3.add(new Character("Briareus Prestia", "Mage", 5, 10));
		characters3.add(new Character("Kame Oda", "Rogue", 11, 5));
		Information info3 = new Information(new Credentials("mculrad0586@perirh.com", "aTtZWI7SDl"), "Gemma Eusebius",
				"France", Arrays.asList("Metin", "4Story"), 1);
		accounts.add(new Account(characters3, 0, info3));

		ArrayList<Character> characters4 = new ArrayList<>();
		characters4.add(new Character("Fedele Sama", "Warrior", 10, 20));
		characters4.add(new Character("Jannik Wriedt", "Mage", 1, 0));
		characters4.add(new Character("Hisa Hano", "Rogue", 2, 20));
		Information info4 = new Information(new Credentials("kdsinc@o0i.es", "bxSvxYcaoD"), "Grimwald Marciane",
				"United Kingdom", Arrays.asList("Metin", "4Story"), 20);
		accounts.add(new Account(characters4, 0, info4));

		ArrayList<Character> characters5 = new ArrayList<>();
		characters5.add(new Character("Rina Zanin", "Warrior", 3, 4));
		characters5.add(new Character("Dyana Inselman", "Mage", 5, 10));
		characters5.add(new Character("Uysal Abdallah", "Rogue", 7, 45));
		Information info5 = new Information(new Credentials("dd55avid@lited.site", "L5PN1Qknrn"), "Sanjiv Bénédicte",
				"India", Arrays.asList("Counter Strike 1.6", "GTA V", "Metin 2"), 60);
		accounts.add(new Account(characters5, 0, info5));

		ArrayList<Character> characters6 = new ArrayList<>();
		characters6.add(new Character("Silvain Spilker", "Warrior", 8, 0));
		characters6.add(new Character("Thibaut Goy", "Mage", 10, 3));
		characters6.add(new Character("Eyup Uner", "Rogue", 2, 3));
		Information info6 = new Information(new Credentials("troydealbaby@eoooodid.com", "nhnn0HXi8q"),
				"Praveena Yevheniy", "Romania", Arrays.asList("Metin 2", "League of Legends"), 10);
		accounts.add(new Account(characters6, 0, info6));

		ArrayList<Character> characters7 = new ArrayList<>();
		characters7.add(new Character("Jaiden Kimmich", "Warrior", 10, 1));
		characters7.add(new Character("Zain Eiden", "Mage", 7, 2));
		characters7.add(new Character("Crocefissa Smeriglio", "Rogue", 11, 5));
		Information info7 = new Information(new Credentials("al6056@keralaairport.net", "fBfQbuDm8Z"), "Victor Madhuri",
				"Bulgaria", Arrays.asList("Lego Lord of the Rings", "Guild Wars 2"), 3);
		accounts.add(new Account(characters7, 0, info7));

		ArrayList<Character> characters8 = new ArrayList<>();
		characters8.add(new Character("Fiona Broussard", "Warrior", 20, 2));
		characters8.add(new Character("Fjodora Schutzman", "Mage", 15, 3));
		characters8.add(new Character("Shigeru Uno", "Rogue", 6, 10));
		Information info8 = new Information(new Credentials("roschsin@epubp.site", "AtprqBw5np"), "Stuart Dorofei",
				"Romania", Arrays.asList("Batman Arkham Knight", "Batman Arkham Asylum"), 13);
		accounts.add(new Account(characters8, 0, info8));

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
				if (currentCharacter.getCurrentHealth() <= 0) {
					System.out.println("YOU LOST!");
					System.out.println("YOU HAVE NO HEALTH LEFT");
					break;
				}
			}
		} finally {
			// scanner.close();
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
					gameGrid.battle(currentEnemy, currentCharacter);
				}
				break;
			case "s":
				if (gameGrid.goSouth() == true) {
					gameGrid.battle(currentEnemy, currentCharacter);
				}
				break;
			case "d":
				if (gameGrid.goEast() == true) {
					gameGrid.battle(currentEnemy, currentCharacter);
				}
				break;
			case "a":
				if (gameGrid.goWest() == true) {
					gameGrid.battle(currentEnemy, currentCharacter);
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

	public static int logIn(ArrayList<Account> accounts) {
		int loggedIn = -1;
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
						System.out.println();
						System.out.println("Login successful!");
						System.out.println();
						loggedIn = accounts.indexOf(account);
						break;
					} else {
						System.out.println("Incorrect password. Please try again.");
					}
				}
			}
			if (loggedIn != -1) {
				break;
			} else {
				System.out.println("Email not found. Please try again.");
			}
		}

		return loggedIn;
	}

	public static void createCharacter(String type) {
		switch (type) {
			case "Warrior":
				System.out.println("You have chosen the warrior");
				Game.myWarrior = new Warrior("Hero", 50, 3, 20, 45, 35);
				Game.myWarrior.setCurrentHealth(myWarrior.maxHealth);
				Game.myWarrior.setCurrentMana(1000);
				Game.currentCharacter = myWarrior;
				Game.currentCharacter.spells = generateRandomSpells();
				break;
			case "Mage":
				System.out.println("You have chosen the mage");
				Game.myMage = new Mage("Hero", 50, 3, 20, 45, 35);
				Game.myMage.setCurrentHealth(myWarrior.maxHealth);
				Game.myMage.setCurrentMana(1000);
				Game.currentCharacter = myMage;
				Game.currentCharacter.spells = generateRandomSpells();
				break;
			case "Rogue":
				System.out.println("You have chosen the rogue");
				Game.myRogue = new Rogue("Hero", 50, 3, 20, 45, 35);
				Game.myRogue.setCurrentHealth(myWarrior.maxHealth);
				Game.myRogue.setCurrentMana(1000);
				Game.currentCharacter = myRogue;
				Game.currentCharacter.spells = generateRandomSpells();
				break;
			default:
				break;
		}
	}

	public static void useSpell(Character currentCharacter, Enemy currentEnemy) {
		System.out.println(currentCharacter.spells.toString());
	}

	public static ArrayList<Spell> generateRandomSpells() {
        ArrayList<Spell> spells = new ArrayList<Spell>();
        Random rd = new Random();
        int numSpells = rd.nextInt(4) + 3; // Generate between 3 and 6 spells

        for (int i = 0; i < numSpells; i++) {
            int damage = rd.nextInt(50) + 10;
            int manaCost = rd.nextInt(20) + 5;
            int spellType = rd.nextInt(3);
            switch (spellType) {
                case 0:
                    spells.add(new Fire(damage, manaCost));
                    break;
                case 1:
                    spells.add(new Earth(damage, manaCost));
                    break;
                case 2:
                    spells.add(new Ice(damage, manaCost));
                    break;
            }
        }

        return spells;
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Game newGame = new Game();
		Game.onSanctuary = false;
		ArrayList<Account> accounts = createAccounts();
		int index = logIn(accounts);
		if (index != -1) {
			Account loggedInAccount = accounts.get(index);
			List<Character> characters = loggedInAccount.getCharacters();
			System.out.println("Professions of characters in the logged-in account:");
			for (int i = 0; i < characters.size(); i++) {
				Character character = characters.get(i);
				System.out.println((i + 1) + ". " + character.getName() + " - " + character.getProfession());
			}
			int choice = -1;
			while (choice < 1 || choice > characters.size()) {
				System.out.print("Choose a character by entering the corresponding number: ");
				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
					if (choice < 1 || choice > characters.size()) {
						System.out.println("Invalid choice. Please try again.");
					}
				} else {
					System.out.println("Invalid input. Please enter a number.");
					scanner.next(); // Clear invalid input
				}
			}
			Character chosenCharacter = characters.get(choice - 1);
			System.out.println("You have chosen: " +
			chosenCharacter.getName() + " - " + chosenCharacter.getProfession());
			
			createCharacter(chosenCharacter.getProfession());

			newGame.run();
		}
	}
}
