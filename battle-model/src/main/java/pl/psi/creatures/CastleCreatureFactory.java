package pl.psi.creatures;


public class CastleCreatureFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";
    public Creature create( final int aTier, final boolean aIsUpgraded, final int aAmount )
    {
        if( aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.PIKEMAN )
                        .amount( aAmount )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.ARCHER )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.GRIFFIN )
                            .amount( aAmount )
                            .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.SWORDSMAN )
                            .amount( aAmount )
                            .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.MONK )
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.CAVALIER )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.ANGEL )
                            .amount( aAmount )
                            .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.HALBERDIER )
                        .amount( aAmount )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.MARKSMAN )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.ROYAL_GRIFFIN )
                            .amount( aAmount )
                            .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.CRUSADER )
                            .amount( aAmount )
                            .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.ZEALOT )
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.CHAMPION )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.ARCHANGEL )
                            .amount( aAmount )
                            .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        throw new IllegalArgumentException( "Cannot recognize creature by tier and upgrade or not." );
    }
}
