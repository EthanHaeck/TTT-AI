import java.util.*;

public class GameBoard {
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    public char[][] gameBoard = {{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}};

    public int evalValue = 0;

    //TODO:
    // Create Function to Clone Board


    public boolean tryPlacePiece(int row, int col, int playerNum){
        /*
        checks that the given coords are inbounds and not already occupied
        and places a piece (X for player 1, O for player 2)
        returns true if a piece placed successfully, or false otherwise
        */
        char symbol;

        if(playerNum == 1){
            symbol = X;
        }
        else{
            symbol = O;
        }

        if((row < 0 || row > 2) || (col < 0 || col > 2)){
            // move is invalid
            return false;
        }
        else if(gameBoard[row][col] != EMPTY){
            //spot already taken
            return false;
        }
        else{
            // move is valid
            gameBoard[row][col] = symbol;
            return true;
        }
    }


    public int checkWin(){
        // NO WIN = 0
        // P1 WINS = 1
        // P2 WINS = 2
        // TIE = 3

        // diagonal cases
        if(gameBoard[0][0] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][2] == 'X'){
            return 1;
        }
        if(gameBoard[0][0] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][2] == 'O'){
            return 2;
        }
        if(gameBoard[0][2] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][0] == 'X'){
            return 1;
        }
        if(gameBoard[0][2] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][0] == 'O'){
            return 2;
        }

        //rows and columns
        for (int i = 0; i <= 2; i++){
            if(gameBoard[i][0] == 'X' & gameBoard[i][1] == 'X' & gameBoard[i][2] == 'X'){
                return 1;
            }
            if(gameBoard[i][0] == 'O' & gameBoard[i][1] == 'O' & gameBoard[i][2] == 'O'){
                return 2;
            }
            if(gameBoard[0][i] == 'X' & gameBoard[1][i] == 'X' & gameBoard[2][i] == 'X'){
                return 1;
            }
            if(gameBoard[0][i] == 'O' & gameBoard[1][i] == 'O' & gameBoard[2][i] == 'O'){
                return 2;
            }
        }

        //check if moves can still be made
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(gameBoard[i][j] == EMPTY){
                    return 0;
                }
            }
        }

        //no winner and no moves can be played -- game ties
        return 3;
    }

    public void drawBoard(){
        /*
        print the board including the current moves
         */
        System.out.printf(" %c | %c | %c \n", gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]);
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c \n", gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]);
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c \n", gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]);
    }

    public void evaluate(){
        //Returns higher numbers if player 1 is at an advantage
        //or lower numbers if player 2 is at an advantage
        // utilizes a heuristic function to determine who has the advantage
        int result;
        int row1 = 0;
        int row2 = 0;
        int row3 = 0;
        int col1 = 0;
        int col2 = 0;
        int col3 = 0;
        int diag1 = 0;
        int diag2 = 0;
        int numEmpty = 0;
        int numX = 0;
        int numO = 0;

        //apply the best value if the board has a win
        // diagonal win cases
        if(gameBoard[0][0] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][2] == 'X'){
            evalValue = Integer.MAX_VALUE;
            return;
        }
        if(gameBoard[0][0] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][2] == 'O'){
            evalValue = Integer.MIN_VALUE;
            return;
        }
        if(gameBoard[0][2] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][0] == 'X'){
            evalValue = Integer.MAX_VALUE;
            return;
        }
        if(gameBoard[0][2] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][0] == 'O'){
            evalValue = Integer.MIN_VALUE;
            return;
        }

        //row and column win cases
        for (int i = 0; i <= 2; i++){
            if(gameBoard[i][0] == 'X' & gameBoard[i][1] == 'X' & gameBoard[i][2] == 'X'){
                evalValue = Integer.MAX_VALUE;
                return;
            }
            if(gameBoard[i][0] == 'O' & gameBoard[i][1] == 'O' & gameBoard[i][2] == 'O'){
                evalValue = Integer.MIN_VALUE;
                return;
            }
            if(gameBoard[0][i] == 'X' & gameBoard[1][i] == 'X' & gameBoard[2][i] == 'X'){
                evalValue = Integer.MAX_VALUE;
                return;
            }
            if(gameBoard[0][i] == 'O' & gameBoard[1][i] == 'O' & gameBoard[2][i] == 'O'){
                evalValue = Integer.MIN_VALUE;
                return;
            }
        }


        //calculate rows and columns

        //---row1---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[0][i] == X){
                numX++;
            }
            if(gameBoard[0][i] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            row1 = 1;
        }
        else if(numO > 0 && numX == 0){
            row1 = -1;
        }

        numX = 0;
        numO = 0;
        // ---row2---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[1][i] == X){
                numX++;
            }
            if(gameBoard[1][i] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            row2 = 1;
        }
        else if(numO > 0 && numX == 0){
            row2 = -1;
        }

        numX = 0;
        numO = 0;
        // ---row3---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[2][i] == X){
                numX++;
            }
            if(gameBoard[2][i] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            row3 = 1;
        }
        else if(numO > 0 && numX == 0){
            row3 = -1;
        }

        numX = 0;
        numO = 0;
        // ---col1---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][0] == X){
                numX++;
            }
            if(gameBoard[i][0] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            col1 = 1;
        }
        else if(numO > 0 && numX == 0){
            col1 = -1;
        }

        numX = 0;
        numO = 0;
        // ---col2---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][1] == X){
                numX++;
            }
            if(gameBoard[i][1] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            col2 = 1;
        }
        else if(numO > 0 && numX == 0){
            col2 = -1;
        }

        numX = 0;
        numO = 0;
        // ---col3---
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][2] == X){
                numX++;
            }
            if(gameBoard[i][2] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            col3 = 1;
        }
        else if(numO > 0 && numX == 0){
            col3 = -1;
        }


        //calculate diagonals
        numX = 0;
        numO = 0;
        //---diag1---
        for(int i = 0; i <= 2; i++){ //top left, middle, bottom right
            if(gameBoard[i][i] == X){
                numX++;
            }
            if(gameBoard[i][i] == O){
                numO++;
            }
        }
        if(numX > 0 && numO == 0){
            diag1 = 1;
        }
        else if(numO > 0 && numX == 0){
            diag1 = -1;
        }


        numX = 0;
        numO = 0;
        //---diag2---
        if(gameBoard[0][2] == X){ //top right
            numX++;
        }
        if(gameBoard[0][2] == O){
            numO++;
        }

        if(gameBoard[1][1] == X){ //middle
            numX++;
        }
        if(gameBoard[1][1] == O){
            numO++;
        }

        if(gameBoard[2][0] == X){ //bottom left
            numX++;
        }
        if(gameBoard[2][0] == O){
            numO++;
        }

        if(numX > 0 && numO == 0){
            diag2 = 1;
        }
        else if(numO > 0 && numX == 0){
            diag2 = -1;
        }


        //heurisitc
        result = row1 + row2 + row3 + col1 + col2 + col3 + diag1 + diag2;

        //set local gameBoard value
        evalValue = result;

    }

    public char getPosition(int row, int col){
        return gameBoard[row][col];
    }

    public GameBoard clone(){
        //returns a copy of the given game board :)
        GameBoard newGameBoard = new GameBoard();
        for (int i = 0; i <= 2; i++){
            for (int j = 0; j <= 2; j++){
                newGameBoard.gameBoard[i][j] = gameBoard[i][j];
            }
        }
        return newGameBoard;
    }

    public int[] compareBoard(char[][] otherBoard){
        //compares provided board to current
        //returns coordinates of first mismatch
        int[] coords = new int[2];
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(gameBoard[i][j] != otherBoard[i][j]){
                    coords[0] = i;
                    coords[1] = j;
                    break;
                }
            }
        }
        return coords;
    }

}
