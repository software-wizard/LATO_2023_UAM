package pl.psi.specialFields;

public class ObstacleFactory {
    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 4";

    public Obstacle create(final int aTier, final int aAmmount){
        switch(aTier){
            case 1:
                return new Obstacle.Builder().statistic(ObstacleStatistic.ROCK)
                        .amount(aAmmount)
                        .build();
            case 2:
                return new Obstacle.Builder().statistic(ObstacleStatistic.TREE)
                        .amount(aAmmount)
                        .build();
            case 3:
                return new Obstacle.Builder().statistic(ObstacleStatistic.BOULDER)
                        .amount(aAmmount)
                        .build();
            case 4:
                return new TransparentObstacle(new Obstacle.Builder().statistic(ObstacleStatistic.SPIKES)
                        .amount(aAmmount)
                        .build());
            default:
                throw new IllegalArgumentException( EXCEPTION_MESSAGE );
        }
    }
}
