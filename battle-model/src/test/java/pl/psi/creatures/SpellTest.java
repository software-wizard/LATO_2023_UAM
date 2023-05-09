package pl.psi.creatures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SpellTest {

    @Test
    void creatureShouldGetSpellDamage() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .armor(0)
                        .build())
                .build();
        Spell spell = new Spell.spellBuilder().statistic(SpellStatistic.FIREBALL).build();
        spell.cast(creature);
        assertThat(creature.getCurrentHp()).isEqualTo(5);
    }
}