import java.util.*;

public class Game {
    static String[][] maze;

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Start and end position of the maze
    static int startRow, startCol;
    static int endRow, endCol;

    // Initialize the game
    void GameArray(String [][] mazeArray){
        maze = mazeArray;
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start(); // Start stopwatch
        Game(); // Start of game logic
        stopwatch.stop(); // End stopwatch
        System.out.println(stopwatch); // Print time elapsed
    }

    // The GAME METHOD
    static void Game() {
        findStartAndEnd(); // Find the start and end of the maze
        List<Node> path = findPath(); // Find path from start to end
        if (path != null) {
            System.out.println("\nPath to 'F':");
            // Marking the path of the file
            for (Node node : path) {
                maze[node.row][node.col] = "*";  // Mark the maze path
            }
            printMaze(); // Print the maze path
        } else {
            System.out.println("No path found.");
        }
    }

    // Method to find the maze 'S' and 'F'
    static void findStartAndEnd() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j].equals("S")) {
                    startRow = i;
                    startCol = j;
                } else if (maze[i][j].equals("F")) {
                    endRow = i;
                    endCol = j;
                }
            }
        }
    }

    // Method to find the path
    static List<Node> findPath() {
        Deque<Node> openSet = new ArrayDeque<>(); // To store open nodes
        Set<String> closedSet = new HashSet<>(); // To store closed nodes
        Map<String, Node> allNodes = new HashMap<>(); // To store all nodes

        Node startNode = new Node(startRow, startCol, 0, heuristic(startRow, startCol, endRow, endCol), null);
        openSet.addLast(startNode);
        allNodes.put(nodeKey(startRow, startCol), startNode);
        closedSet.add(nodeKey(startRow, startCol));

        Node goalNode = null;
        List<String> log = new ArrayList<>();

        while (!openSet.isEmpty()) {
            Node current = openSet.pollFirst();

            if (current.row == endRow && current.col == endCol) {
                goalNode = current;
                break;
            }

            for (int i = 0; i < directions.length; i++) {
                int newRow = current.row;
                int newCol = current.col;

                // Move in the current direction until hitting an obstacle or maze boundary
                while (isValid(newRow + directions[i][0], newCol + directions[i][1]) &&
                        !maze[newRow + directions[i][0]][newCol + directions[i][1]].equals("0") &&
                        !maze[newRow + directions[i][0]][newCol + directions[i][1]].equals("F")) {
                    newRow += directions[i][0];
                    newCol += directions[i][1];
                }

                // Stop if we find 'F'
                if (isValid(newRow + directions[i][0], newCol + directions[i][1]) &&
                        maze[newRow + directions[i][0]][newCol + directions[i][1]].equals("F")) {
                    newRow += directions[i][0];
                    newCol += directions[i][1];
                }

                // Check if the new position is valid and not visited
                if ((newRow != current.row || newCol != current.col) && !closedSet.contains(nodeKey(newRow, newCol))) {
                    String newNodeKey = nodeKey(newRow, newCol);
                    if (!closedSet.contains(newNodeKey)) {
                        Node neighbor = new Node(newRow, newCol, current.gCost + 1, heuristic(newRow, newCol, endRow, endCol), current);
                        openSet.addLast(neighbor);
                        allNodes.put(newNodeKey, neighbor);
                        closedSet.add(newNodeKey);
                    }
                }

                // If the current move hits an obstacle or boundary, change direction
                if (!isValid(newRow + directions[i][0], newCol + directions[i][1]) || maze[newRow + directions[i][0]][newCol + directions[i][1]].equals("0")) {

                    for (int j = 0; j < directions.length; j++) {
                        if (j != i) {
                            int tempRow = current.row + directions[j][0];
                            int tempCol = current.col + directions[j][1];
                            if (isValid(tempRow, tempCol) && !maze[tempRow][tempCol].equals("0") && !closedSet.contains(nodeKey(tempRow, tempCol))) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (goalNode != null) {
            Node traceNode = goalNode;
            Stack<Node> pathStack = new Stack<>();
            while (traceNode != null) {
                pathStack.push(traceNode);
                traceNode = traceNode.parent;
            }

            int step = 1;
            while (!pathStack.isEmpty()) {
                Node stepNode = pathStack.pop();
                log.add(step + ". Move to (" + stepNode.row + ", " + stepNode.col + ")");
                step++;
            }
        } else {
            log.add("No path found.");
        }

        for (String entry : log) {
            System.out.println(entry);
        }

        if (goalNode != null) {
            return reconstructPath(goalNode);
        } else {
            return null;
        }

    }

    // Method to reconstruct the path
    static List<Node> reconstructPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    // Method to check if the row and column is valid or not
    static boolean isValid(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length;
    }

    // Method to calculate the h value
    static int heuristic(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }

    static String nodeKey(int row, int col) {
        return row + "," + col;
    }

    static void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    // The node class
    static class Node {
        int row, col, gCost, hCost;
        Node parent;

        Node(int row, int col, int gCost, int hCost, Node parent) {
            this.row = row;
            this.col = col;
            this.gCost = gCost;
            this.hCost = hCost;
            this.parent = parent;
        }
    }
}
