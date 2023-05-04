package pl.psi;

import lombok.Getter;
import pl.psi.specialFields.FreeStandingObject;

import java.util.List;

public class Obstacles {
    @Getter
    private final List<FreeStandingObject> obstacles;

    public Obstacles(final List< FreeStandingObject > aObstacles )
    {
        obstacles = aObstacles;
    }
}
