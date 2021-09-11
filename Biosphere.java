 /* As needed students can write additional methods and add attributes */
import java.util.Scanner;
public class Biosphere
{
    //Constant declarations and attributes by James Tam, don't change.
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    public static final int NUM_OF_WORLDS = 3;
    public static final int BIRTH_NUM_CON = 3;
    public static final int LONELY_DEATH_CON = 2;
    public static final int CROWDING_DEATH_CON = 3;
    private Critter [][] current;
    private Critter [][] birthDeath = new Critter[ROWS][COLUMNS];
    private Critter [][] tamWorld = new Critter[ROWS][COLUMNS];
    private int numCount = 0;
    private boolean gameRun = true;
    private Scanner in = new Scanner(System.in);
    private Location [] locations;
    private int indexer;
    private String whichSphere;


    //Original code written by James Tam, don't modify
    public Biosphere(Critter [][] aWorld)
    {
      current = aWorld;
      //Original code
    }

    //The code used by the student was based on the display code written by James Tam
    //Method that runs a general display of the three world states
    public void display(){
      //Display the headers that will exist for all simulations
	    System.out.println("  PREVIOUS GENERATION          BIRTHS AND DEATHS               TAMINATOR");
	    System.out.println(HORIZONTAL_COUNT + "      " + HORIZONTAL_COUNT + "      " + HORIZONTAL_COUNT);
      System.out.println(HORIZONTAL_LINE + "       " + HORIZONTAL_LINE + "       " + HORIZONTAL_LINE);
      //Iterate through the ten rows of the arrays, generating the rows for all three world states using generateRow()
      for (int rows = 0; rows < ROWS; rows++){
        generateRow(rows);
        System.out.println();
      }
    numCount = 0;
    }

    //Method which generates the nth row of each of the three worlds side by side
    //Parameter is which row of the three worlds are to be generated
    private void generateRow(int row){
      System.out.print(numCount);
      //Iterate through the nth row of the previous generation world and display it
      for (int m = 0; m < COLUMNS; m++){
        System.out.print("|" + current[row][m]);
      }
      System.out.print("|");
      System.out.print("      ");

      //Iterate through the nth row of the birth and deaths world and display it
      System.out.print(numCount);
      for (int b = 0; b < COLUMNS; b++){
        System.out.print("|" + birthDeath[row][b]);
      }
      System.out.print("|");
      System.out.print("      ");

      //Iterate through the nth row of the taminator world and display it
      System.out.print(numCount);
      for (int q = 0; q < COLUMNS; q++){
        System.out.print("|" + tamWorld[row][q]);
      }
      System.out.print("|");
      System.out.print("      ");
      numCount += 1;
      //Print a new line and the lower horizontal bounds
      System.out.println();
      for (int w = 0; w < NUM_OF_WORLDS; w++){
        System.out.print("" + HORIZONTAL_LINE);
        System.out.print("       ");
      }
    }

    //Original code written by James Tam, don't modify
    public Critter [][] getCurrent()
    {
        return(current);
    }

    //This method checks the conditions for every square in the birthsDeaths world and determines whether or not a birth should occur or a death should occur
    //Parameters are the conditions for birth, death due to loneliness and death due to overcrowding. Including these parameters allows for simple implementation of the ProsperousBiosphere
    public void birthsDeathsChecker(int birthCon, int lonelyCon, int crowdingCon){
      //Define an integer to count the number of adjacencies there are for a given element in the matrix
      int adjacencies = 0;
      for (int row = 0; row < ROWS; row++){
        for (int col = 0; col < COLUMNS; col++){
          //If a space in the array is empty, determine if there are enough adjacencies to warrant a birth
          if (current[row][col].getAppearance() == Critter.EMPTY){
            //Iterate through the adjacent squares to the element in question
            for (int i = (row - 1); i <= (row + 1); i++){
              for (int j = (col - 1); j <= (col + 1); j++){
                //If the desired index is out of the bounds of the world, continue and do nothing
                if ((i < 0) || (j < 0) || (i > 9) || (j > 9)){
                  continue;
                }
                //If an element that is a critter is found, add one to the adjacency counter
                else if (current[i][j].getAppearance() == Critter.DEFAULT_APPEARANCE){
                  adjacencies += 1;
                }
              }
            }
            //If, after searching, there are enough adjacencies to meet the specified birth condition, change the empty square to a critter, simulating a birth
            if (adjacencies == birthCon){
              birthDeath[row][col] = new Critter(Critter.DEFAULT_APPEARANCE);
            }
            adjacencies = 0;
          }
          //If a space in the array is a critter, determine if there are enough adjacencies to warrant a death
          else if (current[row][col].getAppearance() == Critter.DEFAULT_APPEARANCE){
            //Same logic in iteration applies here as the birth conditions
            for (int i = (row - 1); i <= (row + 1); i++){
              for (int j = (col - 1); j <= (col + 1); j++){
                if ((i < 0) || (j < 0) || (i > 9) || (j > 9)){
                  continue;
                }
                else if (current[i][j].getAppearance() == Critter.DEFAULT_APPEARANCE){
                  adjacencies += 1;
                }
              }
            }
            //Change the critter in this spot to be empty, simlulating a death, if the specified conditions for death are met
            if (((adjacencies - 1) < lonelyCon) || ((adjacencies - 1) > crowdingCon)){
              birthDeath[row][col] = new Critter(Critter.EMPTY);
            }
            adjacencies = 0;
          }
        }
      }
    }

    //Method that, using the methods of Taminator, move and cause action in the Taminator entity
    private void tamination(){
      //Iterate through the world and search for a taminator
      for (int row = 0; row < ROWS; row++){
        for (int col = 0; col < COLUMNS; col++){
          //If a taminator is found, run taminatorEnemyScan() to determine any enemeies adjacent to the taminator, and retrieve the index from the accessor method of Taminator
          if (tamWorld[row][col] instanceof Taminator){
            locations = ((Taminator) tamWorld[row][col]).taminatorEnemyScan(tamWorld, row, col);
            indexer = ((Taminator) tamWorld[row][col]).getIndexer();
          //Using indexer as a range, iterate through the locations of enemies and 'kill' (change to empty) any adjacent critters
          for (int i = 0; i < indexer; i++){
            if (tamWorld[locations[i].getRow()][locations[i].getCol()].getAppearance() == Critter.DEFAULT_APPEARANCE){
              tamWorld[locations[i].getRow()][locations[i].getCol()] = new Critter(Critter.EMPTY);
            }
          }
          //Continually ask taminatorTeleport() to generate locations until a valid one i.e an empty one has been reached
          Location destination = new Location(row,col);
          while (tamWorld[destination.getRow()][destination.getCol()].getAppearance() != Critter.EMPTY){
            destination = ((Taminator) tamWorld[row][col]).taminatorTeleport();
          }
          //Change the current location to be empty and 'move' the Taminator to the valid location determined previously 
          tamWorld[row][col] = new Critter(Critter.EMPTY);
          tamWorld[destination.getRow()][destination.getCol()] = new Taminator();
          return;
          }
        }
      }
    }

    //Method used to copy the current state of birthDeath to tamWorld
    private void setTamWorldToBirthDeath(){
      for (int i = 0; i < ROWS; i++){
        for (int j = 0; j < COLUMNS; j++){
          tamWorld[i][j] = new Critter(Critter.EMPTY);
          if (birthDeath[i][j].getAppearance() == Critter.DEFAULT_APPEARANCE){
            tamWorld[i][j] = new Critter(Critter.DEFAULT_APPEARANCE);
          }
          else if (birthDeath[i][j].getAppearance() == Taminator.DEFAULT_APPEARANCE){
            tamWorld[i][j] = new Taminator();
          }
        }
      }
    }

    //Method used to copy the current state of tamWorld to current
    private void setCurrentToTamWorld(){
      for (int i = 0; i < ROWS; i++){
        for (int j = 0; j < COLUMNS; j++){
          current[i][j] = new Critter(Critter.EMPTY);
          if (tamWorld[i][j].getAppearance() == Critter.DEFAULT_APPEARANCE){
            current[i][j] = new Critter(Critter.DEFAULT_APPEARANCE);
          }
          else if (tamWorld[i][j].getAppearance() == Taminator.DEFAULT_APPEARANCE){
            current[i][j] = new Taminator(Taminator.DEFAULT_APPEARANCE);
          }
        }
      }
    }

    //Method used to copy the current state or birthDeath to current
    private void setBirthDeathToCurrent(){
      for (int i = 0; i < ROWS; i++){
        for (int j = 0; j < COLUMNS; j++){
          birthDeath[i][j] = new Critter(Critter.EMPTY);
          if (current[i][j].getAppearance() == Critter.DEFAULT_APPEARANCE){
            birthDeath[i][j] = new Critter(Critter.DEFAULT_APPEARANCE);
          }
          else if (current[i][j].getAppearance() == Taminator.DEFAULT_APPEARANCE){
            birthDeath[i][j] = new Taminator(Taminator.DEFAULT_APPEARANCE);
          }
        }
      }
    }

    //Method which simulates a turn and receives user input to control the properties of the simulation
    public void runTurn(){
      //Boolean to check if an invalid entry was given when selecting which biosphere to simulate 
      boolean badEntry = true;
      String userInput;
      System.out.println("<<<WELCOME TO THE GAME OF LIFE!>>>");
      //Request for valid selection for biosphere type until a valid entry is given, then store in a variable
      while(badEntry){
        System.out.println("Enter 'R' or 'r' to simulate regular biosphere.");
        System.out.println("Enter 'P' or 'p' to simulate prosperous biosphere.");
        whichSphere = in.nextLine();
        if ((whichSphere.equals("P") || whichSphere.equals("p")) || (whichSphere.equals("R") || whichSphere.equals("r"))){
          badEntry = false;
        }
        else {continue;}
      }
      //Iterate through and continually generate simulations until a Quit key is entered
      while(gameRun){
        if ((whichSphere.equals("P") || whichSphere.equals("p"))){
          System.out.println("<<<Prosperous Biosphere Simulation>>>");
          System.out.println("Enter any key to simulate (Enter 'Q' or 'q' to quit)");
          userInput = in.nextLine();
          if ((userInput.equals("Q") || userInput.equals("q"))){
            System.out.println("Bye bye!");
            return;
          }
          else{
            //Make birthDeath a copy of current 
            setBirthDeathToCurrent();
            //Check the birth and death conditions of birthDeath, using the prosperous biosphere conditions since the user selected prosperous
            birthsDeathsChecker(ProsperousBiosphere.BIRTH_NUM_CON, ProsperousBiosphere.LONELY_DEATH_CON, ProsperousBiosphere.CROWDING_DEATH_CON);
            //Make tamWorld a copy of the birthDeath after changes
            setTamWorldToBirthDeath();
            //Call taminator action in the tamWorld
            tamination();
            //Display the results of all the change
            display();
            //Make current a copy of the tamWorld so that the previous generation on the next simulation can be displayed 
            setCurrentToTamWorld();
          }
        }
        else if ((whichSphere.equals("R") || whichSphere.equals("r"))){
          System.out.println("<<<Regular Biosphere Simulation>>>");
          System.out.println("Enter any key to simulate (Enter 'Q' or 'q' to quit)");
          userInput = in.nextLine();
          if ((userInput.equals("Q") || userInput.equals("q"))){
            System.out.println("Bye bye!");
            return;
          }
          //Same logic applied here as for the prosperous biosphere case, except the parameters for birthsDeathsChecker() are for regular biosphere conditions
          setBirthDeathToCurrent();
          birthsDeathsChecker(BIRTH_NUM_CON, LONELY_DEATH_CON, CROWDING_DEATH_CON);
          setTamWorldToBirthDeath();
          tamination();
          display();
          setCurrentToTamWorld();
        }
      }
    }
  }

