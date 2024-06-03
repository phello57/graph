package Functions;
import Class.*;
import java.util.HashMap;
/*
    Создание графа по int[][]
 */
public class Graph {
    static Node add_or_get_node(int value, HashMap<Integer, Node> p_hash) {
        if (value == -1) return null;
        if (p_hash.containsKey(value)) {
            return p_hash.get(value);
        }

        //System.out.println("add node:  "+value);

        Node node = new Node(value);
        p_hash.put(value, node);
        return node;
    }
    static Node add_or_get_node(String value, HashMap<String, Node> p_hash) {
        if (value.equals("-1")) return null;
        if (p_hash.containsKey(value)) {
            return p_hash.get(value);
        }

        //System.out.println("add node:  "+value);

        Node node = new Node(value);
        p_hash.put(value, node);
        return node;
    }
    public static HashMap<Integer, Node> create(int[][] p_file) {

        HashMap<Integer, Node> ret_main_hash = new HashMap<Integer, Node>();

        for (int[] arr : p_file) {
            int i_node_value = arr[0];
            int i_node_pointer = arr[1];
            int i_weight_edge = arr[2];

            Node node = add_or_get_node(i_node_value, ret_main_hash);
            Node pointer_node = add_or_get_node(i_node_pointer, ret_main_hash);

            if (pointer_node == null) continue;
            Edge edge = new Edge(pointer_node, i_weight_edge);

            node.edges.add(edge);
            pointer_node.parents.put(node, edge);

            // System.out.println("From: "+node.value+" to "+pointer_node.value+" with weight= "+i_weight_edge);
        }
        return ret_main_hash;
    }
    public static HashMap<String, Node> create(String[][] p_file) {

        HashMap<String, Node> ret_main_hash = new HashMap<>();

        for (String[] arr : p_file) {
            String i_node_value = arr[0];
            String i_node_pointer = arr[1];

            Node node = add_or_get_node(i_node_value, ret_main_hash);
            Node pointer_node = add_or_get_node(i_node_pointer, ret_main_hash);

            if (pointer_node == null) continue;
            Edge edge = new Edge(pointer_node);

            node.edges.add(edge);
            pointer_node.parents.put(node, edge);
            //System.out.println("From: "+node.s_value+" to "+pointer_node.s_value);

        }
        return ret_main_hash;
    }
}
