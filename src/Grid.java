import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>>{
	private int width, height;
    private Character currentCharacter;
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

	public static Grid createTheGrid(int width, int height) {
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
		grid.currentCell = grid.getCell(playerRow, playerCol);
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
	
	public void goNorth() throws Exception {
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if (row == 0) {
			throw new Exception("You can't go north from here!");
		}
		Cell current = getCell(row, col);
		Cell target = getCell(row - 1, col);
	
		// Schimbă tipurile celulelor
		current.setType(Entity.CellEntityType.VOID);
		target.setType(Entity.CellEntityType.PLAYER);
	
		// Actualizează celula curentă
		currentCell = target;
	}
	public void goSouth() throws Exception{
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if(row == height - 1) {
			throw new Exception("You can't go south from here!");
		}
		currentCell = getCell(row + 1, col);
	}
	public void goEast() throws Exception{
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if(col == 0) {
			throw new Exception("You can't go east from here!");
		}
		currentCell = getCell(row, col - 1);
	}
	public void goWest() throws Exception{
		int row = currentCell.getOx();
		int col = currentCell.getOy();
		if(col == width - 1) {
			throw new Exception("You can't go west from here!");
		}
		currentCell = getCell(row, col + 1);
	}

	public void printGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = getCell(i, j);
				if(cell == currentCell) {
					System.out.print("P ");
				}
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
                        System.out.print(". ");
                        break;
                }
            }
            System.out.println();
        }
    }
	public static void main(String[] args) {
		// testare
		Grid grid = Grid.createTheGrid(10, 10);

        // Afișează grila
        grid.printGrid();
	}
}
