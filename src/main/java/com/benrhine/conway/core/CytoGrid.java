package com.benrhine.conway.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.String.join;
import com.google.common.collect.ArrayTable;

/**
 * @Author Ben Rhine
 */
public class CytoGrid {
    public static final String ALIVE = "O";
    public static final String DEAD = ".";

    private ArrayTable<Integer, Integer, Boolean> state = null;
    private int rows = 0;
    private int cols = 0;

    public CytoGrid() { }

    public CytoGrid(final int rows, final int columns) {
        if(rows > 0 && columns > 0) {
            this.rows = rows;
            this.cols = columns;

            //http://stackoverflow.com/questions/3387373/fill-arrays-with-ranges-of-numbers
            List<Integer> rowsTable  = new ArrayList<Integer>();
            int[] range1 = IntStream.iterate(0, n -> {
                rowsTable.add(n);
                return n + 1;
            }).limit(rows).toArray();

            List<Integer> colsTable  = new ArrayList<Integer>();
            int[] range2 = IntStream.iterate(0, n -> {
                colsTable.add(n);
                return n + 1;
            }).limit(columns).toArray();

            state = ArrayTable.create( rowsTable, colsTable );
        } else {
            System.out.println("Invalid rows and columns");
        }

    }

    public Boolean exists( final int row, final int columns ) {
        return row >= 0 && row < this.rows && columns >= 0 && columns < this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public ArrayTable<Integer, Integer, Boolean> getState() { return this.state; }

    public void setState(ArrayTable<Integer, Integer, Boolean> state) {
        this.state = state;
    }

    //TODO - Upgrade to use Java 8 streams
    @Override
    public String toString() {
        final Map<Integer, Map<Integer, Boolean>> rowMap = state.rowMap();
        String str = "";

        for(Integer key : rowMap.keySet()) {
            for(Integer key2 : rowMap.get(key).keySet()) {
                str = str + (rowMap.get(key).get(key2) ? ALIVE : DEAD);
            }
            str = str + "\n";
        }
        return str;
    }
}
