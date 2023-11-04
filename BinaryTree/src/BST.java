/**
 * An Integer Binary Search Tree
 * @author: Noah Babel
 * @version: 3/30
 */

public class BST {
    private BSTNode root;

    public BST(BSTNode root) {
        this.root = root;
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // TODO - You will have to replace the provided return statement
        BSTNode root = this.root;
        root = searchHelper(root, val);
        if (root!= null){
            return true;
        }
        else{
            return false;
        }
    }

    private BSTNode searchHelper(BSTNode root, int val){
        if (root==null || root.getVal()==val){
            return root;
        }

        if (root.getVal() > val){
            return searchHelper(root.getLeft(), val);
        }
        return searchHelper(root.getRight(), val);
    }

    /**
     * A function that returns a string of the inorder
     * traversal of a binary tree.
     * Each node on the tree should be followed by a '-'.
     *  Ex. "1-2-3-4-5-"
     */

    public String getInorder() {
        // TODO - You will have to replace the provided return statement
        return getInorderHelper(this.root);
    }

    private String getInorderHelper(BSTNode node) {
        String str = "";
        if (node == null) {
            return str;
        }

        str = str + getInorderHelper(node.getLeft());
        str = str + node.getVal() + "-";
        str = str + getInorderHelper(node.getRight());
        return str;
    }

    /**
     * A function that returns a string of the preOrder
     * traversal of a binary tree.
     * Each node on the tree should be followed by a '-'.
     *  Ex. "1-2-3-4-5-"
     */
    public String getPreorder() {
        // TODO - You will have to replace the provided return statement
        return getPreorderHelper(this.root);
    }
    private String getPreorderHelper(BSTNode node) {
        String str = "";
        if (node == null) {
            return str;
        }
        str = str + node.getVal() + "-";
        str = str + getPreorderHelper(node.getLeft());
        str = str + getPreorderHelper(node.getRight());
        return str;
    }

    /**
     * A function that returns a string of the postOrder
     * traversal of a binary tree.
     * Each node on the tree should be followed by a '-'.
     *  Ex. "1-2-3-4-5-"
     */
    public String getPostorder() {
        // TODO - You will have to replace the provided return statement
        return getPostorderHelper(this.root);
    }

    private String getPostorderHelper(BSTNode node) {
        String str = "";
        if (node == null) {
            return str;
        }

        str = str + getPostorderHelper(node.getLeft());
        str = str + getPostorderHelper(node.getRight());
        str = str + node.getVal() + "-";
        return str;
    }
    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // TODO
        BSTNode root = this.root;
        this.root = insertHelper(root, val);
    }

    private BSTNode insertHelper(BSTNode root, int val){
        if (root == null) {
            return new BSTNode(val);
        }

        if (val < root.getVal()){
            root.setLeft(insertHelper(root.getLeft(), val));
        }
        else if (val > root.getVal()){
            root.setRight(insertHelper(root.getRight(), val));
        }
        return root;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BSTNode root = new BSTNode(10);
        root.setLeft(new BSTNode(5));
        root.setRight(new BSTNode((15)));
        root.getLeft().setLeft(new BSTNode(3));
        root.getLeft().setRight(new BSTNode(9));

        BST tree = new BST(root);

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("Preorder traversal of binary tree is");
        System.out.println(tree.getPreorder());

        System.out.println("\nInorder traversal of binary tree is");
        System.out.println(tree.getInorder());

        System.out.println("\nPostorder traversal of binary tree is");
        System.out.println(tree.getPostorder());

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        System.out.println(tree.getInorder());
    }
}
