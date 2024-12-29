import java.util.Random;

public abstract class Entity implements Element<Entity>{
	private int currentMana;
	final int maxMana = 1000;
	private int currentHealth;
	final int maxHealth = 100;

	public enum CellEntityType {
		PLAYER,
		VOID,
		ENEMY,
		SANCTUARY,
		PORTAL
	}

	@Override
	public void accept(Visitor<Entity> visitor){
		visitor.visit(this);
	}

	public void setCurrentHealth(int health) {
		this.currentHealth = health;
	}

	public void setCurrentMana(int mana) {
		this.currentMana = mana;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void getDamage(int damage) {
		this.currentHealth -= damage;
	}

	public void useMana(int mana) {
		this.currentMana -= mana;
	}

	public void lifeRegen(int newHealth) {
		int current = Game.currentCharacter.getCurrentHealth();
		if (newHealth + current > maxHealth) {
			Game.currentCharacter.setCurrentHealth(maxHealth);
			return;
		}
		Game.currentCharacter.setCurrentHealth(newHealth + current);
	}

	public void manaRegen(int newMana) {
		int current = Game.currentCharacter.getCurrentMana();
		if (newMana + current > maxHealth) {
			Game.currentCharacter.setCurrentMana(maxMana);
			return;
		}
		Game.currentCharacter.setCurrentMana(newMana + current);
	}

	public void receiveDamage(int damage) {
		Random rd = new Random();
		boolean halfDamage = rd.nextBoolean(); // 1/2
		if (halfDamage) {
			damage /= 2;
		}
		this.currentHealth -= damage;
		if (this.currentHealth < 0) {
			this.currentHealth = 0;
		}
	}

	public abstract int getDamage();

	public abstract void useAbility(CellEntityType abilityType, Character enemy);
	public abstract boolean getEarthImmunity();
    public abstract boolean getFireImmunity();
    public abstract boolean getIceImmunity();
    public abstract void takeDamage(int damage);
}
