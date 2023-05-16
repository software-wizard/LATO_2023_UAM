package pl.psi;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.creatures.WarMachine;
import pl.psi.warmachines.WarMachineStatistic;
import pl.psi.warmachines.WarMachineStats;

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
            new GameEngine( new Hero( List.of( new BattleUnit(creatureFactory.create( 1, false, 5 ) )) ),
                new Hero( List.of(new BattleUnit(creatureFactory.create( 1, false, 5 ) )) ) );

        gameEngine.attack( new Point( 1, 1 ) );
    }

    @Test
    void healingShouldNotThrowErrors(){
        final GameEngine gameEngine =
                new GameEngine(new Hero(
                        List.of(
                                new BattleUnit(new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).relSkill(0).build()),
                                new BattleUnit(creatureFactory.create(1, false, 5)))),
                        new Hero(List.of(new BattleUnit(creatureFactory.create(1, false, 5)))));
        gameEngine.heal(new Point(1, 1));
        gameEngine.attack(new Point(1, 1));
    }
}
