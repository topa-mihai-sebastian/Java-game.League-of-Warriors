import java.util.Random;

public class Rogue extends Character {
	public Rogue(String name, int XP, int level, int Strength, int Charisma, int Dexterity) {
        super(name, XP, level, Strength, Charisma, Dexterity);
		super.setCurrentHealth(super.maxHealth);
		this.profession = "Rogue";
    }

	public Rogue(String name, Integer experience, int lvl) {
		super(name, experience, lvl);
	}

	public int calculateLoseHealth(int damageDealt) {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		
		if(getCharisma() >= 50) {
			if(halfChance) {
				damageDealt *= 0.7; // inmultesc pentru a ramane scalabil
			}
		}
		if(getStrength() <= 30) {
			damageDealt *= 1.2;
		}
		if(getStrength() <= 20) {
			damageDealt *= 1.5;
		}
		return damageDealt;
	}

	public int calculateDefaultDamage(int Dexterity) {
		int damage = getStrength();
		if(Dexterity >= 50) {
			damage *= 1.1;
		}
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		if(Dexterity <= 25) {
			if(halfChance) {
				damage *= 0.85;
			}
		}
		if(Dexterity <= 20) {
			if(halfChance) {
				damage *= 0.75;
			}
		}
		return damage;
	}
}
