import java.util.*;

public class PlayGame {

    /*
    This should hold general game logic like drawing the board and prompting turns
    */

    GameBoard board = new GameBoard();
    Scanner userInput = new Scanner(System.in);

    public void play(){
    /*
    handle the back and forth turns between players/AIs
    */
        int currentPlayer = 1;

        System.out.print("Select the AIs opponent:\n[1] Human\n[2] AI\n==> ");
        int opponentChoice = userInput.nextInt();

        //Handle different choices

        // loop until the game is finished, alternating turns
        while(true){ // <-- CHANGE LATER
            board.drawBoard();
            playerTurn(currentPlayer);
        }

        // Display game results (Win, Lose, Tie)


    }

    private void playerTurn(int currentPlayer){
        /*
        handles logic of a player's turn (placing X/O, checking validity of move)
        */
        int selectedRow;
        int selectedCol;

        System.out.printf("***Player %d's turn!***\n", currentPlayer);
        System.out.println("Enter row [0 to 2]");
        selectedRow = userInput.nextInt();
        System.out.println("Enter col [0 to 2]");
        selectedCol = userInput.nextInt();

        // check validity and check move
        board.tryPlacePiece(selectedRow, selectedCol);

    }

    private void AITurn(){

    }

}
