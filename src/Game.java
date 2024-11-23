import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public ArrayList<Account> accountList;
	public Grid gameGrid;

	public Game() {
		this.gameGrid = Grid.createTheGrid(10, 10);
	}

	public void run(){
		Scanner scanner = new Scanner(System.in);
		while(true) {
			gameGrid.printGrid();
			printOptions();
			String command = scanner.nextLine();
			try {
				executeCommand(command);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void printOptions(){
		Cell currentCell = gameGrid.getCurrentCell();
        System.out.println("You are currently at cell (" + currentCell.getOx() + ", " + currentCell.getOy() + ")");
        System.out.println("Available options:");
        System.out.println("1. Go North");
        System.out.println("2. Go South");
        System.out.println("3. Go East");
        System.out.println("4. Go West");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
	}
	public void executeCommand(String command) throws Exception {
        switch (command) {
            case "1":
                gameGrid.goNorth();
                break;
            case "2":
                gameGrid.goSouth();
                break;
            case "3":
                gameGrid.goEast();
                break;
            case "4":
                gameGrid.goWest();
                break;
            case "5":
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
