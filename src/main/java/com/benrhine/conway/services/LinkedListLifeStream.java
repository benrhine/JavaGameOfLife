package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.LinkedList;

/**
 * @Author Ben Rhine
 */
public class LinkedListLifeStream extends LifeStream {

    private LinkedList<CytoGrid> history;

    public LinkedListLifeStream(CytoFate cytoFate) {
        super(cytoFate);
        this.history = new LinkedList<>();
    }

    @Override
    public void reset() {
        super.reset();
        history.clear();
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
                try {
                    history.add(transition(history.getLast()));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
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
}
