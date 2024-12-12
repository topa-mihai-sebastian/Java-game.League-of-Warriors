import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grid extends ArrayList<ArrayList<Cell>> {
	public static boolean printable;
	private int width, height;

	public Cell currentCell;

	private Grid(int width, int height) {
		this.width = width;
		this.height = height;

		for (int i = 0; i < height; i++) {
			ArrayList<Cell> row = new ArrayList<>();
			for (int j = 0; j < width; j++) {
				row.add(new Cell(i, j, Entity.CellEntityType.VOID)); // Folosește constructorul cu parametri
			}
			this.add(row);
		}
	}

	public static Grid createTheGrid(int width, int height, Character character) {
		if (width > 10 || height > 10) {
			throw new IllegalArgumentException("Maximum dimension is 10x10!");
		}
		Random rd = new Random();
		Grid grid = new Grid(width, height);
		// sanctuare, inamici, portal
		grid.addEntity(Entity.CellEntityType.SANCTUARY, 2);
		grid.addEntity(Entity.CellEntityType.ENEMY, 4);
		grid.addEntity(Entity.CellEntityType.PORTAL, 1);

		// punem jucatorul pe o pozitie aleatoare
		int playerRow = rd.nextInt(height);
		int playerCol = rd.nextInt(width);
		grid.getCell(playerRow, playerCol).setType(Entity.CellEntityType.PLAYER);
		grid.getCell(playerRow, playerCol).visited = true;
		grid.currentCell = grid.getCell(playerRow, playerCol);
		printable = true;

		Game.currentCharacter = character;

		return grid;
	}

	public void addEntity(Entity.CellEntityType type, int count) {
		Random rd = new Random();
		int placed = 0;
		while (placed < count) {
			int row = rd.nextInt(height);
			int col = rd.nextInt(width);

			Cell currentCell = getCell(row, col);
			if (currentCell.getType() == Entity.CellEntityType.VOID) {
				currentCell.type = type;
				placed++;
			}
		}
	}

	public Cell getCell(int rowIndex, int colIndex) {
		return this.get(rowIndex).get(colIndex);
	}

	public Cell getCurrentCell() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cell current = getCell(i, j);
				if (current.type == Entity.CellEntityType.PLAYER) {
					return current;
				}
			}
		}
		return null;
	}

	public void setCell(int rowIndex, int colIndex, Cell cell) {
		this.get(rowIndex).set(colIndex, cell);
	}

	public void battle(Enemy enemy, Character currentCharacter) {
		Scanner scanner = new Scanner(System.in);
		String choice;
		int defaultDamageEnemy, defaultDamage;
		Random rd = new Random();
		boolean enemyAtack;
		while (enemy.getCurrentHealth() > 0 && Game.currentCharacter.getCurrentHealth() > 0) {
			System.out.println("Choose how to attack: ");
			System.out.println("1. Basic attack");
			System.out.println("2. Spell");
			choice = scanner.nextLine();
			switch (choice) {
				case "1":
					clearScreen();
					printGrid();

					System.out.println("Inamicul are " + enemy.getCurrentHealth() + " HP");
					defaultDamage = Game.currentCharacter.calculateDefaultDamage();
					enemy.receiveDamage(defaultDamage);
					System.out.println("Acum inamicul are " + enemy.getCurrentHealth() + " HP");

					enemyAtack = rd.nextBoolean();
					if (enemyAtack) {
						System.out.println("Inamicul ataca cu un atac de baza!");
						System.out.println("Tu ai " + Game.currentCharacter.getCurrentHealth() + " HP");
						defaultDamageEnemy = enemy.getDamage();
						Game.currentCharacter
								.receiveDamage(Game.currentCharacter.calculateLoseHealth(defaultDamageEnemy));
						System.out.println(
								"Dupa atacul inamicului mai ai " + Game.currentCharacter.getCurrentHealth() + " HP");
					} else {
						System.out.println("Inamicul ataca cu o abilitate!");
						System.out.println("Tu ai " + Game.currentCharacter.getCurrentHealth() + " HP");
						Game.useSpell(enemy, currentCharacter);
						System.out.println(
								"Dupa atacul inamicului mai ai " + Game.currentCharacter.getCurrentHealth() + " HP");
					}
					break;
				case "2":
					clearScreen();
					printGrid();

					// Logica pentru atacul cu vrajă
					System.out.println("Ai folosit o vrajă!");
					System.out.println("Mai ai " + Game.currentCharacter.getCurrentMana() + " mana!");
					System.out.println("Inamicul are " + enemy.getCurrentHealth() + " HP");
					Game.useSpell(Game.currentCharacter, Game.currentEnemy);
					System.out.println("Acum inamicul are " + enemy.getCurrentHealth() + " HP");

					// trebuie sa fac ca inamicul sa aleaga random dintre spell si atac

					enemyAtack = rd.nextBoolean();
					if (enemyAtack) {
						System.out.println("Inamicul ataca cu un atac de baza!");
						System.out.println("Tu ai " + Game.currentCharacter.getCurrentHealth() + " HP");
						defaultDamageEnemy = enemy.getDamage();
						Game.currentCharacter
								.receiveDamage(Game.currentCharacter.calculateLoseHealth(defaultDamageEnemy));
						System.out.println(
								"Dupa atacul inamicului mai ai " + Game.currentCharacter.getCurrentHealth() + " HP");
					} else {
						System.out.println("Inamicul ataca cu o abilitate!");
						System.out.println("Tu ai " + Game.currentCharacter.getCurrentHealth() + " HP");
						Game.useSpell(enemy, currentCharacter);
						System.out.println(
								"Dupa atacul inamicului mai ai " + Game.currentCharacter.getCurrentHealth() + " HP");
					}
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		}
		if (enemy.getCurrentHealth() <= 0) {
			System.out.println("Enemy defeated!");
			Game.currentCharacter.setCurrentHealth(100);
			Game.currentCharacter.setCurrentMana(1000);
			Game.currentCharacter.setStrength((int) (Game.currentCharacter.getStrength() * 1.5));
			Game.currentCharacter.setDexterity((int) (Game.currentCharacter.getDexterity() * 1.5));
			Game.currentCharacter.setCharisma((int) (Game.currentCharacter.getCharisma() * 1.5));
			Game.currentCharacter.spells = Game.generateRandomSpells();
		}
		if (Game.currentCharacter.getCurrentHealth() <= 0) {
			System.out.println("You have been defeated!");
			Game.chooseCharacter(Game.game, Game.loggedInAccount);
		}
	}

	@SuppressWarnings("resource")
	public void usePortal() {
		clearScreen();

		System.out.println("Ai intrat in portal!");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Apasa enter pentru a genera noua harta: ");
		scanner.nextLine();
		// aici fa niste upgrade-uri la caracter
		
		Random rd = new Random();

		int width = rd.nextInt(7) + 4;
		int height = rd.nextInt(7) + 4;
		Game.currentCharacter.setCurrentHealth(100);
		Game.currentCharacter.setCurrentMana(1000);
		Game.currentCharacter.setStrength((int) (Game.currentCharacter.getStrength() * 1.1));
		Game.currentCharacter.setDexterity((int) (Game.currentCharacter.getDexterity() * 1.1));
		Game.currentCharacter.setCharisma((int) (Game.currentCharacter.getCharisma() * 1.1));
		Game.gameGrid = createTheGrid(width, height, Game.currentCharacter);
	}

	public boolean goNorth() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (row == 0) {
			throw new Exception("You can't go north from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row - 1, col);

		// Setează tipul celulei curente la VOID
		current.setType(Entity.CellEntityType.VOID);
		// Setează tipul celulei țintă la PLAYER
		Entity.CellEntityType aux = target.getType();
		target.setType(Entity.CellEntityType.PLAYER);
		target.visited = true;
		// Actualizează celula curentă
		currentCell = target;

		// Actualizează currentEnemy dacă întâlnești un inamic
		if (aux == Entity.CellEntityType.ENEMY) {
			Game.currentEnemy = new Enemy();
		}

		if (aux == Entity.CellEntityType.SANCTUARY) {
			Random rd = new Random();
			Game.onSanctuary = true;
			int bonus = rd.nextInt(35) + 35;
			Game.currentCharacter.lifeRegen(bonus);
			bonus = rd.nextInt(35) + 300;
			;
			Game.currentCharacter.manaRegen(bonus);
		}

		if(aux == Entity.CellEntityType.PORTAL) {
			usePortal();
		}
		return aux == Entity.CellEntityType.ENEMY;
	}

	public boolean goSouth() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (row == height - 1) {
			throw new Exception("You can't go south from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row + 1, col);

		// Setează tipul celulei curente la VOID
		current.setType(Entity.CellEntityType.VOID);

		// Setează tipul celulei țintă la PLAYER
		Entity.CellEntityType aux = target.getType();
		target.setType(Entity.CellEntityType.PLAYER);
		target.visited = true;
		// Actualizează celula curentă
		currentCell = target;

		if (aux == Entity.CellEntityType.ENEMY) {
			Game.currentEnemy = new Enemy();
		}
		if (aux == Entity.CellEntityType.SANCTUARY) {
			Random rd = new Random();
			Game.onSanctuary = true;
			int bonus = rd.nextInt(35) + 35;
			Game.currentCharacter.lifeRegen(bonus);
			bonus = rd.nextInt(35) + 300;
			;
			Game.currentCharacter.manaRegen(bonus);
		}
		if(aux == Entity.CellEntityType.PORTAL) {
			usePortal();
		}
		return aux == Entity.CellEntityType.ENEMY;
	}

	public boolean goEast() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (col == width - 1) {
			throw new Exception("You can't go east from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row, col + 1);

		// Setează tipul celulei curente la VOID
		current.setType(Entity.CellEntityType.VOID);

		// Setează tipul celulei țintă la PLAYER
		Entity.CellEntityType aux = target.getType();
		target.setType(Entity.CellEntityType.PLAYER);
		target.visited = true;
		// Actualizează celula curentă
		currentCell = target;

		if (aux == Entity.CellEntityType.ENEMY) {
			Game.currentEnemy = new Enemy();
		}
		if (aux == Entity.CellEntityType.SANCTUARY) {
			Random rd = new Random();
			Game.onSanctuary = true;
			int bonus = rd.nextInt(35) + 35;
			Game.currentCharacter.lifeRegen(bonus);
			bonus = rd.nextInt(35) + 300;
			;
			Game.currentCharacter.manaRegen(bonus);
		}
		if(aux == Entity.CellEntityType.PORTAL) {
			usePortal();
		}
		return aux == Entity.CellEntityType.ENEMY;
	}

	public boolean goWest() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (col == 0) {
			throw new Exception("You can't go west from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row, col - 1);

		// Setează tipul celulei curente la VOID
		current.setType(Entity.CellEntityType.VOID);

		// Setează tipul celulei țintă la PLAYER
		Entity.CellEntityType aux = target.getType();
		target.setType(Entity.CellEntityType.PLAYER);
		target.visited = true;
		// Actualizează celula curentă
		currentCell = target;

		if (aux == Entity.CellEntityType.ENEMY) {
			Game.currentEnemy = new Enemy();
		}
		if (aux == Entity.CellEntityType.SANCTUARY) {
			Random rd = new Random();
			Game.onSanctuary = true;
			int bonus = rd.nextInt(35) + 35;
			Game.currentCharacter.lifeRegen(bonus);
			bonus = rd.nextInt(35) + 300;
			;
			Game.currentCharacter.manaRegen(bonus);
		}
		if(aux == Entity.CellEntityType.PORTAL) {
			usePortal();
		}
		return aux == Entity.CellEntityType.ENEMY;
	}

	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void printGrid() {
		clearScreen();
		if (printable == true) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Cell cell = getCell(i, j);
					if (cell.visited == false) {
						System.out.print("* ");
					} else {
						switch (cell.getType()) {
							case PLAYER:
								System.out.print("P ");
								break;
							case SANCTUARY:
								System.out.print("S ");
								break;
							case ENEMY:
								System.out.print("E ");
								break;
							case PORTAL:
								System.out.print("O ");
								break;
							default:
								System.out.print("V ");
								break;
						}
					}
				}
				System.out.println();
			}
			System.out.println();
			// Detalii despre caracter in timp real
			System.out.println("->" + Game.currentCharacter.getProfession() + "<-");
			System.out.println("Current health: " + Game.currentCharacter.getCurrentHealth());
			System.out.println("Current mana: " + Game.currentCharacter.getCurrentMana());
			System.out.println("Current strength: " + Game.currentCharacter.getStrength());
			System.out.println("Current dexterity: " + Game.currentCharacter.getDexterity());
			System.out.println("Current charisma: " + Game.currentCharacter.getCharisma());
			System.out.println();

			if (Game.onSanctuary == true) {
				System.out.println("You are in a sanctuary!");
				System.out.println("Your new life is:" + Game.myWarrior.getCurrentHealth());
				System.out.println("Your new mana is:" + Game.myWarrior.getCurrentMana());
				System.out.println();
				Game.onSanctuary = false;
			}
		}
	}
}
