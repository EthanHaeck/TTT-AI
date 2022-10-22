import java.util.*;

public class PlayGame {

    /*
    This should hold general game logic like prompting turns
    */
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private char[][] testBoard = {{EMPTY,O,EMPTY}
                                 ,{EMPTY,X,O}
                                 ,{EMPTY,EMPTY,X}};

    GameBoard board = new GameBoard();
    Scanner userInput = new Scanner(System.in);

    public void play(){
    /*
    handle the back and forth turns between players/AIs
    */
        int gameStatus = 0;
        int round = 0;

        // HEURISTIC TEST
//        System.out.println("----HEURISTIC TEST---");
//        board.evaluate(testBoard);
//        System.out.printf("Eval = %d\n\n", board.evalValue);

        System.out.print("Select the AIs opponent:\n[1] Human\n[2] AI\n==> ");
        int opponentChoice = userInput.nextInt();

        //input validation
        while(opponentChoice < 1 || opponentChoice > 2){
            System.out.print("Enter a valid number: ");
            opponentChoice = userInput.nextInt();
        }

        //Handle different opponent choices
        if(opponentChoice == 1){ // PLAYER VS AI
            // loop until the win condition is reached, alternating turns
            board.drawBoard();
            while(gameStatus == 0){

                board.evaluate();
                System.out.printf("\nEval = %d\n\n", board.evalValue);

                playerTurn();
                gameStatus = board.checkWin();
                board.drawBoard();
                if(gameStatus != 0){
                    break;
                }
                AITurn(2);
                gameStatus = board.checkWin();
                board.drawBoard();
            }
        }
        else{ //AI VS AI
            while(gameStatus == 0){
                //add randomness to the AI moves so the game doesn't always end in a draw
                // just for the first move
                if(round == 0){
                    Random random = new Random();
                    int randRow = random.nextInt(3);
                    int randCol = random.nextInt(3);
                    while(!board.tryPlacePiece(randRow, randCol, 1)){
                        randRow = random.nextInt(3);
                        randCol = random.nextInt(3);
                    }
                }
                else{
                    AITurn(1);
                }

                gameStatus = board.checkWin();
                board.drawBoard();
                if(gameStatus != 0){
                    break;
                }
                AITurn(2);
                gameStatus = board.checkWin();
                board.drawBoard();
                round++;
            }
        }

        // Display game results (Win, Lose, Tie)
        switch(gameStatus){
            case 1:
                System.out.println("\n---Player 1 Wins!!---\n");
                break;
            case 2:
                System.out.println("\n---Player 2 Wins!!---\n");
                break;
            default:
                System.out.println("\n---Tie Game!!---\n");
        }

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
        while(!board.tryPlacePiece(selectedRow, selectedCol, 1)){
            System.out.println("---Move is invalid, try again!---");
            System.out.print("Enter row [0 to 2]: ");
            selectedRow = userInput.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            selectedCol = userInput.nextInt();
        }

    }

    private void AITurn(int playerNum){
        int nodesExpanded;
        int bestChild = 0;
        int bestVal;
        int[] move;
        GameTreeNode root;

        System.out.printf("***Player %d's turn!***\n", playerNum);

        // TEMPORARY - JUST FOR TESTING
//        Random random = new Random();
//        selectedRow = random.nextInt(3);
//        selectedCol = random.nextInt(3);
//        while(!board.tryPlacePiece(selectedRow, selectedCol, playerNum)){
//            selectedRow = random.nextInt(3);
//            selectedCol = random.nextInt(3);
//        }
        //print the move
//        System.out.printf("%d, %d\n", selectedRow, selectedCol);
        // ^^^ JUST FOR TESTING ^^^

        //clone the board
        GameBoard copy = board.clone();
        root = new GameTreeNode(copy);

        //expand the tree and evaluate each node
        nodesExpanded = root.expandChildren(playerNum);

        if(playerNum == 1){
            //choose best child node based on MiniMax value
            bestVal = root.children.get(0).minimaxValue;
            for(int i = 1; i < root.children.size(); i++){
                if(root.children.get(i).minimaxValue > bestVal){
                    bestChild = i;
                }
            }
        }
        else{
            //choose worst child node based on MiniMax value
            bestVal = root.children.get(0).minimaxValue;
            for(int i = 1; i < root.children.size(); i++){
                if(root.children.get(i).minimaxValue < bestVal){
                    bestChild = i;
                }
            }
        }

        //compare child board to current board to determine move
        move = board.compareBoard(root.children.get(bestChild).gameBoard.gameBoard);
        //make the move
        board.tryPlacePiece(move[0], move[1], playerNum);

        //print move information
        System.out.printf("%d, %d\n", move[0], move[1]);
        System.out.printf("Nodes expanded = %d\n", nodesExpanded);

    }

}
