import java.util.Random;

public class Character extends Entity {
	public String name;
	public int XP;
	public int level;
	public String profession;
	public int Strength;
	public int Charisma;
	public int Dexterity;

	public Character() {
		this.name = "noName";
		this.XP = 0;
		this.level = 0;
		this.Strength = 0;
		this.Charisma = 0;
		this.Dexterity = 0;
	}

	public Character(String name, Integer XP, int lvl) {
		this.name = name;
		this.XP = XP;
		this.level = lvl;
	}

	public Character(String name, String profession, int level, int experience) {
		this.name = name;
		this.profession = profession;
		this.level = level;
		this.XP = experience;
	}

	public Character(String name, int XP, int level, int Strength, int Charisma, int Dexterity) {
		this.name = name;
		this.XP = XP;
		this.level = level;
		this.Strength = Strength;
		this.Charisma = Charisma;
		this.Dexterity = Dexterity;
	}

	public int calculateLoseHealth(int damageDealt) {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		String profession = Game.currentCharacter.getProfession();

		// Warrior
		if (profession.equals("Warrior")) {
			if (getCharisma() >= 50 && halfChance) { // wombo combo
				damageDealt *= 0.5;
			}
			if (getCharisma() >= 50) {
				damageDealt *= 0.7;
			}
			if (getCharisma() >= 25 && getCharisma() <= 50) {
				damageDealt *= 0.85;
			}
		}

		// Mage
		if (profession.equals("Mage")) {
			if (getStrength() >= 50 && halfChance) { // wombo combo
				damageDealt *= 0.5;
			}
			if (getStrength() >= 50 && getDexterity() >= 35) {
				damageDealt *= 0.7;
			}
			if (getStrength() >= 25 && getDexterity() <= 50) {
				damageDealt *= 0.85;
			}
		}

		// Rogue
		if (profession.equals("Rogue")) {
			if (getCharisma() >= 50 && halfChance) { // wombo combo
				damageDealt *= 0.5;
			}
			if (getStrength() >= 50 && getDexterity() >= 35) {
				damageDealt *= 0.7;
			}
			if (getStrength() >= 25 && getDexterity() <= 50) {
				damageDealt *= 0.85;
			}
		}
		return damageDealt;
	}

	public int calculateDefaultDamage() {
		Random rd = new Random();
		boolean halfChance = rd.nextBoolean();
		String profession = Game.currentCharacter.getProfession();
		double damageDealt = Strength + Charisma * 0.3 + Dexterity * 0.2;
		if (getCharisma() >= 50 && halfChance) { // wombo combo
			damageDealt *= 1.75;
		}
		if (getCharisma() >= 50) {
			damageDealt *= 1.2;
		}
		if (getCharisma() >= 25 && getCharisma() <= 50) {
			damageDealt *= 1.1;
		}
		return (int) damageDealt;
	}

	public String getName() {
		return name;
	}

	public int getXP() {
		return XP;
	}

	public int getLevel() {
		return level;
	}

	public int getStrength() {
		return Strength;
	}

	public int getCharisma() {
		return Charisma;
	}

	public int getDexterity() {
		return Dexterity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setXP(int newXP) {
		this.XP = newXP;
	}

	public void setCharisma(int Charisma) {
		this.Charisma = Charisma;
	}

	public void setStrength(int Strength) {
		this.Strength = Strength;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setDexterity(int Dexterity) {
		this.Dexterity = Dexterity;
	}

	@Override
	public int getDamage() {
		Random rd = new Random();
		boolean doubleDamage = rd.nextBoolean(); // 1/2
		int baseDamage = Strength * 2; // formula de calculare a damage-ului de baza
		if (doubleDamage) {
			baseDamage = baseDamage * 2;
		}
		return baseDamage;
	}

	@Override
	public void useAbility(CellEntityType abilityType, Character enemy) {

	}
}
