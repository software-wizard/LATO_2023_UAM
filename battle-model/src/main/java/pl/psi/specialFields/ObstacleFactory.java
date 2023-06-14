package pl.psi.specialFields;

public class ObstacleFactory {
    private static final String EXCEPTION_MESSAGE = "Obstacle with given name wasn't found";

    public Obstacle create(final String aName, final int aAmmount){
        switch(aName){
            case "Rock":
                return new Obstacle.Builder().statistic(ObstacleStatistic.ROCK)
                        .amount(aAmmount)
                        .build();
            case "Tree":
                return new Obstacle.Builder().statistic(ObstacleStatistic.TREE)
                        .amount(aAmmount)
                        .build();
            case "Boulder":
                return new Obstacle.Builder().statistic(ObstacleStatistic.BOULDER)
                        .amount(aAmmount)
                        .build();
            case "Spikes":
                return new TransparentObstacle(new Obstacle.Builder().statistic(ObstacleStatistic.SPIKES)
                        .amount(aAmmount)
                        .build());
            default:
                throw new IllegalArgumentException( EXCEPTION_MESSAGE );
        }
    }
}
