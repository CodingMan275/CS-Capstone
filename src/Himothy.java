import javax.swing.JFrame;

public class Himothy {
    
    int Energy;
    int score;
    int rowPos;
    int colPos;
    int currentTilePosition;

    Himothy(int Energy, int score, int currentTilePosition, int rowPos, int colPos)
    {
        this.Energy = Energy;
        this.score = score;
        this.currentTilePosition = currentTilePosition;
        this.rowPos = rowPos;
        this.colPos = colPos;
    }
    
    public int MovementLogic(String input, Board B)
    {
        int oldPos = 0;
        int Rows = Board.Rows;
        int Cols = Board.Cols;

        //Take input
        switch(input){
            //Input given was "w", we want our player to move up a row
        case("w") -> {
            //If we are not at the top row
            if(rowPos != 0)
            {
                //Top row 0, last row n. move to row 1-n 
                rowPos -= 1;
                //Save the tile position for graphical updates and such
                oldPos = currentTilePosition;
                //change the offical tile position of the player
                //Current position - how many rows there are + offset of coloumns (In a 4 by 4 square you just subtract 4)
                currentTilePosition -= Rows + (Cols - Rows);
                //Print statement for testing, feel free to delete later
                System.out.println("row " + rowPos +" col " + colPos);
            }
            }

         case("a") -> {
             //window.getContentPane().setBackground(Color.RED);
             if(colPos != 0)
             {
                 colPos -= 1;
                 oldPos = currentTilePosition;
                 currentTilePosition -= 1;
                 System.out.println("row " + rowPos +" col " + colPos);

             }
            }

         case("s") -> {
             if(rowPos != Rows - 1)
             {
                 rowPos += 1;
                 oldPos = currentTilePosition;
                 currentTilePosition += Rows + (Cols - Rows);
                 System.out.println("row " + rowPos +" col " + colPos);
                 System.out.println("Score: " + score);

             }
            }

         case("d") -> {
             if(colPos != Cols - 1)
             {
                 colPos += 1;
                 oldPos = currentTilePosition;
                 currentTilePosition += 1;
                 System.out.println("row " + rowPos +" col " + colPos);
             }
            }


        }
        return oldPos;
    }
    


    public void EnergyDown()
    {

    //while forever
     // if(on perosn tile)  
     //energy -= 1;
     
    }
}
