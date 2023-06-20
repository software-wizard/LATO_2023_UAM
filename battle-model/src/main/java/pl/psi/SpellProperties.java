package pl.psi;

import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.SpellProtection;

public interface SpellProperties {

    SpellProtection getSpellDamageProtection();


    CreatureStatisticIf getStats();

    void updateStats(CreatureStatisticIf updatedStats);
}
