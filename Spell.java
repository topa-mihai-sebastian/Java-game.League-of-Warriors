public abstract class Spell {
	protected int damage;
    protected int manaCost;

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
    public String toString() {
        return "Spell{" +
                "damage=" + damage +
                ", manaCost=" + manaCost +
                '}';
    }
	public abstract void cast(Character caster, Character target);
}
