public abstract class Spell implements Visitor<Entity> {
	protected int damage;
	protected int manaCost;
	public String name;

	public Spell(int damage, int manaCost) {
		this.damage = damage;
		this.manaCost = manaCost;
	}

	public int getDamage() {
		return damage;
	}

	public int getManaCost() {
		return manaCost;
	}

	@Override
    public void visit(Entity entity) {
        // Logica pentru aplicarea efectului vrăjii asupra entității
        if (this.name.equals("Earth") && entity.getEarthImmunity()) {
            System.out.println("IMMUNITY!");
            this.damage = 0;
        } else if (this.name.equals("Fire") && entity.getFireImmunity()) {
            System.out.println("IMMUNITY!");
            this.damage = 0;
        } else if (this.name.equals("Ice") && entity.getIceImmunity()) {
            System.out.println("IMMUNITY!");
            this.damage = 0;
        }
        // Aplicați daunele entității
        entity.takeDamage(this.damage);
    }

	@Override
	public String toString() {
		return name + " {" +
				"damage=" + damage +
				", manaCost=" + manaCost +
				'}';
	}

	public abstract void cast(Character caster, Enemy target);

	public abstract void cast(Enemy caster, Character target);
}
