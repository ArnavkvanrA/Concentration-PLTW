/**
 * A game board of NxM board of tiles.
 *
 *  @author PLTW
 * @version 2.0
 */

/**
 * A Board class for concentration
 */
public class Board
{
    private static String[] tileValues = {"lion", "lion",
            "penguin", "penguin",
            "dolphin", "dolphin",
            "fox", "fox",
            "monkey", "monkey",
            "turtle", "turtle"};
    private Tile[][] gameboard = new Tile[3][4];

    /**
     * Constructor for the game. Creates the 2D gameboard
     * by populating it with card values
     *
     */
    public Board()
  {
    List<String> temp = Arrays.asList(tileValues);
    Collections.shuffle(temp);
    int index = 0;
   for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 4; j++) {
      gameboard[i][j] = new Tile(temp.get(index));
      index++;
    }
   } 

  }
  public Tile[][] getGameboard() {
    return gameboard;
  }

    /**
     * Returns a string representation of the board, getting the state of
     * each tile. If the tile is showing, displays its value,
     * otherwise displays it as hidden.
     *
     * Precondition: gameboard is populated with tiles
     *
     * @return a string representation of the board
     */
    public String toString()
  {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (gameboard[i][j] == null) {
          return "Gameboard isn't full";
        }
      }
    }
    String board = "";
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (gameboard[i][j].isShowingValue() == true) {
  
        board += gameboard[i][j].getValue() + " "; 
        }
        else {board += gameboard[i][j].getHidden() + " ";}
      }
      board += "\n";
    }
    return board;
  }
    /**
     * Determines if the board is full of tiles that have all been matched,
     * indicating the game is over.
     *
     * Precondition: gameboard is populated with tiles
     *
     * @return true if all tiles have been matched, false otherwise
     */
    public boolean allTilesMatch()
  {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (gameboard[i][j].matched() == false) {
          return false;
        }
      }
    }
    return true;
    
  }

    /**
     * Sets the tile to show its value (like a playing card face up)
     *
     * Preconditions:
     *   gameboard is populated with tiles,
     *   row values must be in the range of 0 to gameboard.length,
     *   column values must be in the range of 0 to gameboard[0].length
     *
     * @param row the row value of Tile
     * @param column the column value of Tile
     */
    public void showValue (int row, int column)
  {
   gameboard[row][column].show();
  }  

    /**
     * Checks if the Tiles in the two locations match.
     *
     * If Tiles match, show Tiles in matched state and return a "matched" message
     * If Tiles do not match, re-hide Tiles (turn face down).
     *
     * Preconditions:
     *   gameboard is populated with Tiles,
     *   row values must be in the range of 0 to gameboard.length,
     *   column values must be in the range of 0 to gameboard[0].length
     *
     * @param row1 the row value of Tile 1
     * @param col1 the column value of Tile 1
     * @param row2 the row value of Tile 2
     * @param col2 the column vlue of Tile 2
     * @return a message indicating whether or not a match occured
     */
    public String checkForMatch(int row1, int col1, int row2, int col2)
  {
    if (row1 >= 0 && row1 < 3 && row2 >= 0 && row2 < 3 && col2 >= 0 && col2 < 4 && col1 >= 0 && col1 < 4) {
      if (gameboard[row1][col1].getValue().equals(gameboard[row2][col2].getValue())) {
        gameboard[row1][col1].foundMatch();
        gameboard[row2][col2].foundMatch();
        return "It's a match";
      }
      gameboard[row1][col1].hide();
      gameboard[row2][col2].hide();
      return "It's not a match";
    }
    gameboard[row1][col1].hide();
    gameboard[row2][col2].hide();
    return "It's not a match";
  }

    /**
     * Checks the provided values fall within the range of the gameboard's dimension
     * and that the tile has not been previously matched
     *
     * @param row the row value of Tile
     * @param col the column value of Tile
     * @return true if row and col fall on the board and the row,col tile is unmatched, false otherwise
     */
    public boolean validateSelection(int row, int col)
  {
    if (row >= 0 && row < 3 && col >= 0 && col < 4 && gameboard[row][col].matched() == false) {
      return true;
    }
    return false;
  }

}
