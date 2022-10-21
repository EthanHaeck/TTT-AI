import java.util.*;

public class GameTreeNode {
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';

    //TODO:
    // MiniMax function
    // Function to build game tree

    public GameTreeNode(GameBoard root){
        this.gameBoard = root;
    }

    public void expandChildren(int initialPlayerNum){
        //Expands game tree to the given depth limit
        //root is 0, so depth 3 is 3 moves past
        //given the current board, generate the children to a depth of 3
        Stack<GameTreeNode> stack = new Stack<>();
        ArrayList<GameTreeNode> visited = new ArrayList<>();
        int currentDepth = 1;

        //create the rest of the tree
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++) {
                if(gameBoard.getPosition(i, j) == EMPTY) {
                    //clone the board
                    GameBoard childBoard = gameBoard.clone();
                    //make the move on the cloned board
                    childBoard.tryPlacePiece(i, j, initialPlayerNum);
                    //add the cloned board to list of children
                    GameTreeNode childNode = new GameTreeNode(childBoard);
                    childNode.gameBoard.evaluate();
                    children.add(childNode);

                    //run MiniMax on the child
                    childNode.runMiniMax(true, 1);
                }
            }
        }


    }

    private void createChildren(int currentDepth, int playerNum){
        //create children based off this node's board
        // a child is a possible move that can be made on the parent board

        //don't create any children if the depth limit has been hit
        if(currentDepth >= MAX_DEPTH){
            return;
        }

        //to swap between placing 'X' and 'O'
        int nextPlayerNum;
        if(playerNum == 1){
            nextPlayerNum = 2;
        }
        else{
            nextPlayerNum = 1;
        }

        //look for an open position to play
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(gameBoard.getPosition(i, j) == EMPTY){
                    //clone the board
                    GameBoard childBoard = gameBoard.clone();
                    //make the move on the cloned board
                    childBoard.tryPlacePiece(i, j, playerNum);
                    //add the cloned board to list of children
                    GameTreeNode childNode = new GameTreeNode(childBoard);
                    childNode.gameBoard.evaluate();
                    children.add(childNode);

                    //recursive call to make more children
                    childNode.createChildren(currentDepth+1, nextPlayerNum);


                }
            }
        }

        //maybe return a value to show nodes expanded

    }


    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        GameTreeNode bestNode;
        int bestVal;

        if(max){
            bestVal = Integer.MIN_VALUE;

            //find the child with the best evaluation
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).gameBoard.evalValue > bestVal){
                    bestVal = children.get(i).gameBoard.evalValue;
                    bestNode = children.get(i);
                }
            }
        }
        else{
            bestVal = Integer.MAX_VALUE;

            //find the child with the worsr evaluation
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).gameBoard.evalValue < bestVal){
                    bestVal = children.get(i).gameBoard.evalValue;
                    bestNode = children.get(i);
                }
            }
        }


        return bestNode;
    }

}
