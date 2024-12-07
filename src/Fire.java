public class Fire extends Spell {
    public Fire(int damage, int manaCost) {
        super(damage, manaCost);
		this.name = "Fire";
    }

    @Override
    public void cast(Character caster, Enemy target) {
        if (caster.getCurrentMana() >= manaCost) {
            target.receiveDamage(damage);
            caster.setCurrentMana(caster.getCurrentMana() - manaCost);
            System.out.println(caster.getName() + " casts Fireball on " + "enemy" + " for " + damage + " damage.");
        } else {
            System.out.println(caster.getName() + " does not have enough mana to cast Fireball. Using default attack.");
            caster.defaultAttack(target);
        }
    }
}