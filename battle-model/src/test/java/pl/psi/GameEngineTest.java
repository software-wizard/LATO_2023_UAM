package pl.psi;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.creatures.BastionCreatureFactory;
import pl.psi.creatures.Creature;
import pl.psi.specialFields.Obstacle;
import pl.psi.specialFields.ObstacleFactory;


/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest {
    @Test
    void shoudWorksHeHe() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()));

        gameEngine.attack(new Point(1, 1));
    }

    @Test
    void bastionCreatureFactoryTest(){
        final BastionCreatureFactory creatureFactory = new BastionCreatureFactory();
            final GameEngine gameEngine =
                    new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()),
                            new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()));
            gameEngine.attack(new Point(1, 1));
    }

    @Test
    void shouldGenerateNeighboursGivenPoint() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()));

        List<Node> actual = gameEngine.generateNeigboursList(0, 0);
        List<Node> expected = Arrays.asList(new Node(0, 1),
                new Node(1, 0));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    void shouldCalculateHeuristicGivenCurrentAndDestinationNode() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), Collections.emptyList()));
        int actual = gameEngine.calculateHeuristic(new Node(4, 10), new Node(2, 2));
        int expected = 2 + 8;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @Disabled
    void shouldMoveToAGivenPoint(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        HashMap<Point, Obstacle> aObstaclePlacement = new HashMap<>();
        ObstaclePlacementList obstaclePlacementList =  new ObstaclePlacementList(aObstaclePlacement);

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),obstaclePlacementList);
        Optional<Creature> creature = gameEngine.getCreature(new Point(0, 1));

        gameEngine.move(new Point(2, 3));
        Optional<Creature> creature_expected = gameEngine.getCreature(new Point(2, 3));
        Assertions.assertEquals(creature, creature_expected);
    }

    @Test
    void shouldGenerateActualMovesList() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        HashMap<Point, Obstacle> aObstaclePlacement = new HashMap<>();
        aObstaclePlacement.put(new Point(2,0),new ObstacleFactory().create(1,1) );
        aObstaclePlacement.put(new Point(3,1),new ObstacleFactory().create(1,1) );
        ObstaclePlacementList obstaclePlacementList =  new ObstaclePlacementList(aObstaclePlacement);

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),obstaclePlacementList);

//        Optional<Creature> creature = gameEngine.getCreature(new Point(0, 0));
        Node start = new Node(0, 0);
        Node goal = new Node(4, 0);

        Map<Point, Integer> obstacles = new HashMap<>();


        List<Node> actualMovesList = Arrays.asList(new Node(0, 0), new Node(1, 0),
                new Node(1, 1), new Node(2, 1), new Node(2, 2), new Node(3, 2),
                new Node(4, 2), new Node(4, 1), new Node(4, 0));

//        List<Node> expectedMovesList = gameEngine.generateMovesList(start, goal, obstacles);
        List<Node> expectedMovesList = gameEngine.generateMovesList(start, goal);
        System.out.println(expectedMovesList);

//        expectedMovesList.forEach(e -> System.out.println(e.getCost()));
        Assertions.assertIterableEquals(actualMovesList, expectedMovesList);
    }
    @Test
    void shouldChooseCorrectWeight(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        HashMap<Point, Obstacle> aObstaclePlacement = new HashMap<>();
        aObstaclePlacement.put(new Point(2,0),new ObstacleFactory().create(1,1) );
        ObstaclePlacementList obstaclePlacementList =  new ObstaclePlacementList(aObstaclePlacement);

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),
                        new Hero(List.of(creatureFactory.create(1, false, 5)),Collections.emptyList()),obstaclePlacementList);
        Node node = new Node(2,0);
        gameEngine.chooseWeight(node);
        int actualWeight = node.getWeight();
        int expectedWeight = 10;

        Assertions.assertEquals(expectedWeight, actualWeight);

    }

}
