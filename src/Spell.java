public abstract class Spell {
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
    public String toString() {
        return name + " {" +
                "damage=" + damage +
                ", manaCost=" + manaCost +
                '}';
    }
	public abstract void cast(Character caster, Enemy target);
	public abstract void cast(Enemy caster, Character target);
}
