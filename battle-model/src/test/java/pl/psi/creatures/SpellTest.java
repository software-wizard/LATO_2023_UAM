package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SpellTest {

    @Test
    void creatureShouldGetSpellDamage() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .name("lala")
                        .attack(CreatureStatistic.LICH.getAttack())
                        .armor(40)
                        .maxHp(100)
                        .moveRange(7)
                        .damage(Range.closed(2, 5))
                        .tier(1)
                        .spellDamageProtection(new SpellProtection.spellProtectionBuilder()
                                .airProtection(30)
                                .fireProtection(30)
                                .earthProtection(30)
                                .waterProtection(30)
                                .build())
                        .description("lala")
                        .isUpgraded(false)
                        .build())
                .build();

        Spell spell1 = new Spell.spellBuilder().statistic(SpellStatistic.ICE_BOLT).build();
        Spell spell2 = new Spell.spellBuilder().statistic(SpellStatistic.CURE).build();
        Spell spell3 = new Spell.spellBuilder().statistic(SpellStatistic.PROTECTION_FROM_WATER).build();
        Spell spell4 = new Spell.spellBuilder().statistic(SpellStatistic.WEAKNESS).build();


        spell1.cast(creature);
        assertThat(creature.getCurrentHp()).isEqualTo(93);

        spell2.cast(creature);
        assertThat(creature.getCurrentHp()).isEqualTo(98);

        spell3.cast(creature);
        assertThat(creature.getSpellDamageProtection().getWaterProtection()).isEqualTo(40);
        spell1.cast(creature);
        assertThat(creature.getCurrentHp()).isEqualTo(92);

        spell4.cast(creature);
        assertThat(creature.getDamage()).isEqualTo(Range.closed(1,4));

    }
}