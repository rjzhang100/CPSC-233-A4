/* As needed students can write additional methods and add attributes */

public class ProsperousBiosphere extends Biosphere // Students can't change this inheritance relation.
{
    //Students can make any changes except for the relationship.
    //Define constants for use in biosphere's birthDeathChecker() to be called when the prosperous biosphere is selected by the user 
    public final static int BIRTH_NUM_CON = 4;
    public final static int LONELY_DEATH_CON = 1;
    public final static int CROWDING_DEATH_CON = 4;

    public ProsperousBiosphere(Critter [][] aWorld)
    {
        super(aWorld);
    }


  }
