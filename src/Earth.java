public class Earth extends Spell {
    public Earth(int damage, int manaCost) {
        super(damage, manaCost);
		this.name = "Earth";
    }

    @Override
    public void cast(Character caster, Enemy target) {
        if (caster.getCurrentMana() >= manaCost) {
            target.receiveDamage(damage);
            caster.setCurrentMana(caster.getCurrentMana() - manaCost);
            System.out.println(caster.getName() + " casts Earth on " + "enemy" + " for " + damage + " damage.");
        } else {
            System.out.println(caster.getName() + " does not have enough mana to cast Earth. Using default attack.");
            caster.defaultAttack(Game.currentEnemy);
        }
    }
}