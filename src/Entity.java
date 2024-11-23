import java.util.Random;

public abstract class Entity {
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
		if(newHealth > maxHealth) {
			return;
		}
		this.currentHealth = newHealth;
	}
	
	public void manaRegen(int newMana) {
		if(newMana > maxMana) {
			return;
		}
		this.currentMana = newMana;
	}
	
	public void receiveDamage(int damage) {
		Random rd = new Random();
		boolean halfDamage = rd.nextBoolean(); // 1/2
		if(halfDamage) {
			damage /= 2;
		}
		this.currentHealth -= damage;
		if(this.currentHealth < 0) {
			this.currentHealth = 0;
		}
	}
	public abstract int getDamage();
	public abstract void useAbility(CellEntityType abilityType, Character enemy);

	// TODO: metoda pentru folosirea de abilitati 
}
