// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue
{

    public final static String END_OF_TURN = "END_OF_TURN";
    private final List< Creature > allCreatures;
    private final Queue< Creature > queue = new LinkedList<>();
    private Creature currentCreature;
    private Collection< PropertyChangeListener > observers = new HashSet<>();

    public TurnQueue( List< Creature > aCreatures )
    {
        allCreatures = aCreatures;
        observers.addAll( aCreatures );
        nextCreature();
    }

    public Creature getCurrentCreature()
    {
        return currentCreature;
    }

    public void nextCreature()
    {
        if( queue.isEmpty() )
        {
            initialize();
            endOfTurn();
        }
        currentCreature = queue.poll();
    }

    private void endOfTurn()
    {
        observers.forEach( c -> c.propertyChange( new PropertyChangeEvent( this, END_OF_TURN, 1, 2 ) ) );
    }

    private void initialize()
    {
        queue.addAll( allCreatures.stream()
            .sorted( ( c1, c2 ) -> c2.getMoveRange() - c1.getMoveRange() )
            .toList() );
    }
}
