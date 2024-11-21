import java.util.ArrayList;
import java.util.List;

public class Grid extends ArrayList<ArrayList<Cell>>{
	int width, height;
	int number;
	Character currentCharacter;
	List<List<Cell>> grid;

	private Grid(int width, int height) {
		this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new Cell()); // Assuming Cell has a default constructor 
            }
            this.add(row);
        }
    }
	public Cell getCell(int rowIndex, int colIndex) {
        return this.get(rowIndex).get(colIndex);
    }

    public void setCell(int rowIndex, int colIndex, Cell cell) {
        this.get(rowIndex).set(colIndex, cell);
    }
	
	public void goNorth() {

	}
	public void goSouth() {
		
	}
	public void goEast() {
		
	}
	public void goWest() {
		
	}
}
