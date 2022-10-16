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

        return true;
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
