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


    }

}
