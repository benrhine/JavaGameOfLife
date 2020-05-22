package com.benrhine.conway.services;

import com.benrhine.conway.core.CytoGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xtheshadowgod on 2/19/17.
 */
public class CytoFate {

    public Boolean nextState( CytoGrid grid, int row, int col ) {
        //System.out.println("nextState "+row+"x"+col);
//        def liveNeighbors = getNeighbors( grid, row, col ).count{ it }
//        liveNeighbors == 3 || (liveNeighbors == 2 && grid.at( row, col ))
        Integer liveCount = 0;
        Boolean returnBool = false;
        List<Boolean> liveNeighbors = getNeighbors(grid, row, col);
        //System.out.println("Size "+liveNeighbors.size());
//        for(int i = 0; i < liveNeighbors.size(); i++) {
//            //System.out.println(liveNeighbors.get(i));
//
//            //if(liveNeighbors.get(i) != null) {
//            if(liveNeighbors.get(i)) {
//                liveCount++;
//            }
//        }
        liveCount = liveNeighbors.size();

        if((liveCount == 3) || (liveCount == 2 && grid.getState().at( row, col ))) {
            returnBool = true;
        }
        //System.out.println("getting here");
        return returnBool;
    }

//    Boolean nextState(final CytoGrid grid, final int row, final int col ) {
//        final List<Boolean> liveNeighbors = getNeighbors( grid, row, col ).count{ it }
//        liveNeighbors == 3 || (liveNeighbors == 2 && grid.at( row, col ))
//    }

    //TODO - i think the bug is here
    public static List<Boolean> getNeighbors(CytoGrid grid, int row, int col ) {
        //System.out.println("getNeighbors " + row + " " + col);
        List<Boolean> neighbors = new ArrayList<>();

        for(int r  = row; r < (row+1); r++) {
            for(int c  = col; c < (col+1); c++) {
                //System.out.println(r+"x"+c + " ");
                if( (r != row || c != col) && grid.exists( r, c ) ) {
                    //System.out.println(r+"x"+c);
                    neighbors.add(grid.getState().at( r, c ));
                }
            }
        }

//        if(row == grid.getRows()) {
//            //System.out.println("Row " + grid.getRows());
//            for(int r  = (row-1); r < (row); r++) {
//                columnCalc(r, row, col, grid, neighbors);
//            }
//        } else {
//            //System.out.println("Row " + row);
//            for(int r  = (row-1); r < (row+1); r++) {
//                columnCalc(r, row, col, grid, neighbors);
//            }
//            System.out.println("here 3");
//        }

        //Null values are cells that didn't exist.
        return neighbors;
    }

//    static List<Boolean> getNeighbors(final CytoGrid grid, final int row, final int col ) {
//        List<Boolean> neighbors = []
//
//        ((row-1)..(row+1)).each{ r ->
//                ((col-1)..(col+1)).each { c ->
//
//            //The cell must exist and not be itself.
//            if( (r != row || c != col) && grid.exists( r, c ) ) {
//                neighbors << grid.at( r, c )
//            }
//        }
//        }
//
//        //Null values are cells that didn't exist.
//        neighbors
//    }

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
            //System.out.println("Col " + grid.getColumns());
            for (int c = (col-1); c < (col); c++) {
                neighbors = neighborAdd(r, c, row, col, grid, neighbors);
            }
            //System.out.println("here 2");
        } else {
            //System.out.println("Col " +  col);
            for (int c = (col-1); c < (col + 1); c++) {
                neighbors = neighborAdd(r, c, row, col, grid, neighbors);
            }
        }
    }
}
