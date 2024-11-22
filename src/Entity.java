public abstract class Entity {
	private int currentMana;
	final int maxMana = 100;
	private int currentHealth;
	final int maxHealth = 1000;

	public enum CellEntityType {
		PLAYER,
		VOID,
		ENEMY,
		SANCTUARY,
		PORTAL
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
	// TODO: metoda pentru folosirea de abilitati 
}
