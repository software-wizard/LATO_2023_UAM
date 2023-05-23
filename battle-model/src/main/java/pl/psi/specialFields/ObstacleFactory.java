package pl.psi.specialFields;

public class ObstacleFactory {
    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 2";

    public Obstacle create(final int aTier, final int aAmmount){
        switch(aTier){
            case 1:
                return new Obstacle.Builder().statistic(ObstacleStatistic.ROCK)
                        .amount(aAmmount)
                        .build();
            case 2:
                return new Obstacle.Builder().statistic(ObstacleStatistic.BOULDER)
                        .amount(aAmmount)
                        .build();
            default:
                throw new IllegalArgumentException( EXCEPTION_MESSAGE );
        }
    }
}
