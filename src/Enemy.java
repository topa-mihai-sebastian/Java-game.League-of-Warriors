import java.util.Random;

public class Enemy extends Entity {
    private int Strength;
    private int Charisma;
    private int Dexterity;

    private int fireImmunity;
    private int iceImmunity;
    private int earthImmunity;

    private String[] abilities;

    // Constructor pentru inițializarea câmpurilor
    public Enemy() {
        Random rd = new Random();

        // Setează viața și mana cu valori aleatoare
        setCurrentHealth(rd.nextInt(501) + 500); // Interval: 500 - 1000
        setCurrentHealth(rd.nextInt(51) + 50); // Interval: 50 - 100

        // Setează damage-ul de bază cu o valoare aleatoare
        this.Strength = rd.nextInt(21) + 10; // Interval: 10 - 30
		this.Dexterity = rd.nextInt(21) + 10; // Interval: 10 - 30
		this.Charisma = rd.nextInt(21) + 10; // Interval: 10 - 30

        // Setează imunitățile cu valori aleatoare
        this.fireImmunity = rd.nextInt(101); // Interval: 0 - 100
        this.iceImmunity = rd.nextInt(101); // Interval: 0 - 100
        this.earthImmunity = rd.nextInt(101); // Interval: 0 - 100

        // Instantiază abilitățile aleator
        int numAbilities = rd.nextInt(4) + 3; // Interval: 3 - 6
        this.abilities = new String[numAbilities];
        String[] possibleAbilities = {"Fireball", "Ice Spike", "Earthquake"};
        for (int i = 0; i < numAbilities; i++) {
            this.abilities[i] = possibleAbilities[rd.nextInt(possibleAbilities.length)];
        }
    }

    @Override
    public void receiveDamage(int damage) {
        Random rd = new Random();
        boolean avoidDamage = rd.nextBoolean(); // 50% șansă

        if (avoidDamage) {
            System.out.println("Enemy avoided the damage!");
            return;
        }

        setCurrentHealth(getCurrentHealth() - damage);

        if (getCurrentHealth() < 0) {
            setCurrentHealth(0); 
        }
    }

    @Override
    public int getDamage() {
        Random rd = new Random();
        boolean doubleDamage = rd.nextBoolean(); // 50% șansă
        int baseDamage = this.Strength;

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

    public String[] getAbilities() {
        return abilities;
    }
}
