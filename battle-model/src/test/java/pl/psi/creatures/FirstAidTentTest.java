package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstAidTentTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);
    private static final Integer NOT_IMPORTANT_HERO_FIRST_AID_SKILL = 0;


    @Test
    void creatureShouldGetHealed(){
        /*
        final Creature firstAidTent = new WarMachineFactory.create(false, 1); //type, associated skill (First Aid for tent, Artillery for siege machines)

        final Creature aCreature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        aCreature.setCurrentHp(99);
        firstAidTent.heal(aCreature, NOT_IMPORTANT_HERO_FIRST_AID_SKILL);
        assertThat(aCreature.getCurrentHp()).isEqualTo(100);*/
    }

    @Test
    void randomCreatureShouldBeHealed(){
/*
        final Creature firstAidTent = new WarMachineFactory.create(false, 1); //controllable, type
        final Creature randomCreatureA = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        final Creature randomCreatureB = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        randomCreatureA.setCurrentHp(99);
        randomCreatureB.setCurrentHp(99);
        final List<Creature> hurtCreatures = List.of(randomCreatureA, randomCreatureB);
        Random rand = Mockito.mock(Random.class);
        when(rand.nextInt()).thenReturn(0);
        firstAidTent.heal(hurtCreatures.get(rand.nextInt()), NOT_IMPORTANT_HERO_FIRST_AID_SKILL); // who to heal, how much will the healing be
        assertThat(randomCreatureA.getCurrentHp()).isEqualTo(100);
        //should hurt creatures be kept in a separate list? if choosing random creature and trying to heal it, the algorithm might never end
        */

    }

    @Test
    void tentShouldHealOnEndOfTurn(){
        //TODO: set up event listeners/observers which heal at end of turn
    }

    @Test
    void playerShouldBeAbleToChooseHealedUnit(){
        //TODO: skill for Hero which allows to choose which unit to heal
    }
}