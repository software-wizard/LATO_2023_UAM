package pl.psi;


import lombok.Setter;
import pl.psi.creatures.DamageCalculatorIf;

import java.beans.PropertyChangeListener;

public interface Defendable{
     boolean canCounterAttack();

     int getMaxHp();

     int getCurrentHp();

     void setCurrentHp(int i);

     int getAmount();
     void setAmount(int i);

}
