import java.util.Scanner;
/*
  Author: Ray Zhang
  UCID: 30110113
  Tutorial Number: T04

  PROGRAM FUNCTION:
  This program simulates a remix of Conway's "The Game of Life" in a text-based, command prompt display. Simulations are continually generated until the user inputs a quit entry. Two biospheres, a regular
  and prosperous one allow for new rules regarding birth and death of the entities, critters, and can be selected by the user upon beginning the program. For input files with a Taminator, the Taminator will teleport and 'taminate' critters
  in the adjacent squares to it, before teleporting to a randomly generated, empty location on the array. Display is organized to show the previous world's condition, the condition after births and deaths of the world, as well as the condition of the world after
  Tamination.

  PROGRAM LIMITATIONS:
  This program is limited by the types of entities there are, and types of conditions. The only means of changing population are through births, deaths or tamination. The program also does not allow for more than one taminator,
  or immigration or emigration from the world. As well, rules for births and deaths are fixed across an entire simulation, and will not change based on changing conditions of the world.

  VERSIONS:
  Version: March 28th, 2021
    1) Implemented display function that prints the 3 arrays in a row with bounding lines and index numbering
    2) Create basic while loop to run the program until a q or Q is inputted by the user
  Version: March 29th, 2021
    1) Created birth death checker to determine births and deaths for the current world
    2) Implemented basic Taminator (that doesn't work) but know I understand the structure of how it needs to be built
    3) Broke down all processes into methods, even small ones like copying arrays, for better organization and understanding
    To-Do: Implement taminator properly and begin implementing prosperous biosphere
  Version: March 31st, 2021
    1) Properly implemented taminator to kill and move
    2) Implemented prosperous biosphere
    3) Added nicer output messages based on user input
    4) Reworked procedure for cases where critter is at the edge of the array
    To-Do: Add documentation and maybe debugging mode?
  Version: April 1st, 2021
    1) Added documentation

*/

public class GameOfLife
{
    public static void main (String [] args)
    {
        //Start of code written by James Tam, students can freely modify (but still need to
        //fulfill assignment requirements and stylistic approaches).
        Biosphere regular;
        regular = new Biosphere(FileInitialization.read());
        regular.runTurn();
    }
}
