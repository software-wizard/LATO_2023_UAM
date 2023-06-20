package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.*;
import pl.psi.specialFields.EffectStatusTrap;
import pl.psi.specialFields.Obstacle;
import pl.psi.specialFields.ObstacleTestStats;
import pl.psi.specialFields.PhysicalDamageTrap;

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
        final PhysicalDamageTrap spikes = new PhysicalDamageTrap(
                new Obstacle(ObstacleTestStats.builder()
                        .maxHp(2)
                        .build()), 0, null);
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(20)
                        .build())
                .build();

        final Point destination = new Point(3,2);

        final List<Creature> creatures = new ArrayList<>();
        creatures.add(angel);
        final BiMap<Point, Obstacle> obstacles = HashBiMap.create();
        obstacles.put(destination, spikes);

        final Board board = new Board(creatures, Collections.emptyList(), obstacles);
        assertThat(board.getObject(destination)).contains(spikes);  //spikes are on the board

        board.move(angel, destination); //moves onto the spikes, stomps on them
        assertThat(board.getObject(destination)).contains(angel);

        board.move(angel, new Point(20,2)); //moves elsewhere second time
        assertThat(board.getObject(destination)).contains(spikes);  //spikes are back on the board

    }

    @Test
    void ShouldDamageWhenTranspassed() {
        final PhysicalDamageTrap spikes = new PhysicalDamageTrap(
                new Obstacle(ObstacleTestStats.builder()
                        .maxHp(2)
                        .build()), 5, null);
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .moveRange(20)
                        .build())
                .build();

        final Point destination = new Point(3,2);

        final List<Creature> creatures = new ArrayList<>();
        creatures.add(angel);
        final BiMap<Point, Obstacle> obstacles = HashBiMap.create();
        obstacles.put(destination, spikes);

        final Board board = new Board(creatures, Collections.emptyList(), obstacles);
        assertThat(board.getObject(destination)).contains(spikes);  //spikes are on the board

        board.move(angel, destination); //moves onto the spikes, stomps on them
        assertThat(board.getObject(destination)).contains(angel);

        board.performOnTouch(destination);  //angel should be damaged because it stands on the spikes

        assertThat(angel.getCurrentHp()).isEqualTo(95); //spikes does 5 dmg on hit
    }

    @Test
    void ShouldApplyEffectOnTouch() {
        final EffectStatusTrap healing_totem = new EffectStatusTrap(
                new Obstacle(ObstacleTestStats.builder()
                        .maxHp(100)
                        .build()),
                        new Spell.spellBuilder().statistic(SpellStatistic.CURE).build());


        final Creature sceleton = new NecropolisFactory().create( false, 1, 5 );

        final Point destination = new Point(3,2);

        final List<Creature> creatures = new ArrayList<>();
        creatures.add(sceleton);
        final BiMap<Point, Obstacle> obstacles = HashBiMap.create();
        obstacles.put(destination, healing_totem);

        final Board board = new Board(creatures, Collections.emptyList(), obstacles);

        board.move(sceleton, destination); //moves onto the spikes, stomps on them
        assertThat(board.getObject(destination)).contains(sceleton);

        sceleton.applyDamage(2);
        assertThat(sceleton.getCurrentHp()).isEqualTo(4); //deal 2 damage to the creature

        board.performOnTouch(destination);  //angel should be healed because it stands on the spikes

        assertThat(sceleton.getCurrentHp()).isEqualTo(6); //angel should be healed back
    }
}