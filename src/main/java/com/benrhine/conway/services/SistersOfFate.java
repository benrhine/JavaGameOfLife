package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xtheshadowgod on 2/19/17.
 */
public class SistersOfFate {

    public void nextState( CytoGrid grid, int row, int col ) {
//        def liveNeighbors = getNeighbors( grid, row, col ).count{ it }
//        liveNeighbors == 3 || (liveNeighbors == 2 && grid.at( row, col ))
    }

    public static List<Boolean> getNeighbors(CytoGrid grid, int row, int col ) {
        List<Boolean> neighbors = new ArrayList<Boolean>();

//        ((row-1)..(row+1)).each{ r ->
//                ((col-1)..(col+1)).each { c ->
//
//            //The cell must exist and not be itself.
//            if( (r != row || c != col) && grid.exists( r, c ) ) {
//                neighbors << grid.at( r, c );
//            }
//        }
//        }

        for(int r  = (row-1); r < (row+1); r++) {
            for(int c  = (col-1); c < (col+1); c++) {
                if( (r != row || c != col) && grid.exists( r, c ) ) {
                    //neighbors << grid.at( r, c );
                }
            }
        }

        //Null values are cells that didn't exist.
        return neighbors;
    }
}
