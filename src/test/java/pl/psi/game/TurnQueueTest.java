// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class TurnQueueTest
{

    @Test
    void turnQueueTest()
    {
//        Creature c1 = new Creature( CreatureStats.builder()
//            .name( "c1" )
//                .moveRange(3)
//            .build() );
        Creature c1 = mock(Creature.class);
        when(c1.getMoveRange()).thenReturn(3);
        Creature c2 = new Creature( CreatureStats.builder()
            .name( "c2" )
                .moveRange(1)
            .build() );
        Creature c3 = new Creature( CreatureStats.builder()
            .name( "c3" )
                .moveRange(2)
            .build() );

        TurnQueue queue = new TurnQueue( List.of( c1, c2, c3 ) );

        assertThat( queue.getCurrentCreature() ).isEqualTo( c1 );

        queue.nextCreature();
        assertThat( queue.getCurrentCreature() ).isEqualTo( c3 );

        queue.nextCreature();
        assertThat( queue.getCurrentCreature() ).isEqualTo( c2 );
        verify(c1, times(1)).propertyChange(any());

        queue.nextCreature();
        assertThat( queue.getCurrentCreature() ).isEqualTo( c1 );
    }
}