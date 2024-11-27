import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grid extends ArrayList<ArrayList<Cell>>{
	public static boolean printable;
	private int width, height;
    private Warrior currentCharacter;
    private Cell currentCell;

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

	public static Grid createTheGrid(int width, int height, Warrior warrior) {
		if(width > 10 || height > 10) {
			throw new IllegalArgumentException("Maximum dimension is 10x10!");
		}
		Random rd = new Random();
		Grid grid = new Grid(width, height);
		// sanctuare, inamici, portal
		grid.addEntity(Entity.CellEntityType.SANCTUARY, 2);
        grid.addEntity(Entity.CellEntityType.ENEMY, 5);
        grid.addEntity(Entity.CellEntityType.PORTAL, 1);

		// punem jucatorul pe o pozitie aleatoare
		int playerRow = rd.nextInt(height);
		int playerCol = rd.nextInt(width);
		grid.getCell(playerRow, playerCol).setType(Entity.CellEntityType.PLAYER);
		grid.getCell(playerRow, playerCol).visited = true;
		grid.currentCell = grid.getCell(playerRow, playerCol);
		printable = true;

		grid.currentCharacter = warrior;

		return grid;
	}

	public void addEntity(Entity.CellEntityType type, int count) {
		Random rd = new Random();
		int placed = 0;
		while(placed < count) {
			int row = rd.nextInt(height);
			int col = rd.nextInt(width);

			Cell currentCell = getCell(row, col);
			if(currentCell.getType() == Entity.CellEntityType.VOID) {
				currentCell.type = type;
				placed++;
			}
		}
	}

	public Cell getCell(int rowIndex, int colIndex) {
        return this.get(rowIndex).get(colIndex);
    }

	public Cell getCurrentCell() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Cell current = getCell(i, j);
				if(current.type == Entity.CellEntityType.PLAYER) {
					return current;
				}
			}
		}
		return null;
	}

    public void setCell(int rowIndex, int colIndex, Cell cell) {
        this.get(rowIndex).set(colIndex, cell);
    }
	
	public void battle(Enemy enemy, Warrior currentCharacter) {
		Scanner scanner = new Scanner(System.in);
		while(enemy.getCurrentHealth() > 0 && currentCharacter.getCurrentHealth() > 0) {
			String choice = scanner.nextLine();
			switch (choice) {
				case "1":
					System.out.println("Inamicul are " + enemy.getCurrentHealth() + " HP");
					int defaultDamage = currentCharacter.calculateDefaultDamage(currentCharacter.getStrength());
					enemy.receiveDamage(defaultDamage);
					System.out.println("Acum inamicul are " + enemy.getCurrentHealth() + " HP");

					System.out.println("Tu ai " + currentCharacter.getCurrentHealth() + " HP");
					int defaultDamageEnemy = enemy.getDamage();
					currentCharacter.receiveDamage(currentCharacter.calculateLoseHealth(defaultDamageEnemy));
					System.out.println("Dupa atacul inamicului mai ai " + currentCharacter.getCurrentHealth() + " HP");
					
					break;
				case "2":
					break;
				default:
					break;
			}
		}
		scanner.close();
	}

	public void goNorth() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (row == 0) {
			throw new Exception("You can't go north from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row - 1, col);
		if(target.type == Entity.CellEntityType.ENEMY) {
			Enemy enemy = new Enemy();

			System.out.println("Choose how to attack: ");
			System.out.println("1. Basic attack");
			System.out.println("2. Spell");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			// modul de combat
			Game.currentEnemy = enemy;
			battle(enemy, currentCharacter);
			
			printable = false;
		}
		if(target.type != Entity.CellEntityType.ENEMY) {
			printable = true;
		}
		target.visited = true;
		// Setează tipul celulei curente la VOID
		current.setType(Entity.CellEntityType.VOID);
	
		// Setează tipul celulei țintă la PLAYER
		target.setType(Entity.CellEntityType.PLAYER);
	
		// Actualizează celula curentă
		currentCell = target;
	}

	public void goSouth() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (row == height - 1) {
			throw new Exception("You can't go south from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row + 1, col);
		if(target.type == Entity.CellEntityType.ENEMY) {
			Enemy enemy = new Enemy();

			System.out.println("Choose how to attack: ");
			System.out.println("1. Basic attack");
			System.out.println("2. Spell");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			// modul de combat
			Game.currentEnemy = enemy;
			battle(enemy, currentCharacter);
			
			printable = false;
		}
		if(target.type != Entity.CellEntityType.ENEMY) {
			printable = true;
		}
		target.visited = true;
		current.setType(Entity.CellEntityType.VOID);
	
		// Setează tipul celulei țintă la PLAYER
		target.setType(Entity.CellEntityType.PLAYER);

		currentCell = target;
	}
	
	public void goEast() throws Exception{
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if(col == width - 1) {
			throw new Exception("You can't go east from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row, col + 1);
		if(target.type == Entity.CellEntityType.ENEMY) {
			Enemy enemy = new Enemy();

			System.out.println("Choose how to attack: ");
			System.out.println("1. Basic attack");
			System.out.println("2. Spell");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			// modul de combat
			Game.currentEnemy = enemy;
			battle(enemy, currentCharacter);
			
			printable = false;
		}
		if(target.type != Entity.CellEntityType.ENEMY) {
			printable = true;
		}
		target.visited = true;
		current.setType(Entity.CellEntityType.VOID);
	
		// Setează tipul celulei țintă la PLAYER
		target.setType(Entity.CellEntityType.PLAYER);

		currentCell = target;
	}

	public void goWest() throws Exception{
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if(col == 0) {
			throw new Exception("You can't go west from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row, col - 1);
		if(target.type == Entity.CellEntityType.ENEMY) {
			Enemy enemy = new Enemy();

			System.out.println("Choose how to attack: ");
			System.out.println("1. Basic attack");
			System.out.println("2. Spell");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			// modul de combat
			Game.currentEnemy = enemy;
			battle(enemy, currentCharacter);
			
			printable = false;
		}
		if(target.type != Entity.CellEntityType.ENEMY) {
			printable = true;
		}
		target.visited = true;
		current.setType(Entity.CellEntityType.VOID);
	
		// Setează tipul celulei țintă la PLAYER
		target.setType(Entity.CellEntityType.PLAYER);

		currentCell = target;
	}

	public void printGrid() {
		if(printable == true) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Cell cell = getCell(i, j);
					if(cell == currentCell) {
						System.out.print("P ");
					}
					if(cell.visited == false) {
						System.out.print("* ");
					}
					else {
						switch (cell.getType()) {
							case PLAYER:
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
							case VOID:
							default:
								System.out.print("V ");
								break;
						}
					}
				}
				System.out.println();
			}
		}
    }
}
