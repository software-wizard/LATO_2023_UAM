package pl.psi.creatures;

import java.util.Random;

public class Spell {

    private SpellStatisticIf stats;

    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;
    }

    public void cast(Creature creature) {
        if (creature.isAlive()) {

            final String Name = this.stats.getName();
            final int ClassOfSpell = this.stats.getClassOfSpell();
            final int Cost = this.stats.getCost();
            final int SpellDamage = this.stats.getSpellDamage();
            final int ArmorChange = this.stats.getArmorChange();
            final int DamageChange = this.stats.getDamageChange();
            final int MoveRangeChange = this.stats.getMoveRangeChange();
            final int SpellProtect = this.stats.getSpellProtection();
            final int Tier = this.stats.getTier();
            final String Description = this.stats.getDescription();

            applySpell(creature, Name, ClassOfSpell, Cost, SpellDamage, ArmorChange, DamageChange, MoveRangeChange, SpellProtect, Tier, Description);
        }
    }

    void applySpell(Creature creature, String name, int classOfSpell, int cost, int spellDamage, int armorChange, int damageChange, int moveRangeChange, int spellProtect, int tier, String description) {
        System.out.println("Using " + name);

        if(spellDamage >0){ //usual damage
            creature.setCurrentHp(creature.getCurrentHp()-creature.getSpellDamageResistance()/100*spellDamage);
        }
        if(spellDamage < 0){ //healing
            creature.setCurrentHp(creature.getCurrentHp()-spellDamage);
        }

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
