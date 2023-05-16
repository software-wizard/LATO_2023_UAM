package pl.psi;

import java.util.*;

import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.creatures.Creature;


/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    @Test
    void shoudWorksHeHe()
    {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
            new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );

        gameEngine.attack( new Point( 1, 1 ) );
    }

    @Test
    void shouldGenerateNeighboursGivenPoint(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                        new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );

        List<Node> actual = gameEngine.generateNeigboursList(0, 0);
        List<Node> expected = Arrays.asList(new Node(0, 1),
                new Node(1, 0));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    void shouldCalculateHeuristicGivenCurrentAndDestinationNode(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                        new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );
        int actual = gameEngine.calculateHeuristic(new Node(4,10),new Node(2,2));
        int expected = 2 + 8;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldMoveToAGivenPoint(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                            new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );
        Optional<Creature> creature = gameEngine.getCreature(new Point(0, 1));
        gameEngine.move(new Point(2, 3));
        Optional<Creature> creature_expected = gameEngine.getCreature(new Point(2, 3));
        Assertions.assertEquals(creature, creature_expected);
    }

    @Test
    void shouldGenerateActualMovesList(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ),
                        new Hero( List.of( creatureFactory.create( 1, false, 5 ) ) ) );
        Optional<Creature> creature = gameEngine.getCreature(new Point(0, 0));
        Node start = new Node(0,0);
        Node goal = new Node(2,0);
        List<Node> actualMovesList = new ArrayList<>(Arrays.asList(new Node(0,0), new Node(1,0),
                new Node(2,0), new Node(2,1)));
        Map<Point, Point> Obstacles = Collections.emptyMap();
        List<Node> expectedMovesList = gameEngine.generateMovesList(start, goal, Obstacles);
        System.out.println(expectedMovesList);
        expectedMovesList.forEach(e -> System.out.println(e.getCost()));
        Assertions.assertIterableEquals(actualMovesList, expectedMovesList);
    }

}
