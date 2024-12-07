public class Fire extends Spell {
    public Fire(int damage, int manaCost) {
        super(damage, manaCost);
    }

    @Override
    public void cast(Character caster, Character target) {
        if (caster.getCurrentMana() >= manaCost) {
            target.receiveDamage(damage);
            caster.setCurrentMana(caster.getCurrentMana() - manaCost);
            System.out.println(caster.getName() + " casts Fireball on " + target.getName() + " for " + damage + " damage.");
        } else {
            System.out.println(caster.getName() + " does not have enough mana to cast Fireball. Using default attack.");
            caster.defaultAttack(target);
        }
    }
}