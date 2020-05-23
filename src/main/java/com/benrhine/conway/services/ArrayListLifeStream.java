package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.ArrayList;

/**
 * @Author Ben Rhine
 */
public class ArrayListLifeStream extends LifeStream {

    private ArrayList<CytoGrid> history;

    public ArrayListLifeStream(CytoFate cytoFate) {
        super(cytoFate);
        this.history = new ArrayList<CytoGrid>();
    }

    @Override
    public void reset() {
        super.reset();
        history.clear();
        history.add(initialState);
    }

    public CytoGrid forward(Integer steps ) {
        if( initialState == null ) {
            //TODO IllegalStateException is not the best exception to be throwing
            throw new IllegalStateException( "LifeMachine must be initialized first" );
        }

        if(steps != null && steps > 0) {
            for (int i = 0; i < steps; i++) {
                generation++;
                try {
                    history.add(transition(history.get(history.size()-1)));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return history.get(history.size()-1);
    }

    public CytoGrid back( Integer steps ) {
        if(steps != null && steps > 0 && steps <= history.size()) {
            for (int i = 0; i < steps; i++) {
                generation--;
                history.remove(history.size()-1);
            }
        }
        return history.get(history.size()-1);
    }
}
