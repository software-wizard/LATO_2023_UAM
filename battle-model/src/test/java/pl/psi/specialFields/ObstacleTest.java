package pl.psi.specialFields;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.*;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.Spell;
import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ObstacleTest {
    @Test
    void ObstacleShouldBeDamagedOnce(){
        final Obstacle basicRock = new Obstacle.Builder().statistic(
                ObstacleTestStats.builder()
                        .maxHp(4)   //rock should take 4 hits
                        .build())
                .build();
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
        final Obstacle basicRock = new Obstacle.Builder().statistic(
                        ObstacleTestStats.builder()
                                .maxHp(4)
                                .build())
                .build();

        basicRock.applyDamage(1);
        basicRock.applyDamage(1);
        basicRock.applyDamage(1);
        basicRock.applyDamage(1);

        assertThat(Objects.isNull(basicRock));
        assertThat(basicRock.getAmount()).isEqualTo(0);
    }

    @Test
    void ObstacleIsPresentOnTheMap(){
        final Obstacle basicRock = new Obstacle.Builder().statistic(
                        ObstacleTestStats.builder()
                                .maxHp(4)
                                .build())
                .build();

        final BiMap<Point, Obstacle> obstaclesMap = HashBiMap.create();

        final Point rockPoint = new Point(3,3);
        final Point emptyPoint = new Point(5,5);

        obstaclesMap.put(rockPoint, basicRock);

        final ObstaclePlacementList placementList = new ObstaclePlacementList(obstaclesMap);

        final GameEngine gameEngine = new GameEngine(
                new Hero(Collections.<Creature> emptyList(), Collections.<Spell> emptyList()),
                new Hero(Collections.<Creature> emptyList(), Collections.<Spell> emptyList()),
                placementList);

        assertThat(gameEngine.getObject(rockPoint)).contains(basicRock);
        assertThat(gameEngine.getObject(emptyPoint)).isEmpty();
        assertThat((gameEngine.canMove(rockPoint))).isEqualTo(false);
    }
}
