import java.util.*;

public class Game {
    int current_column;
    int current_row;
    int goal_column;
    int goal_row;

    Game(){}

    // To find the S and the F and assign them to the variables
    void startAndEnd(String[][] mazeArray){
        for (int row = 0; row < mazeArray.length; row++){
            for (int col = 0; col < mazeArray[row].length; col++){
                if (Objects.equals(mazeArray[row][col], "S")) {
                    current_row = row;
                    current_column = col;
                } else if (Objects.equals(mazeArray[row][col], "F")) {
                    goal_row = row;
                    goal_column = col;
                }
            }
        }
    }

    void GameArray(String[][] mazeArray){
        startAndEnd(mazeArray);
        List<Node> path = findPath(mazeArray);
        printPath(path);
    }

    List<Node> findPath(String[][] mazeArray) {
        PriorityQueue<Node> openNodes = new PriorityQueue<>();
        boolean[][] closedNodes = new boolean[mazeArray.length][mazeArray[0].length];

        // Add start node to the open node list
        Node startNode = new Node(current_row, current_column, 0, heuristic(current_row, current_column, goal_row, goal_column), null);
        openNodes.add(startNode);

        while (!openNodes.isEmpty()) {
            Node currentNode = openNodes.poll();
            closedNodes[currentNode.row][currentNode.col] = true;

            if (currentNode.row == goal_row && currentNode.col == goal_column) {
                return constructPath(currentNode);
            }

            for (Node neighbor : getNeighbors(currentNode, mazeArray, closedNodes)) {
                if (closedNodes[neighbor.row][neighbor.col]) continue;

                int newGCost = currentNode.gCost + 1;
                if (newGCost < neighbor.gCost || !openNodes.contains(neighbor)) {
                    neighbor.gCost = newGCost;
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = currentNode;
                    openNodes.add(neighbor);
                }
            }
        }

        return null; // No path found
    }

    List<Node> getNeighbors(Node node, String[][] mazeArray, boolean[][] closedSet) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newRow = node.row;
            int newCol = node.col;

            while (isInBounds(newRow, newCol, mazeArray) && !Objects.equals(mazeArray[newRow][newCol], "0")) {
                newRow += direction[0];
                newCol += direction[1];
            }

            newRow -= direction[0];
            newCol -= direction[1];

            if (!closedSet[newRow][newCol]) {
                int hCost = heuristic(newRow, newCol, goal_row, goal_column);
                neighbors.add(new Node(newRow, newCol, node.gCost + 1, hCost, node));
            }
        }

        return neighbors;
    }

    boolean isInBounds(int row, int col, String[][] mazeArray) {
        return row >= 0 && row < mazeArray.length && col >= 0 && col < mazeArray[0].length;
    }

    int heuristic(int row, int col, int goalRow, int goalCol) {
        return Math.abs(row - goalRow) + Math.abs(col - goalCol);
    }

    List<Node> constructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    void printPath(List<Node> path) {
        if (path == null) {
            System.out.println("No path found.");
            return;
        }

        Node startNode = path.get(0);
        System.out.printf("1. Start at (%d, %d)\n", startNode.row + 1, startNode.col + 1);

        for (int i = 1; i < path.size(); i++) {
            Node node = path.get(i);
            System.out.printf("%d. Move to (%d, %d)\n", i + 1, node.row + 1, node.col + 1);
        }
        System.out.println("Done!");
    }
}
