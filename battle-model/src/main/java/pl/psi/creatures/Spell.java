package pl.psi.creatures;

import java.util.Random;

public class Spell {

    private SpellStatisticIf stats;

    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;
    }

    void cast(Creature creature) {
        if (creature.isAlive()) {
            final int damage = this.stats.getDamage();
            applySpell(creature, damage);
        }
    }

    void applySpell(Creature creature, int damage) {
        creature.setCurrentHp(creature.getCurrentHp()-damage);
    }

    public static class spellBuilder {
        private SpellStatisticIf statistic;
        public spellBuilder statistic(final SpellStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }
        public Spell build() {
            return new Spell(statistic);
        }
    }


}
