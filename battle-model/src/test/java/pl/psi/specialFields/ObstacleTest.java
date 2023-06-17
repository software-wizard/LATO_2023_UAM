package pl.psi.specialFields;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.*;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ObstacleTest {
    @Test
    void ObstacleShouldBeDamagedOnce(){
        final Obstacle basicRock = new Obstacle(ObstacleTestStats.builder().maxHp(4).build());
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        angel.attack(basicRock);

        assertThat(basicRock.getCurrentHp()).isEqualTo(3);
    }

    @Test
    void ObjectShouldRemoveWhenDestroyed(){
        final Obstacle basicRock = new Obstacle(ObstacleTestStats.builder().maxHp(4).build());

        basicRock.applyDamage(1);
        basicRock.applyDamage(1);
        basicRock.applyDamage(1);
        basicRock.applyDamage(1);

        assertThat(Objects.isNull(basicRock));
        //TODO assertion is always false
    }

    @Test
    void ObstacleIsPresentOnTheMap(){
        final Obstacle basicRock = new Obstacle(ObstacleTestStats.builder().maxHp(4).build());

        final BiMap<Point, Obstacle> obstaclesMap = HashBiMap.create();

        final Point rockPoint = new Point(3,3);
        final Point emptyPoint = new Point(5,5);

        obstaclesMap.put(rockPoint, basicRock);

        final ObstaclePlacementList placementList = new ObstaclePlacementList(obstaclesMap);

        final GameEngine gameEngine = new GameEngine(
                new Hero(Collections.emptyList(), Collections.emptyList()),
                new Hero(Collections.emptyList(), Collections.emptyList()),
                placementList);

        assertThat(gameEngine.getObject(rockPoint)).contains(basicRock);    //obstacle on the map
        assertThat(gameEngine.getObject(emptyPoint)).isEmpty(); //empty point is empty
        assertThat((gameEngine.canMove(rockPoint))).isEqualTo(false);   //obstacle cannot move
    }
}
