import java.util.ArrayList;
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

	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			gameGrid.printGrid();
			if(Grid.printable == true) {
				printOptions();
			}
			String command = scanner.nextLine();
			try {
				executeCommand(command);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if(currentEnemy != null && currentEnemy.getCurrentHealth() <= 0) {
				System.out.println("Enemy defeated!!");
				currentEnemy = null;
			}
			if(myWarrior.getCurrentHealth() <= 0) {
				System.out.println("YOU LOST!");
				System.out.println("YOU HAVE NO HEALTH LEFT");
				break;
			}
		}
		scanner.close();
	}

	public void printOptions(){
		Cell currentCell = gameGrid.getCurrentCell();
        System.out.println("You are currently at cell [" + currentCell.getOx() + ", " + currentCell.getOy() + "]");
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
