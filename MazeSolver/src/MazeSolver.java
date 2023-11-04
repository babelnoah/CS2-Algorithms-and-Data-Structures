/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/04/2022
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> cellStack = new Stack<MazeCell>();
        ArrayList<MazeCell> cells = new ArrayList<>();
        MazeCell cell = this.maze.getEndCell();
        while (cell != this.maze.getStartCell()) {
            cellStack.push(cell);
            cell = cell.getParent();
        }
        cellStack.push(this.maze.getStartCell());
        int cellStackSize = cellStack.size();
        for(int i = 0; i < cellStackSize;i++) {
            cells.add(cellStack.pop());
        }
        return cells;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: DFS to solve the maze
        Stack<MazeCell> toVisit = new Stack<MazeCell>();
        ArrayList<MazeCell> explored = new ArrayList<>();
        int cellsExplored = 0;

        MazeCell cell = this.maze.getStartCell();
        while(cell != maze.getEndCell()) {
            if (this.maze.isValidCell(cell.getRow() + 1, cell.getCol())) {
                toVisit.push(this.maze.getCell(cell.getRow() + 1, cell.getCol()));
                maze.getCell(cell.getRow() + 1, cell.getCol()).setVisited(true);
            }
            if (this.maze.isValidCell(cell.getRow(), cell.getCol() + 1)) {
                toVisit.push(this.maze.getCell(cell.getRow(), cell.getCol() + 1));
                maze.getCell(cell.getRow(), cell.getCol()+1).setVisited(true);
            }
            if (this.maze.isValidCell(cell.getRow() - 1, cell.getCol())) {
                toVisit.push(this.maze.getCell(cell.getRow() - 1, cell.getCol()));
                maze.getCell(cell.getRow() - 1, cell.getCol()).setVisited(true);
            }
            if (this.maze.isValidCell(cell.getRow(), cell.getCol() - 1)) {
                toVisit.push(this.maze.getCell(cell.getRow(), cell.getCol() - 1));
                maze.getCell(cell.getRow(), cell.getCol()-1).setVisited(true);
            }

            MazeCell temp = cell;
            cell = toVisit.pop();
            cell.setParent(temp);
            cellsExplored += 1;
        }
        System.out.println("Cells Explored: " + cellsExplored);
        return getSolution();

    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: BFS to solve the maze

        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        ArrayList<MazeCell> ordered = new ArrayList<MazeCell>();
        int cellsExplored = 0;
        MazeCell currCell = maze.getStartCell();
        while(currCell != maze.getEndCell()) {
            int row = currCell.getRow();
            int col = currCell.getCol();

            if(maze.isValidCell(row-1,col)) {
                cellsToVisit.add(maze.getCell(row -1,col));
                maze.getCell(row-1,col).setVisited(true);
            }

            if (maze.isValidCell(row,col+1)) {
                cellsToVisit.add(maze.getCell(row,col +1));
                maze.getCell(row,col+1).setVisited(true);
            }

            if(maze.isValidCell(row+1,col)) {
                cellsToVisit.add(maze.getCell(row-1,col));
                maze.getCell(row+1,col).setVisited(true);
            }

            if(maze.isValidCell(row,col-1)) {
                cellsToVisit.add(maze.getCell(row,col-1));
                maze.getCell(row,col-1).setVisited(true);
            }

            MazeCell temp = currCell;
            currCell = cellsToVisit.remove();
            currCell.setParent(temp);
            cellsExplored = cellsExplored + 1;
        }
        System.out.println("Cells Explored: " + cellsExplored);
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("data/maze1.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
