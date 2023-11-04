/**
 * A class that represents a single cell of the maze.
 * @author Ms. Namasivayam
 * @version 03/04/2022
 */

public class MazeCell {
    private boolean visited;
    private boolean isWall;
    private MazeCell parent;
    private int row;
    private int col;

    public MazeCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isWall = false;
        this.visited = false;
        this.parent = null;
    }

    /** Getters and Setters **/
    public boolean isVisited() {
        return visited;
    }

    public boolean isWall() {
        return isWall;
    }

    public MazeCell getParent() {
        return parent;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setParent(MazeCell parent) {
        this.parent = parent;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }
}
