package pl.psi.creatures;

import pl.psi.Defendable;

import java.util.Random;

public class WarMachineDamageCalculator {
    public static final int MAX_ATTACK_DIFF = 60;
    public static final int MAX_DEFENCE_DIFF = 12;
    public static final double DEFENCE_BONUS = 0.025;
    public static final double ATTACK_BONUS = 0.05;
    private final Random rand;

    protected WarMachineDamageCalculator(final Random aRand )
    {
        rand = aRand;
    }

    public int calculateDamage(WarMachine aAttacker, Defendable aDefendable){
        final int armor = aDefendable.getArmor();

        double damageToDeal = (2+ rand.nextInt(2));

        if(!aAttacker.getRelevantSkills().get("Attack").equals((double)0)){
            damageToDeal*=(1+aAttacker.getRelevantSkills().get("Attack").intValue());
        }
        if(!aAttacker.getRelevantSkills().get("Archery").equals((double)0)){
            damageToDeal*= aAttacker.getRelevantSkills().get("Archery");
        }

        if(rand.nextInt(100)<aAttacker.getRelevantSkills().get("Ballistics").intValue()){
            damageToDeal*=2;
        }

        if( aAttacker.getStats().getAttack() >= armor )
        {
            int attackPoints = aAttacker.getStats().getAttack() - armor;
            if( attackPoints > MAX_ATTACK_DIFF )
            {
                attackPoints = MAX_ATTACK_DIFF;
            }

            damageToDeal *= (1 + attackPoints * ATTACK_BONUS);
        }
        else
        {
            int defencePoints = armor - aAttacker.getStats().getAttack();
            if( defencePoints > MAX_DEFENCE_DIFF )
            {
                defencePoints = MAX_DEFENCE_DIFF;
            }
            damageToDeal *= (1 - defencePoints * DEFENCE_BONUS);
        }

        if( damageToDeal < 0 )
        {
            damageToDeal = 0;
        }

        return (int)damageToDeal;

    }
}
