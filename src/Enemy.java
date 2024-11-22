import java.util.Random;

public class Enemy extends Entity{
	private int Strength;
	private int Charisma;
	private int Dexterity;
	public Enemy() {
		Random rd = new Random();
		setCurrentHealth(rd.nextInt(maxHealth));
		setCurrentMana(rd.nextInt(maxMana));
	}
}
