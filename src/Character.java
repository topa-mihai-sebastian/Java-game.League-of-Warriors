public class Character extends Entity{
	public String name;
	private int XP;
	private int level;

	private int Strength;
	private int Charisma;
	private int Dexterity;

	public Character() {
		this.name = "noName";
		this.XP = 0;
		this.level = 0;
		this.Strength = 0;
		this.Charisma = 0;
		this.Dexterity = 0;
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
}
