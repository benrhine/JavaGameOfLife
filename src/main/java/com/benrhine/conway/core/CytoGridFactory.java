package com.benrhine.conway.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author Ben Rhine
 */
public class CytoGridFactory {

    private final Random rand;

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
            final String[] split = gridString.split( "\n" );
            List<String> input = new ArrayList<String>();
            for (String s: split) {
                input.add(s.trim());
            }
            final int rows = input.size();

            final List<Integer> lineLength = new ArrayList<>();
            for (String str : input) {
                lineLength.add(str.length());
            }

            final int cols = Collections.max(lineLength);

            grid = new CytoGrid( rows, cols );

            final List<String> collect = IntStream.range(0, input.size())
                    .mapToObj(index -> input.get(index))
                    .collect(Collectors.toList());

            for (int i = 0; i < collect.size(); i++) {
                //System.out.println(collect.get(i));
                char[] chars = collect.get(i).toCharArray();
                final List<String> stringList = new ArrayList<>();
                for(char c: chars) {
                    stringList.add(String.valueOf(c));
                }


                for (int ii = 0; ii < stringList.size(); ii++) {
                    final String str = stringList.get(ii);
                    boolean b = CytoGrid.ALIVE.equals(str);
                    grid.getState().put(i, ii, b);
                }
            }
        }
        return grid; // return
    }

    public CytoGrid randomizedGrid( final int rows, final int cols ) {
        final CytoGrid grid;

        grid = new CytoGrid( rows, cols );

        for( int row = 0; row < rows; row++ ) {
            for( int col = 0; col < cols; col++ ) {
                grid.getState().put( row, col, rand.nextBoolean() );
            }
        }
        return grid; // return
    }
}
