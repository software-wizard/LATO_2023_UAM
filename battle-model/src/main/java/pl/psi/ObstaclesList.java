package pl.psi;

import lombok.Getter;
import pl.psi.specialFields.Obstacle;

import java.util.List;

public class ObstaclesList {
    @Getter
    private final List<Obstacle> obstacles;

    public ObstaclesList(final List<Obstacle> aObstacles )
    {
        obstacles = aObstacles;
    }
}
