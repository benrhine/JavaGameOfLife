package com.benrhine.conway;

import com.benrhine.conway.core.CytoGrid;
import com.benrhine.conway.core.CytoGridFactory;
import com.benrhine.conway.services.LifeStream;
import com.benrhine.conway.services.CytoFate;
import org.apache.commons.cli.*;

import static java.lang.System.exit;

/**
 * Created by xtheshadowgod on 2/18/17.
 */
public class JavaGameOfLife {
    final static CytoGridFactory cytoGridFactory = new CytoGridFactory(null);
    final static CytoFate CYTO_FATE = new CytoFate();

    public static void main( String[] args ) {
        Options opts = new Options();
        opts.addOption( "r", "randomize", true, "Start the game with a randomly generated grid of the specified size.  Example: --randomize 12x12" );
        opts.addOption( "f", "file", true, "Start the game with a cell in the specified file." );
        opts.addOption( "g", "generations", true, "The number of generations to print. Defaults to 1");

        //CommandLine cmd = new DefaultParser().parse( opts, args );
        DefaultParser dp = new DefaultParser();

        try {
            CommandLine cmd = dp.parse( opts, args );


            CytoGrid initialGrid = new CytoGrid();
            Integer generations = Integer.parseInt(cmd.getOptionValue( "g", "1" ));
            System.out.println("Incoming number of generations: " + generations);

            if( cmd.hasOption( "r" ) && cmd.getOptionValue("r").contains("x") ) {
                String str = cmd.getOptionValue( "r" );
                String[] size = str.split("x", 2);
                initialGrid = cytoGridFactory.randomizedGrid(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
            } else if( cmd.hasOption( "f" ) ) {
                //TOOD handle exceptions if file can't be read
                String str = cmd.getOptionValue( "f" );
                //String string = new File( str ).text;
                //initialGrid = cytoGridFactory.stringInitializedGrid( string );
            } else {
                System.err.println( "You must specify either --randomize or --file options" );
                printUsage( opts );
                exit(0);
                return;
            }
            System.out.println("Initial Population");
            System.out.println("-----------------------");
            System.out.println(initialGrid);
            System.out.println("\n");

            LifeStream life = new LifeStream(CYTO_FATE);
            life.initialize( initialGrid );

            CytoGrid current;
            CytoGrid previous = initialGrid;

            for( int i = 0; i < generations; i++ ) {

                current = life.forward(1);

                printGeneration( current, life.getGeneration() );

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Print the cell grid.
     *
     * @param grid
     * @param generation
     * @return
     */
    private static void printGeneration(CytoGrid grid, Long generation ) {
        System.out.println("Generation " + generation);
        System.out.println("-----------------");
        System.out.println(grid);
        System.out.println("\n");
    }


    private static void printUsage(Options opts ) {
        new HelpFormatter().printHelp( "java -jar game-of-life.jar", opts );
    }
}
