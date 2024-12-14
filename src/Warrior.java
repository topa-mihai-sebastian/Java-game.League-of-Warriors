import java.util.Random;

public class Warrior extends Character {
	public Warrior(String name, int XP, int level, int Strength, int Charisma, int Dexterity) {
		super(name, XP, level, Strength, Charisma, Dexterity);
		super.setCurrentHealth(super.maxHealth);
		this.profession = "Warrior";
		Random rd = new Random();
		this.fireImmunity = true;
		this.iceImmunity = false;
		this.earthImmunity = false;
	}
	// constructor ca in test
	// public Warrior(String name, Integer experience, int lvl) {
	// super(name, experience, lvl);
	// }

	public int calculateLoseHealth(int damageDealt) {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		if (getDexterity() >= 50) {
			if (halfChance) {
				damageDealt = 0;
			}
		}
		halfChance = rd.nextBoolean();
		if (getCharisma() >= 50) {
			if (halfChance) {
				damageDealt *= 0.9;
			}
		}
		return damageDealt;
	}

	public int calculateDefaultDamage(int Strength) {
		return 3 * Strength; // atacul de baza pt warrior este de 3 ori cat Strength
	}
}
