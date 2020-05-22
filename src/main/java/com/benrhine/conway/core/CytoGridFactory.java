package com.benrhine.conway.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xtheshadowgod on 2/19/17.
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
//            input.eachWithIndex{ String row, int rowNum ->
//                row.padRight( cols, "." ).toList().eachWithIndex{ String ch, int colNum ->
//                    grid.put( rowNum, colNum, CytoGrid.ALIVE == ch )
//                }
//            }

            List<String> collect = IntStream.range(0, input.size())
                    .mapToObj(index -> input.get(index))
                    .collect(Collectors.toList());

            for (int i = 0; i < collect.size(); i++) {
                System.out.println(collect.get(i));
                char[] chars = collect.get(i).toCharArray();
                final List<String> stringList = new ArrayList<>();
                for(char c: chars) {
                    stringList.add(String.valueOf(c));
                }

                stringList.forEach(line -> {
                    System.out.println(line);
                });

                for (int ii = 0; ii < stringList.size(); ii++) {
                    String str = stringList.get(ii);
                    boolean b = CytoGrid.ALIVE_CHAR.equals(str);
                    grid.getState().put(i+1, ii+1, b);
                }
            }
        }
        return grid; // return
    }

    public CytoGrid randomizedGrid( final int rows, final int cols ) {
        final CytoGrid grid;

        //if(rows != null && cols != null) {
            grid = new CytoGrid( rows, cols );

            for( int row = 0; row < rows; row++ ) {
                for( int col = 0; col < cols; col++ ) {
                    grid.getState().put( row + 1, col + 1, rand.nextBoolean() );
                }
            }
        //}
        return grid; // return
    }
}
