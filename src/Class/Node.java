package Class;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Node {
    public int value;

    public LinkedHashSet<Edge> edges = new LinkedHashSet<>();

    // нужна для поиска всех циклов в графе
    public LinkedHashMap<Node, Edge> parents = new LinkedHashMap<>();
    public Node(int value) {
        this.value = value;
    }
}
