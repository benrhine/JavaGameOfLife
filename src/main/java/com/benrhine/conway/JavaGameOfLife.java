package com.benrhine.conway;

import com.benrhine.conway.core.CytoGrid;
import com.benrhine.conway.core.CytoGridFactory;
import com.benrhine.conway.services.LifeStream;
import com.benrhine.conway.services.SistersOfFate;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.File;

import static java.lang.System.exit;

/**
 * Created by xtheshadowgod on 2/18/17.
 */
public class JavaGameOfLife {
    final static CytoGridFactory cytoGridFactory = new CytoGridFactory();
    final static SistersOfFate sistersOfFate = new SistersOfFate();

    static void main( String[] args ) {
        Options opts = new Options();
        opts.addOption( "r", "randomize", true, "Start the game with a randomly generated grid of the specified size.  Example: --randomize 12x12" );
        opts.addOption( "f", "file", true, "Start the game with a cell in the specified file." );
        opts.addOption( "g", "generations", true, "The number of generations to print. Defaults to 1");

        DefaultParser cmd = new DefaultParser().parse( opts, args );

        CytoGrid initialGrid = null;
        Integer genrations = cmd.getOptionValue( "g", "1" ).toInteger();

        if( cmd.hasOption( "r" ) && cmd.getOptionValue("r").contains("x") ) {
            Integer size = cmd.getOptionValue( "r" ).split("x", 2);
            initialGrid = gridFactory.randomizedGrid( size[0].toInteger(), size[1].toInteger() );

        } else if( cmd.hasOption( "f" ) ) {

            //TOOD handle exceptions if file can't be read
            String string = new File( cmd.getOptionValue( "f" ) )?.text
                    initialGrid = gridFactory.stringInitializedGrid( string );

        } else {
            System.err.println( "You must specify either --randomize or --file options" );
            printUsage( opts );
            exit(0);
            return;
        }

        LifeStream life = new LifeStream( sistersOfFate );
        life.initialize( initialGrid );

        System.out.println("Initial Population");
        System.out.println("-----------------------");
        System.out.println(initialGrid);
        System.out.println("\n");

        CytoGrid current  = null;
        CytoGrid previous = initialGrid;

        for( int i = 0; i < genrations; i++ ) {

            current = life.forward(1);

            printGeneration( current, life.generation );

            //Extinction occurs when a grid is produce with no life.
            if( !current.values().contains( true ) ) {
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

    /**
     * Print the cell grid.
     *
     * @param grid
     * @param generation
     * @return
     */
    private static void printGeneration(CytoGrid grid, Long generation ) {
        System.out.println("Generation ${generation}");
        System.out.println("-----------------");
        System.out.println(grid);
        System.out.println("\n");
    }


    private static void printUsage(Options opts ) {
        return new HelpFormatter().printHelp( "java -jar game-of-life.jar", opts );
    }
}
