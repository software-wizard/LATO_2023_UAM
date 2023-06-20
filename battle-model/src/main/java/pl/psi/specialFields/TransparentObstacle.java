package pl.psi.specialFields;

import pl.psi.Defendable;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.SpellProtection;

import java.beans.PropertyChangeEvent;
import java.util.function.Consumer;

abstract class TransparentObstacle extends Obstacle{
    private final Obstacle decorated;
    private Consumer<Obstacle> obstacleRemoveMethod;

    public TransparentObstacle (final Obstacle aObstacle){

        decorated = aObstacle;
        obstacleRemoveMethod = (c) -> {};
    }
//    private Effect effect = aDefendable -> {applyEffectOnTouch(aDefendable);};
////    private Damage damage = aDefendable -> {};



    @Override
    public void applyEffectOnTouch(Defendable aDefendable){
        //do sth when stomped at
    }

    @Override
    public CreatureStatisticIf getStats(){
        return decorated.getStats();
    };


    @Override
    public int getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public void setCurrentHp(final int aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    public boolean isAlive() {
        return decorated.isAlive();
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
        if(this.getCurrentHp()<=0){
            obstacleRemoveMethod.accept(this);
        };
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
    @Override
    public void setObstacleRemoveMethod(Consumer<Obstacle> aObstacleRemoverMethod) {
        obstacleRemoveMethod = aObstacleRemoverMethod;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public SpellProtection getSpellDamageProtection() {
        return decorated.getSpellDamageProtection();
    }

    @Override
    public void updateStats(CreatureStatisticIf updatedStats) {
        decorated.updateStats(updatedStats);
    }

    @Override
    public String toString(){
        return decorated.toString();
    }
}