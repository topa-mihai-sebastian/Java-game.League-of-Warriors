public class Ice extends Spell {
    public Ice(int damage, int manaCost) {
        super(damage, manaCost);
    }

    @Override
    public void cast(Character caster, Character target) {
        if (caster.getCurrentMana() >= manaCost) {
            target.receiveDamage(damage);
            caster.setCurrentMana(caster.getCurrentMana() - manaCost);
            System.out.println(caster.getName() + " casts Ice on " + target.getName() + " for " + damage + " damage.");
        } else {
            System.out.println(caster.getName() + " does not have enough mana to cast Ice. Using default attack.");
            caster.defaultAttack(target);
        }
    }
}