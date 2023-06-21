package pl.psi;

import pl.psi.creatures.BattleUnit;
import pl.psi.specialFields.Obstacle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutonomousUnitController implements PropertyChangeListener {

    private final Runnable passMethod;
    private final Hero hero1;
    private final Hero hero2;

    private List<Obstacle> obstacles;

    public AutonomousUnitController(Hero aHero1, Hero aHero2, Runnable aPassMethod, List<Obstacle> aObstacles){
        hero1 = aHero1;
        hero2 = aHero2;
        passMethod = aPassMethod;
        obstacles = aObstacles;
    }

    public void propertyChange(PropertyChangeEvent evt){
        if(evt.getPropertyName().equals("NEXT_UNIT")){
            BattleUnit currentBattleUnit = (BattleUnit) evt.getNewValue();

            if(!currentBattleUnit.isControllable()){
                List<BattleUnit> allies = Stream.concat(
                        hero1.getBattleUnits().stream().filter(b -> hero1.isAlly(currentBattleUnit, b)).collect(Collectors.toList()).stream(),
                        hero2.getBattleUnits().stream().filter(b -> hero1.isAlly(currentBattleUnit, b)).collect(Collectors.toList()).stream()
                        ).collect(Collectors.toList());

                List<BattleUnit> enemies = Stream.concat(
                        hero1.getBattleUnits().stream().filter(b -> !hero1.isAlly(currentBattleUnit, b)).collect(Collectors.toList()).stream(),
                        hero2.getBattleUnits().stream().filter(b -> !hero1.isAlly(currentBattleUnit, b)).collect(Collectors.toList()).stream()
                ).collect(Collectors.toList());

                currentBattleUnit.selfAct(allies, enemies, obstacles);

                passMethod.run();
            }
        }
    }
}
