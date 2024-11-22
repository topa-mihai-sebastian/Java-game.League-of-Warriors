public class Cell {
	int Ox, Oy;
	boolean visited;
	Entity.CellEntityType type;
	
	public Cell() {
		this.Ox = 0;
		this.Oy = 0;
		this.type = Entity.CellEntityType.VOID; // Sau orice tip implicit dorești
		this.visited = false;
	}

	public Cell(int Ox, int Oy, Entity.CellEntityType type) {
        this.Ox = Ox;
        this.Oy = Oy;
        this.type = type;
        this.visited = false;
    }

    // Getteri și setteri pentru câmpuri
    public int getOx() {
        return Ox;
    }

    public void setOx(int Ox) {
        this.Ox = Ox;
    }

    public int getOy() {
        return Oy;
    }

    public void setOy(int Oy) {
        this.Oy = Oy;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Entity.CellEntityType getType() {
        return type;
    }

    public void setType(Entity.CellEntityType type) {
        this.type = type;
    }
}
