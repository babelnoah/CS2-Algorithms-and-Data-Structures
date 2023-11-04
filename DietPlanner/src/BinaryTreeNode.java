/**
 * My project Diet Planner has the purpose of giving you a detailed planner on what you should be eating based
 * on personal factors or preferences. Questions  include factors such as gender, weight, amount of exercise,
 * type of exercise,  and more. Based on this information, the program gives you the amount of macronutrients you
 * should be consuming, as well as exercise plans on this information.
 *
 * @author: Noah Babel
 * @version: 05/12/22
 */
public class BinaryTreeNode {
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private String val;
    //creates a binary tree node, with left and right getter and setter methods, and a getter value method
    public BinaryTreeNode(String val) {
        this.left = null;
        this.right = null;
        this.val = val;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public String getVal() {
        return val;
    }
}
