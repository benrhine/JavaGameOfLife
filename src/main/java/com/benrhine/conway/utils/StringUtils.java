package com.benrhine.conway.utils;

import com.benrhine.conway.core.CytoGrid;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.util.Date;

/**
 * @Author Ben Rhine
 */
public final class StringUtils {

    /**
     * Print the cell grid.
     *
     * @param title
     * @param grid
     * @param generation
     * @return
     */
    public static void printGeneration(final String title, final CytoGrid grid, final long generation ) {
        System.out.println(title + " Generation " + generation);
        System.out.println("-----------------");
        System.out.println(grid);
        System.out.println("\n");
    }

    public static void printUsage(final Options opts ) {
        new HelpFormatter().printHelp( "java -jar game-of-life.jar", opts );
    }

    public static void printGameSummary(final long startTime, final int generations) {
        final Date endDate = new Date();
        final long endTime = endDate.getTime();
        final long runTime = endTime - startTime;

        System.out.println("Started Game of life at: " + startTime);
        System.out.println("Completing Game of life at: " + endTime);
        System.out.println("Game of life took " + runTime + " millis to complete for " + generations + " generations.");
    }
}
