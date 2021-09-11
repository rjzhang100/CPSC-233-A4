/* As needed students can write additional methods and add attributes */
import java.util.Random;
public class Taminator extends Critter
{
    //Class attribute by James Tam. Students do not make any changes to it but
    //additional attributes may be added.
    public static final char DEFAULT_APPEARANCE = 'T';
    private int tamIndexer = 0;
    private Random generator = new Random();

    //Start of modifiable code written by James Tam
    //Taminator(), Taminator(char)
    //Students can make any changes.
    public Taminator(){
	     super(DEFAULT_APPEARANCE);
    }

    public Taminator(char newAppearance){
	     super(newAppearance);
    }

    //Method that creates a random location for Taminator's destination
    public Location taminatorTeleport(){
      Location tamDest = new Location();
        int destRow = generator.nextInt(10);
        int destCol = generator.nextInt(10);
        tamDest = new Location(destRow,destCol);
      return tamDest;
    }

    //Method that scans all adjacent squares to the taminator, and returns the locations of any adjacent critters in a list of Locations
    //Parameters are the world in which the Taminator is acting, and the row and column coordinates of the Taminator's current position in the world
    public Location[] taminatorEnemyScan(Critter[][] tamWorld, int tamRow, int tamCol){
      //Indexer is used to append values to the list
      tamIndexer = 0;
      Location [] locations = new Location[9];
      //Iterate through a small matrix, which is all the adjacent squares surrounding the Taminator, including itself
      for (int u = (tamRow - 1); u <= (tamRow + 1); u++){
         for (int p = (tamCol - 1); p <= (tamCol + 1); p++){
           //If the location is outside the bounds of the world, do nothing and continue
           if ((u < 0) || (p < 0) || (u > 9) || (p > 9)){
             continue;
          }
          //If a critter is found, store this location using the wrapper class and append it to the locations list
           else if (tamWorld[u][p].getAppearance() == Critter.DEFAULT_APPEARANCE){
            locations[tamIndexer] = new Location(u,p);
            tamIndexer += 1;
          }
         }
      }      
      return locations;
    }

    //Method to access the indexer used in scanning for enemies
    public int getIndexer(){
      return(tamIndexer);
    }
}
