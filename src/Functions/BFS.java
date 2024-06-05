package Functions;

import Class.*;

import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    public static void iteration(Node p_node) {
        System.out.println("Current Node: "+ p_node.value);

        HashSet<Node> set_passed = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        set_passed.add(p_node);
        queue.addLast(p_node);

        while(!queue.isEmpty()) {
            Node node = queue.removeFirst();

            for (Edge edge : node.edges) {
                if (!set_passed.contains(edge.pointerNode)) {
                    queue.addLast(edge.pointerNode);
                    set_passed.add(edge.pointerNode);
                }
            }
        }


    }
    // Допилить
    private static PathNode findShortestPath(Node p_start_node, Node p_end_node) {
        HashSet<Node> set_passed = new HashSet<>();
        LinkedList<PathNode> queue = new LinkedList<>();
        queue.addLast(new PathNode(p_start_node, null));

        set_passed.add(p_start_node);

        while (!queue.isEmpty()) {
            PathNode pathNode = queue.removeFirst();
            if (pathNode.node == p_end_node) return pathNode;

            for (Edge edge : pathNode.node.edges) {
                if (set_passed.contains(edge.pointerNode)) continue;

                if (edge.pointerNode == p_end_node) return new PathNode(edge.pointerNode, pathNode);

                queue.addLast(new PathNode(edge.pointerNode, pathNode));
                set_passed.add(edge.pointerNode);
            }
        }
        return null;
    }
}
