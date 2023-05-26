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
                        .spellDamageResistance(new int[]{30,30,30,30})
                        .description("lala")
                        .isUpgraded(false)
                        .build())
                .build();
        Spell spell1 = new Spell.spellBuilder().statistic(SpellStatistic.ICE_BOLT).build();
        Spell spell2 = new Spell.spellBuilder().statistic(SpellStatistic.CURE).build();
        Spell spell3 = new Spell.spellBuilder().statistic(SpellStatistic.PROTECTION_FROM_WATER).build();
        Spell spell4 = new Spell.spellBuilder().statistic(SpellStatistic.WEAKNESS).build();

        System.out.println(creature.getCurrentHp());
        System.out.println(creature.getDamage());
        System.out.println(creature.getArmor());
        System.out.println(creature.getMoveRange());
        System.out.println(creature.getSpellDamageResistance()[0]);
        System.out.println(creature.getSpellDamageResistance()[1]);
        System.out.println(creature.getSpellDamageResistance()[2]);
        System.out.println(creature.getSpellDamageResistance()[3]);

        spell1.cast(creature);
        System.out.println(creature.getCurrentHp());
        System.out.println(creature.getSpellDamageResistance()[3]);

        spell2.cast(creature);
        System.out.println(creature.getCurrentHp());
        System.out.println(creature.getSpellDamageResistance()[3]);
        assertThat(creature.getCurrentHp()).isEqualTo(98);

        spell3.cast(creature);
        spell1.cast(creature);
        System.out.println(creature.getCurrentHp());
        System.out.println(creature.getSpellDamageResistance()[3]);
        assertThat(creature.getCurrentHp()).isEqualTo(92);

        System.out.println(creature.getDamage());
        spell4.cast(creature);
        System.out.println(creature.getDamage());


    }
}