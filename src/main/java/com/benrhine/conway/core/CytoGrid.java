package com.benrhine.conway.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.join;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;

/**
 * Created by xtheshadowgod on 2/18/17.
 */
public class CytoGrid {
    static final String ALIVE_CHAR     = "O";
    static final String NOT_ALIVE_CHAR = ".";

    ArrayTable<Integer, Integer, Boolean> state = null;
    Integer rows = 0;
    Integer columns = 0;

    public CytoGrid() { }

    public CytoGrid(final Integer rows, final Integer columns) {
        if(rows > 0 && columns > 0) {
            this.rows = rows;
            this.columns = columns;

            //http://stackoverflow.com/questions/3387373/fill-arrays-with-ranges-of-numbers
            List<Integer> rowsTable  = new ArrayList<Integer>();
            int[] range1 = IntStream.iterate(1, n -> {rowsTable.add(n);return n + 1;}).limit(rows-1).toArray();

            List<Integer> colsTable  = new ArrayList<Integer>();
            int[] range2 = IntStream.iterate(1, n -> {colsTable.add(n);return n + 1;}).limit(columns-1).toArray();

            state = ArrayTable.create( rowsTable, colsTable );
        } else {
            System.out.println("Invalid rows and columns");
        }

    }

    public Boolean exists( final Integer row, final Integer columns ) {
        return row >= 0 && row < this.rows && columns >= 0 && columns < this.columns;
    }

    public Integer getRows() {
        return this.rows;
    }

    public Integer getColumns() {
        return this.columns;
    }

//    @Override
//    public String toString() {
//        state.rowMap().collect{ row ->
//                row.value.collect{ col -> col.value ? ALIVE_CHAR : NOT_ALIVE_CHAR }.join("")
//        }.join( "\n" );
//    }
}
