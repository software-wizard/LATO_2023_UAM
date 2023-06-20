package pl.psi.specialFields;

import lombok.Getter;
import pl.psi.Defendable;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.SpellProtection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;

@Getter
public class Obstacle implements PropertyChangeListener, Defendable {
    private ObstacleStatisticIf stats;
    private int currentHp;
    private Consumer<Obstacle> obstacleRemoveMethod;

    Obstacle() {
    }

    //TODO: accept() function takes creature

    public Obstacle(final ObstacleStatisticIf aStats) {
        stats = aStats;
        currentHp = stats.getMaxHp();
        obstacleRemoveMethod = (c) -> {};
    }

    public int getMaxHp() {
        return stats.getMaxHp();
    }

    @Override
    public void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    @Override
    public void counterAttack(Creature aDefender) {
        //they cannot attack
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return this.getCurrentHp() > 0;
    }

    public String getName() {
        return stats.getName();
    }

    public Integer getWeight(){
        return stats.getWeight();
    }

    @Override
    public void applyDamage(int dmg) {
        setCurrentHp(currentHp-1);
        if(this.getCurrentHp()<=0){
            obstacleRemoveMethod.accept(this);
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void attack(Defendable aDefender) {
        //they do not attack
    }

    public void setObstacleRemoveMethod(Consumer<Obstacle> aObstacleRemoverMethod) {
        obstacleRemoveMethod = aObstacleRemoverMethod;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public void applyEffectOnTouch(Defendable aDefendable) {}

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public SpellProtection getSpellDamageProtection() {
        return null;
    }

    public CreatureStatisticIf getStats() {
        CreatureStatisticIf placeholder = null;
        return placeholder;
    }

    @Override
    public void updateStats(CreatureStatisticIf updatedStats) {}
}
