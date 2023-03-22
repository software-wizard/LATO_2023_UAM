//// ******************************************************************
////
//// Copyright 2023 PSI Software AG. All rights reserved.
//// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
////
//// ******************************************************************
//
//package pl.psi.game;
//
//import com.google.common.collect.Range;
//import org.junit.jupiter.api.Test;
//
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
// */
//class CreatureTest
//{
//
//    public static final int NOT_IMPORTANT = 0;
//
//    @Test
//    void creatureShouldAttackProperly()
//    {
//        Creature attacker = new Creature( 1, Range.closed( 10, 10 ) );
//        Creature defender = new Creature( 100, Range.closed( 0, 0 ) );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 90 );
//    }
//
//    @Test
//    void creatureShouldAttackProperlyEvenUseRandom()
//    {
//        Creature attacker = new Creature( 1, Range.closed( 0, 10 ), new TestRandom() );
//        Creature defender = new Creature( 100, Range.closed( 0, 0 ) );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
//    }
//
//    @Test
//    void creatureShouldAttackProperlyEvenUseArmorAttack()
//    {
//        //TODO add all cases in method source test
//        Creature attacker = new Creature( 1, Range.closed( 10, 10 ),30, NOT_IMPORTANT);
//        Creature defender = new Creature( 100, Range.closed( NOT_IMPORTANT, NOT_IMPORTANT ), NOT_IMPORTANT, 10 );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 80 );
//    }
//
//    @Test
//    void creatureShouldAttackProperlyEvenUseReduceArmorStrategy()
//    {
//        //TODO add all cases in method source test
//        Creature attacker = new Creature( 1, Range.closed( 10, 10 ),30, NOT_IMPORTANT);
//        attacker.setDamageCalculateStrategy(new ReduceArmorDmgCalculatorStrategy(0.5));
//        Creature defender = new Creature( 100, Range.closed( NOT_IMPORTANT, NOT_IMPORTANT ), NOT_IMPORTANT, 10 );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 78 );
//    }
//
////    @ParameterizedTest
////    @MethodSource("provideStringsForIsBlank")
////    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
////        assertEquals(expected, Strings.isBlank(input));
////    }
////
////    private static Stream<Arguments> provideStringsForIsBlank() {
////        return Stream.of(
////                Arguments.of(null, true),
////                Arguments.of("", true),
////                Arguments.of("  ", true),
////                Arguments.of("not blank", false)
////        );
////    }
//
//    @Test
//    void defenderShouldCounterAttack(){
//        Creature attacker = new Creature( 100, Range.closed( 0, 0 ) );
//        Creature defender = new Creature( 1, Range.closed( 10, 10 ) );
//
//        attacker.attack( defender );
//
//        assertThat(attacker.getCurrentHp()).isEqualTo(90);
//    }
//
//    private class TestRandom extends Random
//    {
//
//        @Override
//        public double nextDouble()
//        {
//            return 0.5;
//        }
//    }
//}