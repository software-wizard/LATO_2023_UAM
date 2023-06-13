package pl.psi.creatures;

import java.util.Random;

public class SpellFailureCalculator {
    public boolean spellWillNotFail(Spell spell) {
        Random random = new Random();
        int randomNumber = random.nextInt(10);

        if ( randomNumber < spell.chancesOfSpellFailure()) {
            return true;
        }
        return false;
    }

}
