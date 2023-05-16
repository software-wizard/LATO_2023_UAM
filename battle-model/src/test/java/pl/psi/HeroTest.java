package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.BattleUnit;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroTest {

    final BattleUnit aBattleUnit1 = new BattleUnit(new Creature.Builder().statistic(CreatureStats.builder()
                    .build())
            .build());
    final BattleUnit aBattleUnit2 = new BattleUnit(new Creature.Builder().statistic(CreatureStats.builder()
                    .build())
            .build());
    @Test
    void creatureShouldBeAllied(){
        final Hero aHero1 = new Hero(List.of(aBattleUnit1, aBattleUnit2));

        assertThat(aHero1.isAlly(aBattleUnit1, aBattleUnit2)).isTrue();
    }

    @Test
    void creatureShouldNotBeAllied(){
        final Hero aHero1 = new Hero(List.of(aBattleUnit1));
        final Hero aHero2 = new Hero(List.of(aBattleUnit2));

        assertThat(aHero1.isAlly(aBattleUnit1, aBattleUnit2)).isFalse();
    }

    @Test
    void herolessCreatureShouldNotBeAllied(){
        final Hero aHero1 = new Hero(List.of(aBattleUnit1));
        assertThat(aHero1.isAlly(aBattleUnit1, aBattleUnit2)).isFalse();
    }
}
