import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public ArrayList<Account> accountList;
	public Grid gameGrid;

	public Game() {
		Random rd = new Random();
		int randomWidth = rd.nextInt(7) + 4;
		int randomHeight = rd.nextInt(7) + 4;
		this.gameGrid = Grid.createTheGrid(randomWidth, randomHeight);
	}

	public void run(){
		Scanner scanner = new Scanner(System.in);
		while(true) {
			gameGrid.printGrid();
			if(Grid.printable == false) {
				System.out.println("INAMIC BA FUUUGI!");
			}
			if(Grid.printable == true) {
				printOptions();
			}
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
