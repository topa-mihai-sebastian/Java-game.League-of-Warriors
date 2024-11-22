import java.util.Random;

public class Mage extends Character{
	public Mage(String name, int XP, int level, int Strength, int Charisma, int Dexterity) {
        super(name, XP, level, Strength, Charisma, Dexterity);
    }

	public int calculateLoseHealth(int damageDealt) {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		if(getCharisma() >= 50 && halfChance) { // wombo combo
			damageDealt *= 0.5;
		}
		if(getCharisma() >= 50) {
			damageDealt *= 0.7;
		}
		if(getCharisma() >= 25) {
			damageDealt *= 0.85;
		}
		return damageDealt;
	}
	public int calculateDefaultDamage(int Charisma) {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		int damageDealt = Charisma;
		if(getCharisma() >= 50 && halfChance) { // wombo combo
			damageDealt *= 1.75;
		}
		if(getCharisma() >= 50) {
			damageDealt *= 1.2;
		}
		if(getCharisma() >= 25) {
			damageDealt *= 1.1;
		}
		return damageDealt;
	}
}
