import java.util.*;

public class GameTreeNode {
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;

    //TODO:
    // MiniMax function
    // Function to build game tree

    public void expandChildren(int depthLimit){
        //Expands game tree to the given depth limit

    }

    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        GameTreeNode bestNode = new GameTreeNode();

        return bestNode;
    }

}
