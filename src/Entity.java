public class Entity {
	int currentMana;
	final int maxMana = 100;
	int currentHealth;
	final int maxHealth = 1000;

	public enum CellEntityType {
		PLAYER,
		VOID,
		ENEMY,
		SANCTUARY,
		PORTAL
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
}
