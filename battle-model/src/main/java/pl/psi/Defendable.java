package pl.psi;

import pl.psi.creatures.Creature;

public interface Defendable extends Transparency, SpellProperties{

     int getMaxHp();

     void attack(Defendable aDefender);

     int getCurrentHp();

     void setCurrentHp(int i);

     void applyDamage(int i);

     void counterAttack(Creature aDefender);

     int getArmor();

     int getCounterAttackCounter();

     boolean isAlive();
}
