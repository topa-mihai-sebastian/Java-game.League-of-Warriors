import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
	public Game testGame;

	public Test() {
		Scanner scanner = new Scanner(System.in);
		testGame = new Game();
		Game.gameGrid = Grid.createTheGrid(5, 5, Game.currentCharacter);
		Game.onSanctuary = false;
		ArrayList<Account> accounts = Game.createAccounts();
		int index = Game.logIn(accounts);
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

			Game.createCharacter(chosenCharacter.getProfession());

			// HARDCODARE

			// Setează toate celulele la tipul VOID
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					Game.gameGrid.getCell(i, j).setType(Entity.CellEntityType.VOID);
					Game.gameGrid.getCell(i, j).visited = false;
				}
			}
			Game.gameGrid.getCell(0, 0).setType(Entity.CellEntityType.PLAYER);
			Game.gameGrid.getCell(0, 3).setType(Entity.CellEntityType.SANCTUARY);
			Game.gameGrid.getCell(1, 3).setType(Entity.CellEntityType.SANCTUARY);
			Game.gameGrid.getCell(2, 0).setType(Entity.CellEntityType.SANCTUARY);
			Game.gameGrid.getCell(4, 3).setType(Entity.CellEntityType.SANCTUARY);
			Game.gameGrid.getCell(3, 4).setType(Entity.CellEntityType.ENEMY);
			Game.gameGrid.getCell(4, 4).setType(Entity.CellEntityType.PORTAL);

			// Setează poziția caracterului la (0,0)
			Game.gameGrid.getCell(0, 0).setType(Entity.CellEntityType.PLAYER);
			Game.gameGrid.getCell(0, 0).visited = true;
			Game.gameGrid.currentCell = Game.gameGrid.getCell(0, 0);
			testGame.run();
		}
	}

	public static void main(String[] args) {
		Test test = new Test();
	}
}
