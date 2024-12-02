import java.util.Random;

public class Character extends Entity{
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

	public void setDexterity(int Dexterity) {
        this.Dexterity = Dexterity;
    }

	@Override
	public int getDamage() {
		Random rd = new Random();
		boolean doubleDamage = rd.nextBoolean(); // 1/2
		int baseDamage = Strength * 2; // formula de calculare a damage-ului de baza
		if(doubleDamage) {
			baseDamage = baseDamage * 2;
		}
		return baseDamage;
	}
	@Override
	public void useAbility(CellEntityType abilityType, Character enemy) {
		
	}
}
