package Class;

public class Edge {
    public int weight;
    public Node pointerNode;

    public Edge(Node pointerNode, int weight) {
        this.weight = weight;
        this.pointerNode = pointerNode;
    }
}
