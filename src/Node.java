class Node implements Comparable<Node>{
    int row, col;
    int gCost, hCost, fCost;
    Node parent;

    Node(int row, int col, int gCost, int hCost, Node parent) {
        this.row = row;
        this.col = col;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost, other.fCost);
    }

}
