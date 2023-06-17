package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialFields.Obstacle;
import pl.psi.specialFields.ObstacleTestStats;
import pl.psi.specialFields.TransparentObstacle;

class BoardTest
{
    @Test
    @Disabled
    void unitsMoveProperly()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
            .moveRange( 5 )
            .build() )
            .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2, null );

        board.move( creature, new Point( 3, 3 ) );

        assertThat( board.getObject( new Point( 3, 3 ) )
            .isPresent() ).isTrue();
    }
    @Test
    void TransparentObstacleShouldBeOverriddenWhenTranspassed(){
        final TransparentObstacle spikes = new TransparentObstacle(
                new Obstacle(ObstacleTestStats.builder()
                        .maxHp(2)
                        .build()));
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(20)
                        .build())
                .build();

        final Point destination = new Point(3,2);

        final List<Creature> creatures = new ArrayList<>();
        creatures.add(angel);
        final Map<Point, Obstacle> obstacles = new HashMap<>();
        obstacles.put(destination, spikes);

        final Board board = new Board(creatures, Collections.emptyList(), obstacles);
        assertThat(board.getObject(destination)).contains(spikes);  //spikes are on the board

        board.move(angel, destination); //moves onto the spikes, stomps on them
        assertThat(board.getObject(destination)).contains(angel);

        board.move(angel, new Point(20,2)); //moves elsewhere second time
        assertThat(board.getObject(destination)).contains(spikes);  //spikes are back on the board

    }
}