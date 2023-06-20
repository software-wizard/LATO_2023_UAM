package pl.psi.specialFields;

import pl.psi.creatures.Spell;
import pl.psi.creatures.SpellStatistic;

public class ObstacleFactory {
    private static final String EXCEPTION_MESSAGE = "Obstacle with given name wasn't found";

    public Obstacle create(final String aName){
        switch(aName){
            case "Tree":
                return new Obstacle(ObstacleStatistic.TREE);
            case "Rock":
                return new Obstacle(ObstacleStatistic.ROCK);
            case "Boulder":
                return new Obstacle(ObstacleStatistic.BOULDER);
            case "Spikes":
                return new PhysicalDamageTrap(new Obstacle(ObstacleStatistic.SPIKES),
                        5,
                        null);
            case "Firewall":
                return new PhysicalDamageTrap(new Obstacle(ObstacleStatistic.FIREWALL),
                        8,
                        new Spell.spellBuilder().statistic(SpellStatistic.FIREBALL).build());
            case "Weakness Totem":
                return new EffectStatusTrap(new Obstacle(ObstacleStatistic.WEAKNESS_TOTEM),
                        new Spell.spellBuilder().statistic(SpellStatistic.WEAKNESS).build());
            case "Healing Totem":
                return new EffectStatusTrap(new Obstacle(ObstacleStatistic.HEALING_TOTEM),
                        new Spell.spellBuilder().statistic(SpellStatistic.CURE).build());
            default:
                throw new IllegalArgumentException( EXCEPTION_MESSAGE );
        }
    }
}
