package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.LinkedList;

/**
 * Created by xtheshadowgod on 2/19/17.
 */
public class LifeStream {
    protected Long generation = 0L;
    protected SistersOfFate sistersOfFate;
    protected LinkedList<CytoGrid> history;

    protected CytoGrid initialState;

    public LifeStream( SistersOfFate sistersOfFate ) {
        if(sistersOfFate != null) {
            this.sistersOfFate  = sistersOfFate;
        } else {
            this.sistersOfFate  = null;
        }
        this.history = new LinkedList<CytoGrid>();
    }

    public void initialize( final CytoGrid cytoGrid ) {
        initialState = cytoGrid;
        reset();
    }

    public void reset() {
        history.clear();
        generation = 0L;
        history.add(initialState);
    }

    public LinkedList<CytoGrid> forward( Integer steps ) {
        //checkArgument steps > 0, 'steps must be greater than zero'

        if( initialState != null ) {
            //TODO IllegalStateException is not the best exception to be throwing
            throw new IllegalStateException( "LifeMachine must be initialized first" );
        }

        if(steps != null && steps > 0) {
            for (int i = 0; i < steps; i++) {
                generation++;
                history.add(transition(history.getLast()));
            }
        }

        history.getLast();
    }

    public LinkedList<CytoGrid> back( Integer steps ) {
        //checkArgument steps > 0, 'steps given cannot be less than zero'
        //checkArgument steps <= history.size(), 'cannot go back more than %d steps', history.size()

        if(steps != null && steps > 0 && steps <= history.size()) {
            for (int i = 0; i < steps; i++) {
                generation--;
                history.removeLast();
            }
        }

        history.getLast();
    }

    protected CytoGrid transition( CytoGrid grid ) {
        final Integer rows = grid.getRows();
        final Integer columns = grid.getColumns();

        CytoGrid next = new CytoGrid( rows, columns );

        for( int row = 0; row < rows; row++ ) {
            for( int col = 0; col < columns; col++ ) {
                //next.put( row, col, sistersOfFate.nextState( grid, row, col ) );
            }
        }

        return next;
    }
}
