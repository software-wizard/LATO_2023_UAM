package pl.psi;


import com.google.common.collect.Range;
import pl.psi.creatures.Creature;
import pl.psi.creatures.DamageCalculatorIf;

public interface Defendable{

     int getMaxHp();

     void attack(Defendable aDefender);

     int getCurrentHp();

     void setCurrentHp(int i);

     int getAmount();
     void setAmount(int i);

     void applyDamage(int i);

     void counterAttack(Creature aDefender);

     int getArmor();

     int getCounterAttackCounter();
}
