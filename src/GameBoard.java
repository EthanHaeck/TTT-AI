public class GameBoard {
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private char[][] gameBoard = {{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}
                                 ,{EMPTY,EMPTY,EMPTY}};

    //possible methods:
    /*
        - tryPlacePiece
        - checkWin
        - evaluate
        - drawBoard
     */

    public boolean tryPlacePiece(int row, int col){
        /*
        checks that the given coords are inbounds and not already occupied
        and places a piece (X for player 1, O for player 2)
        returns true if a piece placed successfully, or false otherwise
        */

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
            gameBoard[row][col] = X;
            return true;
        }
    }

    public boolean checkWin(){
        if(gameBoard[0][0] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][2] == 'X'){
            return true;
        }
        if(gameBoard[0][0] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][2] == 'O'){
            return true;
        }
        if(gameBoard[0][2] == 'X' & gameBoard[1][1] == 'X' & gameBoard[2][0] == 'X'){
            return true;
        }
        if(gameBoard[0][2] == 'O' & gameBoard[1][1] == 'O' & gameBoard[2][O] == 'O'){
            return true;
        }
        for (int i = 0; i > 2; i++){
                if(gameBoard[i][0] == 'X' & gameBoard[i][1] == 'X' & gameBoard[i][2] == 'X'){
                    return true;
                }
                if(gameBoard[i][0] == 'O' & gameBoard[i][1] == 'O' & gameBoard[i][2] == 'O'){
                    return true;
                }
                if(gameBoard[0][i] == 'X' & gameBoard[1][i] == 'X' & gameBoard[2][i] == 'X'){
                    return true;
                }
                if(gameBoard[0][i] == 'O' & gameBoard[1][i] == 'O' & gameBoard[2][i] == 'O'){
                    return true;
                }
            }
            return false;
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

}
