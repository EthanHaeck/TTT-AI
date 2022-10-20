import java.util.*;

public class GameTreeNode {
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private int playerNum;
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

    public void expandChildren(int depthLimit){
        //Expands game tree to the given depth limit
        //root is 0, so depth 3 is 3 moves past
        //given the current board, generate the children to a depth of 3
        Stack<GameTreeNode> stack = new Stack<>();
        ArrayList<GameTreeNode> visited = new ArrayList<>();
        int currentDepth = 0;

        // add all children of the current node to the stack
        stack.addAll(children);

        // iterate through each child
        while(!stack.isEmpty()){
            //do this three times, each iteration on the newly created children
            if(currentDepth <= depthLimit){
                GameTreeNode current = stack.pop();


            }
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

                }
            }
        }

    }

    private void createChildren(GameTreeNode parentNode){
        //given a board, create children
        // a child is a possible move that can be made on the parent board

        //look for an open position to play
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(parentNode.gameBoard.getPosition(i, j) == EMPTY){
                    //clone the board
                    GameBoard childBoard = parentNode.gameBoard.clone();
                    //make the move on the cloned board
                    childBoard.tryPlacePiece(i, j, playerNum);
                    //add the cloned board to list of children
                    GameTreeNode childNode = new GameTreeNode(childBoard);
                    childNode.gameBoard.evaluate();
                    parentNode.children.add(childNode);

                }
            }
        }
    }

    public void evaluateNodes(GameTreeNode child){
        //evaluate a game board and set its evalValue
        for(int i = 0; i < children.size(); i++){
            children.get(i).gameBoard.evaluate();
        }
    }

    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        GameTreeNode bestNode = new GameTreeNode(gameBoard);

        // run MiniMax on the list of children

        return bestNode;
    }

}
