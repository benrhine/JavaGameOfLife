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
        this.history = new LinkedList<>();
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

    public CytoGrid forward( Integer steps ) {
        if( initialState == null ) {
            //TODO IllegalStateException is not the best exception to be throwing
            throw new IllegalStateException( "LifeMachine must be initialized first" );
        }

        if(steps != null && steps > 0) {
            for (int i = 0; i < steps; i++) {
                generation++;
                history.add(transition(history.getLast()));
            }
        }
        return history.getLast();
    }

    public CytoGrid back( Integer steps ) {
        if(steps != null && steps > 0 && steps <= history.size()) {
            for (int i = 0; i < steps; i++) {
                generation--;
                history.removeLast();
            }
        }
        return history.getLast();
    }

    public Long getGeneration() {
        return this.generation;
    }

    protected CytoGrid transition( CytoGrid grid ) {
        final Integer rows = grid.getRows();
        final Integer columns = grid.getColumns();

        CytoGrid next = new CytoGrid( rows, columns );

        for( int row = 1; row <= rows; row++ ) {
            for( int col = 1; col <= columns; col++ ) {
                //System.out.println(col);
                next.getState().put( row, col, sistersOfFate.nextState( grid, row, col ) );

            }
            //System.out.println("getting here 1");
        }
        //System.out.println("getting here");
        return next;
    }
}
