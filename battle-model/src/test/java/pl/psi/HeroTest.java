package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroTest {

    final Creature aCreature1 = new Creature.Builder().statistic(CreatureStats.builder()
                    .build())
            .build();
    final Creature aCreature2 = new Creature.Builder().statistic(CreatureStats.builder()
                    .build())
            .build();
    @Test
    void creatureShouldBeAllied(){
        final Hero aHero1 = new Hero(List.of(aCreature1, aCreature2));

        assertThat(aHero1.isAlly(aCreature1, aCreature2)).isTrue();
    }

    @Test
    void creatureShouldNotBeAllied(){
        final Hero aHero1 = new Hero(List.of(aCreature1));
        final Hero aHero2 = new Hero(List.of(aCreature2));

        assertThat(aHero1.isAlly(aCreature1, aCreature2)).isFalse();
    }

    @Test
    void herolessCreatureShouldNotBeAllied(){
        final Hero aHero1 = new Hero(List.of(aCreature1));
        assertThat(aHero1.isAlly(aCreature1, aCreature2)).isFalse();
    }
}
