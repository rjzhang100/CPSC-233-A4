//This class functions as a standard wrapper class for communicating coordinates and transferring coordinates between multiple classes
//Allows for storing coordinates, retrieving coordinates and mutating coordinates 
public class Location{

  private int row;
  private int col;

  public Location(int newRow, int newCol){
    row = newRow;
    col = newCol;
  }

  public Location(){
  }

  public int getRow(){
    return(row);
  }

  public int getCol(){
    return(col);
  }

  public void setCol(int newCol){
    col = newCol;
  }

  public void setRow(int newRow){
    row = newRow;
  }

}
