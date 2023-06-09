package pl.psi.specialFields;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

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


}
