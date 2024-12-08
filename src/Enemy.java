import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity {
    private int Strength;
    private int Charisma;
    private int Dexterity;

    private int fireImmunity;
    private int iceImmunity;
    private int earthImmunity;
	private int currentMana;
    private ArrayList<Spell> spells;

    // Constructor pentru inițializarea câmpurilor
    public Enemy() {
        Random rd = new Random();

        // Setează viața și mana cu valori aleatoare
        setCurrentHealth(rd.nextInt(51) + 50); // Interval: 50 - 100

        // Setează damage-ul de bază cu o valoare aleatoare
        this.Strength = rd.nextInt(21) + 10; // Interval: 10 - 30
		this.Dexterity = rd.nextInt(21) + 10; // Interval: 10 - 30
		this.Charisma = rd.nextInt(21) + 10; // Interval: 10 - 30

        // Setează imunitățile cu valori aleatoare
        this.fireImmunity = rd.nextInt(101); // Interval: 0 - 100
        this.iceImmunity = rd.nextInt(101); // Interval: 0 - 100
        this.earthImmunity = rd.nextInt(101); // Interval: 0 - 100
		this.currentMana = 100 + rd.nextInt(100);
        // Instantiază abilitățile aleator
		this.spells= Game.generateRandomSpells();
    }

	public void defaultAttack(Character target) {
		int defaultDamage = getDamage();
		target.receiveDamage(defaultDamage);
		System.out.println(Game.currentCharacter.profession + " attacks " + "enemy" + " for " + defaultDamage + " damage.");
	}

    @Override
    public void receiveDamage(int damage) {
        Random rd = new Random();
        double chance = rd.nextDouble(); // Returnează un număr între 0.0 și 1.0
		boolean avoidDamage = chance < 0.1; // 10% șansă de a evita daunele
        if (avoidDamage) {
            System.out.println("Enemy avoided the damage!");
            return;
        }

        setCurrentHealth(getCurrentHealth() - damage);

        if (getCurrentHealth() < 0) {
            setCurrentHealth(0); 
        }
    }

	public ArrayList<Spell> getSpells() {
		return spells;
	}

    @Override
    public int getDamage() {
        Random rd = new Random();
        double chance = rd.nextDouble();
        int baseDamage = this.Strength;
		boolean doubleDamage = chance < 0.1; //10% sansa
        if (doubleDamage) {
            baseDamage *= 2;
        }

        return baseDamage;
    }

    @Override
    public void useAbility(CellEntityType abilityType, Character enemy) {
        // Implementarea specifică pentru Enemy
        // Exemplu: reduce health-ul inamicului cu un anumit damage
        int abilityDamage = 20; // Exemplu de damage adăugat de abilitate
        enemy.receiveDamage(abilityDamage);
    }

    // Getteri pentru imunități și abilități (opțional)
    public int getFireImmunity() {
        return fireImmunity;
    }

    public int getIceImmunity() {
        return iceImmunity;
    }

    public int getEarthImmunity() {
        return earthImmunity;
    }
}
