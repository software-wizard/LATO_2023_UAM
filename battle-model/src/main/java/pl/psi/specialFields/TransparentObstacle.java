package pl.psi.specialFields;

import pl.psi.Defendable;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.util.function.Consumer;

public class TransparentObstacle extends Obstacle{
    private final Obstacle decorated;

    public TransparentObstacle (final Obstacle aObstacle){
        decorated = aObstacle;
    }
    @Override
    public int getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public void setCurrentHp(final int aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    public void counterAttack(Creature aDefender) {
        decorated.counterAttack(aDefender);
    }

    @Override
    public int getArmor() {

        return decorated.getArmor();
    }

    @Override
    public int getCounterAttackCounter() {
        return decorated.getCounterAttackCounter();
    }
    @Override
    public String getName() {
        return decorated.getName();
    }
    @Override
    public Integer getWeight(){
        return decorated.getWeight();
    }

    @Override
    public void applyDamage(int dmg) {
        decorated.applyDamage(dmg);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }

    @Override
    public void attack(Defendable aDefender) {
        decorated.attack(aDefender);
        //they do not attack
    }

    public void setObstacleRemoveMethod(Consumer<Obstacle> aObstacleRemoverMethod) {
        decorated.setObstacleRemoveMethod(aObstacleRemoverMethod);
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public String toString(){
        return decorated.toString();
    }


}