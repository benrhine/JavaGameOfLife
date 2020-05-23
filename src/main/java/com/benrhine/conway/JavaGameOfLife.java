package com.benrhine.conway;

import com.benrhine.conway.core.CytoGrid;
import com.benrhine.conway.core.CytoGridFactory;
import com.benrhine.conway.services.ArrayListLifeStream;
import com.benrhine.conway.services.LifeStream;
import com.benrhine.conway.services.LinkedListLifeStream;
import com.benrhine.conway.services.CytoFate;
import org.apache.commons.cli.*;

import java.util.Date;

import static com.benrhine.conway.utils.StringUtils.*;
import static java.lang.System.exit;

/**
 * @Author Ben Rhine
 */
public class JavaGameOfLife {
    private final static CytoGridFactory cytoGridFactory = new CytoGridFactory(null);
    private final static CytoFate fate = new CytoFate();

    public static void main( String[] args ) {
        final Date startDate = new Date();
        final long startTime = startDate.getTime();

        final Options opts = new Options();
        opts.addOption( "r", "randomize", true, "Start the game with a randomly generated grid of the specified size.  Example: --randomize 12x12" );
        opts.addOption( "f", "file", true, "Start the game with a cell in the specified file." );
        opts.addOption( "g", "generations", true, "The number of generations to print. Defaults to 1");
        opts.addOption( "l", "list", true, "List implementation to use. Defaults to LinkedList");

        try {
            final CommandLine cmd = new DefaultParser().parse( opts, args );

            final CytoGrid initialGrid;
            final int generations = Integer.parseInt(cmd.getOptionValue( "g", "1" ));
            System.out.println("Incoming number of generations: " + generations);

            if( cmd.hasOption( "r" ) && cmd.getOptionValue("r").contains("x") ) {
                final String str = cmd.getOptionValue( "r" );
                final String[] size = str.split("x", 2);
                initialGrid = cytoGridFactory.randomizedGrid(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
            } else if( cmd.hasOption( "f" ) ) {
                //TOOD handle exceptions if file can't be read
                final String str = cmd.getOptionValue( "f" );
                //String string = new File( str ).text;
                //initialGrid = cytoGridFactory.stringInitializedGrid( string );
                throw new RuntimeException("This feature not implemented");
            } else {
                System.err.println( "You must specify either --randomize or --file options" );
                printUsage( opts );
                exit(0);
                return;
            }
            final String listType = cmd.getOptionValue( "l", "linked" );

            if (listType.equalsIgnoreCase("array")) {
                System.out.println("Using ArrayList for life stream ...");

                lifeCycle(new ArrayListLifeStream(fate), initialGrid, generations, listType);

            } else {
                System.out.println("Using LinkedList for life stream ...");

                lifeCycle(new LinkedListLifeStream(fate), initialGrid, generations, listType);
            }
            printGameSummary(startTime, generations);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void lifeCycle(final LifeStream life, final CytoGrid initialGrid, final Integer generations, final String listType) {
        life.initialize( initialGrid );

        printGeneration("Initial Population: Iteration ", initialGrid, 0);

        CytoGrid current;
        CytoGrid previous = initialGrid;

        for( int i = 0; i < generations; i++ ) {

            if (listType.equals("linked")) {
                current = ((LinkedListLifeStream) life).forward(1);
            } else {
                current = ((ArrayListLifeStream) life).forward(1);
            }

            printGeneration( "Generation", current, life.getGeneration() );

            //Extinction occurs when a grid is produce with no life.
            if( !current.getState().containsValue( true ) ) {
                System.out.println("\n\nExtinction occurred at generation ${life.generation}. No more life is possible.\n\n");
                break;
            }

            //Uptopia occurs when cells align in a way that their state will never change.  This is usually represented
            //by ring formations, where the cell(s) in the center will never have enough neighbors and the ring cells
            //always have two.
            if( current == previous ) {
                System.out.println("\n\nUtopia occurred at generation ${life.generation-1}.  All living cells will live forever, and no more new cells can come to life.");
                break;
            }

            previous = current;
        }
    }
}
