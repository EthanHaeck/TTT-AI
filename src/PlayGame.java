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
        if(opponentChoice == 1){ // PLAYER VS AI
            // loop until the win condition is reached, alternating turns
            while(true){ // <-- CHANGE LATER
                board.drawBoard();
                playerTurn();
                board.drawBoard();
                AITurn(2);
            }
        }
        else{ //AI VS AI
            while(true){
                board.drawBoard();
                AITurn(1);
                board.drawBoard();
                AITurn(2);
            }
        }

        // Display game results (Win, Lose, Tie)


    }

    private void playerTurn(){
        /*
        handles logic of a player's turn (placing X/O, checking validity of move)
        */
        int selectedRow;
        int selectedCol;

        System.out.println("***Player 1's turn!***");
        System.out.print("Enter row [0 to 2]: ");
        selectedRow = userInput.nextInt();
        System.out.print("Enter col [0 to 2]: ");
        selectedCol = userInput.nextInt();

        // --- OPTIMIZE LATER ---
        // check validity and check move
        while(!board.tryPlacePiece(selectedRow, selectedCol)){
            System.out.println("---Move is invalid, try again!---");
            System.out.print("Enter row [0 to 2]: ");
            selectedRow = userInput.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            selectedCol = userInput.nextInt();
        }

    }

    private void AITurn(int playerNum){
        System.out.printf("***Player %d's turn!***\n", playerNum);



    }

}
