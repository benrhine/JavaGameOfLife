package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ben Rhine
 */
public class CytoFate {

    public Boolean nextState( CytoGrid grid, int row, int col ) {
        long liveCount = 0;
        Boolean returnBool = false;
        List<Boolean> liveNeighbors = getNeighbors(grid, row, col);
        liveCount = liveNeighbors.stream().filter(p -> p == true).count();

        if((liveCount == 3) || (liveCount == 2 && grid.getState().at( row, col ))) {
            returnBool = true;
        }

        return returnBool;
    }

    public static List<Boolean> getNeighbors(CytoGrid grid, int row, int col ) {
        final List<Boolean> neighbors = new ArrayList<>();

        for(int r  = (row-2); r < (row+2); r++) {
            for(int c  = (col-2); c < (col+2); c++) {
                if( (r != row || c != col) && grid.exists( r, c ) ) {
                    neighbors.add(grid.getState().at( r, c ));
                }
            }
        }

        return neighbors;
    }

    private static List<Boolean> neighborAdd(int r, int c, int row, int col, CytoGrid grid, List<Boolean> neighbors) {
        //System.out.println(r+"x"+c+" " + row + " " + col);
        //System.out.println(grid.exists( 0, 0 ));
        //System.out.println(grid.exists( 49, 49 ));
        //System.out.println(grid.exists( 50, 50 ));
        if( grid.exists( r, c ) ) {
            //System.out.println(r+"x"+c);
            neighbors.add(grid.getState().at( r, c ));
            //System.out.println("here 1");
        }
        return neighbors;
    }

    private static void columnCalc(int r, int row, int col, CytoGrid grid, List<Boolean> neighbors) {
        if(col == grid.getCols()) {
            for (int c = (col-1); c < (col); c++) {
                neighbors = neighborAdd(r, c, row, col, grid, neighbors);
            }
        } else {
            for (int c = (col-1); c < (col + 1); c++) {
                neighbors = neighborAdd(r, c, row, col, grid, neighbors);
            }
        }
    }
}
