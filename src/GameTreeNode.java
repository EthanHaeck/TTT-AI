import java.util.*;

public class GameTreeNode {
    private List<GameTreeNode> children = new ArrayList<>();;
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

    public int expandChildren(int initialPlayerNum){
        //Expands game tree to the given depth limit
        //root is 0, so depth 3 is 3 moves past
        //given the current board, generate the children to a depth of 3
        int currentDepth = 1;
        int nodesExpanded = 0;

        //create children for the parent node
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++) {
                if(gameBoard.getPosition(i, j) == EMPTY) {
                    System.out.println("Creating first row of children");
                    //clone the board
                    GameBoard childBoard = gameBoard.clone();
                    //make the move on the cloned board
                    childBoard.tryPlacePiece(i, j, initialPlayerNum);
                    //add the cloned board to list of children
                    GameTreeNode childNode = new GameTreeNode(childBoard);
                    childNode.gameBoard.evaluate();
                    children.add(childNode);

                    //create children of the child (grandchildren)
                    nodesExpanded += childNode.createChildren(currentDepth, initialPlayerNum);

                    //run MiniMax on the child
                    //childNode.runMiniMax(true);
                }
            }
        }

        return nodesExpanded;


    }

    private int createChildren(int currentDepth, int playerNum){
        //create children based off this node's board
        // a child is a possible move that can be made on the parent board

        //don't create any children if the depth limit has been hit
        if(currentDepth > MAX_DEPTH){
            return 0;
        }

        //counts the current node as being expanded
        //generated children have not been expanded yet
        int expandedNodes = 1;

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
//                    System.out.printf("Creating row %d of children\n", currentDepth);
                    //clone the board
                    GameBoard childBoard = gameBoard.clone();
                    //make the move on the cloned board
                    childBoard.tryPlacePiece(i, j, nextPlayerNum);
                    //add the cloned board to list of children
                    GameTreeNode childNode = new GameTreeNode(childBoard);
                    childNode.gameBoard.evaluate();
                    children.add(childNode);

                    //recursive call to make more children
                    expandedNodes += childNode.createChildren(currentDepth+1, nextPlayerNum);


                }
            }
        }

        //return a value to show nodes expanded
        return expandedNodes;

    }


    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        GameTreeNode bestNode = this;
        int bestVal;

        //recurive case
        if(children.isEmpty()){
            minimaxValue = gameBoard.evalValue;
            return this;
        }



        if(max){
            bestVal = Integer.MIN_VALUE;

            //find the child with the best value
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).minimaxValue > bestVal){
                    bestVal = children.get(i).minimaxValue;
                    bestNode = children.get(i);
                }

                // choose between two nodes
                bestVal = Math.max(bestVal, runMiniMax(!max).minimaxValue);

            }
        }
        else{
            bestVal = Integer.MAX_VALUE;

            //find the child with the worst evaluation
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).minimaxValue < bestVal){
                    bestVal = children.get(i).minimaxValue;
                    bestNode = children.get(i);
                }
            }
        }

        return bestNode;
    }

}
