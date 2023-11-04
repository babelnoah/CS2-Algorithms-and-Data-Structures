/**
 * The Node for an Integer Binary Tree
 *
 * @author: Noah Babel
 * @version: 03/28/22
 */

public class BSTNode {
    private BSTNode left;
    private BSTNode right;
    private int val;

    public BSTNode(int val) {
        this.left = null;
        this.right = null;
        this.val = val;
    }

    /** Getter and Setter methods**/

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }


    }

