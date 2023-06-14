package pl.psi.creatures;

import java.util.Random;

public class SpellFailureCalculator {
    public boolean spellWillNotFail(Creature aCreature) {
        Random random = new Random();
        int spellFailurePercent = 100 - aCreature.getPercentOfSpellResistance();
        int randomNumber = random.nextInt(10);

        return randomNumber < spellFailurePercent;
    }
}
