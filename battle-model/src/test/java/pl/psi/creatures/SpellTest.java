package pl.psi.creatures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SpellTest {

    @Test
    void creatureShouldGetSpellDamage() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .armor(10)
                        .spellDamageResistance(9)
                        .build())
                .build();
        Spell spell1 = new Spell.spellBuilder().statistic(SpellStatistic.ICE_BOLT).build();
        Spell spell2 = new Spell.spellBuilder().statistic(SpellStatistic.CURE).build();
        spell1.cast(creature);
        spell2.cast(creature);
        assertThat(creature.getCurrentHp()).isEqualTo(5);
    }
}