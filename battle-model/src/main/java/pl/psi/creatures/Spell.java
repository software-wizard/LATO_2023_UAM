package pl.psi.creatures;

import com.google.common.collect.Range;
import pl.psi.Defendable;

public class Spell {

    private SpellStatisticIf stats;

    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;
    }

    public void cast(Defendable defender) {
        if (defender.isAlive()) {
            applySpell(defender, stats);
        }
    }

    void applySpell(Defendable defender, SpellStatisticIf stats) {
        if (defender.getSpellDamageProtection() != null){
            System.out.println("\nUsing " + stats.getName());

            if (stats.getSpellDamage() > 0){
                defender.setCurrentHp(defender.getCurrentHp() - (int)((float)(100 - defender.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getSpellDamage()));
            }
            else {
                if(defender.getCurrentHp()-stats.getSpellDamage() < defender.getStats().getMaxHp()){
                    defender.setCurrentHp(defender.getCurrentHp()-stats.getSpellDamage());
                }
                else {
                    defender.setCurrentHp(defender.getMaxHp());
                }
            }

            int[] spellProtectionCalc = {0,0,0,0};
            spellProtectionCalc[stats.getClassOfSpell()] += stats.getSpellProtectionChange();
            SpellProtection spellProtection1 = new SpellProtection.spellProtectionBuilder()
                    .airProtection(spellProtectionCalc[0])
                    .fireProtection(spellProtectionCalc[1])
                    .earthProtection(spellProtectionCalc[2])
                    .waterProtection(spellProtectionCalc[3])
                    .build();

            final CreatureStatisticIf creature1stats = CreatureStats.builder()
                    .armor((int)((float)(100 - defender.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getArmorChange()))
                    .moveRange((int)((float)(100 - defender.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getMoveRangeChange()))
                    .damage(Range.closed((int)((float)(100 - defender.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getDamageChange()),
                            ((int)((float)(100 - defender.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getDamageChange()))))
                    .spellDamageProtection(spellProtection1)
                    .build();

            defender.updateStats(creature1stats);
            System.out.println("The spell has been applied\n");
        }
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
