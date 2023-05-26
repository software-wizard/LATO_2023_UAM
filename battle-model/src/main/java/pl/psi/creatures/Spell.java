package pl.psi.creatures;

import com.google.common.collect.Range;
import com.google.common.collect.Table;

import java.util.Random;

public class Spell {

    private SpellStatisticIf stats;

    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;
    }

    public void cast(Creature creature) {
        if (creature.isAlive()) {
            applySpell(creature, stats);
        }
    }

    void applySpell(Creature creature, SpellStatisticIf stats) {
        System.out.println("Using " + stats.getName());
        int[] spellResistanceCalc = creature.getStats().getSpellDamageResistance();
        spellResistanceCalc[stats.getClassOfSpell()] += stats.getSpellProtection();

        if (stats.getSpellDamage() > 0){
            creature.setCurrentHp(creature.getCurrentHp() - (int)((float)(100 - spellResistanceCalc[stats.getClassOfSpell()])/100 * stats.getSpellDamage()));
        }
        else {
            if(creature.getCurrentHp()-stats.getSpellDamage() < creature.getStats().getMaxHp()){
                creature.setCurrentHp(creature.getCurrentHp()-stats.getSpellDamage());
            }
            else {
                creature.setCurrentHp(creature.getMaxHp());
            }
        }

        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .name(creature.getStats().getName())
                        .attack(creature.getStats().getAttack())
                        .armor(creature.getStats().getArmor() +
                                (int)((float)(100 - spellResistanceCalc[stats.getClassOfSpell()])/100 * stats.getArmorChange()))
                        .maxHp(creature.getStats().getMaxHp())
                        .moveRange(creature.getStats().getMoveRange() +
                                (int)((float)(100 - spellResistanceCalc[stats.getClassOfSpell()])/100 * stats.getMoveRangeChange()))
                        .damage(Range.closed(creature.getStats().getDamage().lowerEndpoint()+
                                        (int)((float)(100 - spellResistanceCalc[stats.getClassOfSpell()])/100 * stats.getDamageChange()),
                                creature.getStats().getDamage().upperEndpoint()+(
                                        (int)((float)(100 - spellResistanceCalc[stats.getClassOfSpell()])/100 * stats.getDamageChange()))))
                        .tier(creature.getStats().getTier())
                        .spellDamageResistance(spellResistanceCalc)
                        .description(creature.getStats().getDescription())
                        .isUpgraded(creature.getStats().isUpgraded())
                        .build())
                .build();
        creature.updateStats(creature1.getStats());
        System.out.println("The spell has been applied");
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

    public String getName() {
        return this.stats.getName();
    }


}
