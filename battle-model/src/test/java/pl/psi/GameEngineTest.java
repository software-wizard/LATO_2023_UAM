package pl.psi;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.creatures.WarMachine;
import pl.psi.warmachines.WarMachineStatistic;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
    @Test
    void shoudWorksHeHe()
    {
        final GameEngine gameEngine =
            new GameEngine( new Hero( List.of( new BattleUnit(creatureFactory.create( 1, false, 5 ) )), Collections.emptyList() ),
                new Hero( List.of(new BattleUnit(creatureFactory.create( 1, false, 5 ) )), Collections.emptyList() ) );

        gameEngine.attack( new Point( 1, 1 ) );
    }

    @Test
    void healingShouldNotThrowErrors(){
        final GameEngine gameEngine =
                new GameEngine(new Hero(
                        List.of(
                                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).build()),
                                new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()));
        gameEngine.heal(new Point(1, 1));
        gameEngine.attack(new Point(1, 1));
    }

    @Test
    void shouldGenerateNeighboursGivenPoint() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()));

        List<Node> actual = gameEngine.generateNeigboursList(0, 0);
        List<Node> expected = Arrays.asList(new Node(0, 1),
                new Node(1, 0));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    void shouldCalculateHeuristicGivenCurrentAndDestinationNode() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))), Collections.emptyList()));
        int actual = gameEngine.calculateHeuristic(new Node(4, 10), new Node(2, 2));
        int expected = 2 + 8;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @Disabled
    void shouldMoveToAGivenPoint() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))),Collections.emptyList()),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))),Collections.emptyList()));
        Optional<BattleUnit> battleUnit = gameEngine.getBattleUnit(new Point(0, 1));
        gameEngine.move(new Point(2, 3));
        Optional<BattleUnit> expectedBattleUnit = gameEngine.getBattleUnit(new Point(2, 3));
        Assertions.assertEquals(battleUnit, expectedBattleUnit);
    }

    @Test
    @Disabled
    void shouldGenerateActualMovesList() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))),Collections.emptyList()),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5))),Collections.emptyList()));

        Optional<BattleUnit> battleUnit = gameEngine.getBattleUnit(new Point(0, 0));
        Node start = new Node(0, 0);
        Node goal = new Node(4, 0);

        Map<Point, Integer> obstacles = new HashMap<>();
        obstacles.put(new Point(2, 0), Integer.MAX_VALUE);
        obstacles.put(new Point(3, 1), Integer.MAX_VALUE);

        List<Node> actualMovesList = Arrays.asList(new Node(0, 0), new Node(1, 0),
                new Node(1, 1), new Node(2, 1), new Node(2, 2), new Node(3, 2),
                new Node(4, 2), new Node(4, 1), new Node(4, 0));

        List<Node> expectedMovesList = gameEngine.generateMovesList(start, goal, obstacles);
        System.out.println(expectedMovesList);
//        expectedMovesList.forEach(e -> System.out.println(e.getCost()));
        Assertions.assertIterableEquals(actualMovesList, expectedMovesList);
    }

}
