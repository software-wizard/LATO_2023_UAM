package pl.psi.creatures;

import com.google.common.collect.Range;
import pl.psi.Point;
import pl.psi.specialFields.Obstacle;
import pl.psi.specialFields.ObstacleStatistic;

import java.util.Map;
import java.util.function.Consumer;
public class Spell {
    private SpellStatisticIf stats;
    private Obstacle obstacle = new Obstacle(ObstacleStatistic.TREE);
    public Obstacle getObstacle() {
        if (stats.getClassOfSpell() == 0){
            obstacle = new Obstacle(ObstacleStatistic.TREE);
        }
        if (stats.getClassOfSpell() == 1){
            obstacle = new Obstacle(ObstacleStatistic.SPIKES);
        }
        if (stats.getClassOfSpell() == 2){
            obstacle = new Obstacle(ObstacleStatistic.BOULDER);
        }
        if (stats.getClassOfSpell() == 3){
            obstacle = new Obstacle(ObstacleStatistic.ROCK);
        }
        return obstacle;
    }

    public SpellStatisticIf getStats() {
        return stats;
    }


    private Spell(final SpellStatisticIf aStats) {
        stats = aStats;
    }
    public void cast(Creature creature) {
        if (creature.isAlive()) {
            applySpell(creature, stats);
        }
    }
    void applySpell(Creature creature, SpellStatisticIf stats) {
        System.out.println("\nUsing " + stats.getName());
        if (stats.getSpellDamage() > 0){
            creature.setCurrentHp(creature.getCurrentHp() - (int)((float)(100 - creature.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getSpellDamage()));
        }
        else {
            if(creature.getCurrentHp()-stats.getSpellDamage() < creature.getStats().getMaxHp()){
                creature.setCurrentHp(creature.getCurrentHp()-stats.getSpellDamage());
            }
            else {
                creature.setCurrentHp(creature.getMaxHp());
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
                .armor((int)((float)(100 - creature.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getArmorChange()))
                .moveRange((int)((float)(100 - creature.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getMoveRangeChange()))
                .damage(Range.closed((int)((float)(100 - creature.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getDamageChange()),
                        ((int)((float)(100 - creature.getSpellDamageProtection().getProtectionByClass(stats.getClassOfSpell()))/100 * stats.getDamageChange()))))
                .spellDamageProtection(spellProtection1)
                .build();
        creature.updateStats(creature1stats);
        System.out.println("The spell has been applied\n");
    }
    public void castObstacle(Consumer<Map<Point, Obstacle>> addObstacleByPoint, Map<Point, Obstacle> aObstacles) {
        addObstacleByPoint.accept(aObstacles);
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