package com.benrhine.conway.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by xtheshadowgod on 2/19/17.
 */
public class CytoGridFactory {

    private Random rand;

    public CytoGridFactory( Random random ) {
        if(random == null) {
            rand = new Random();
        } else {
            rand = random;
        }
    }

    public CytoGrid stringInitializedGrid( final String gridString ) {
        CytoGrid grid = null;
        if(gridString != null) {
            //final List input = gridString.split( "\n" ).collect{ String it -> it.trim() };
            final String[] splitGs = gridString.split( "\n" );
            List<String> input = new ArrayList<String>();
            for (String gs: splitGs) {
                input.add(gs.trim());
            }
            final Integer rows = input.size();

            //The number of columns is derived from the row with the most characters
            final Integer cols = 0; //input.size().max();

            grid = new CytoGrid( rows, cols );
//            input.eachWithIndex{ String row, Integer rowNum ->
//                    row.padRight( cols, "." ).toList().eachWithIndex{ String ch, Integer colNum ->
//                    grid.put( rowNum, colNum, CytoGrid.ALIVE_CHAR == ch );
//            }
//            }
//            IntStream.range(0, input.size()).forEach(idx -> query.bind(idx, input.get(idx)));
        }
        return grid; // return
    }

    public CytoGrid randomizedGrid( final Integer rows, final Integer columns ) {
        CytoGrid grid = null;

        if(rows != null && columns != null) {
            grid = new CytoGrid( rows, columns );

            for( int row = 0; row < rows; row++ ) {
                for( int col = 0; col < columns; col++ ) {
                    //grid.put( row, col, rand.nextBoolean() );
                }
            }
        }
        return grid; // return
    }
}
