package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

/**
 * @Author Ben Rhine
 */
public class LifeStream {

    protected Long generation = 0L;
    protected CytoFate cytoFate;
    protected CytoGrid initialState;

    LifeStream(CytoFate cytoFate) {
        if(cytoFate != null) {
            this.cytoFate = cytoFate;
        } else {
            this.cytoFate = null;
        }
    }

    public void initialize( final CytoGrid cytoGrid ) {
        initialState = cytoGrid;
        reset();
    }

    public void reset() {
        generation = 0L;
    }

    public Long getGeneration() {
        return this.generation;
    }

    public CytoGrid getInitialState() {
        return this.initialState;
    }

    public CytoFate getCytoFate() {
        return this.cytoFate;
    }

    CytoGrid transition( CytoGrid grid ) {
        final int rows = grid.getRows();
        final int columns = grid.getCols();

        final CytoGrid next = new CytoGrid( rows, columns );

        for( int row = 0; row < rows; row++ ) {
            for( int col = 0; col < columns; col++ ) {
                next.getState().put( row, col, cytoFate.nextState( grid, row, col ) );
            }
        }
        return next;
    }
}
