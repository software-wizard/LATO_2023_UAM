package pl.psi.creatures;

public class Spell {

    void cast(Creature creature) {
        if (creature.isAlive()) {
            final int damage = 0;
            applySpell(creature, damage);
        }
    }
    void applySpell(Creature creature, int damage) {
        creature.setCurrentHp(damage);
    }
}
