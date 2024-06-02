package Functions;

import Class.*;

import java.util.*;

public class Path {

    public static boolean recursion(Node start, Node end, HashSet<Node> p_passed, LinkedList<Node> p_set_way) {
        if (start == end) {
            p_set_way.addFirst(start);
            return true;
        }

        p_passed.add(start);

        for (Edge edge : start.edges ) {
            if (!p_passed.contains(edge.pointerNode)) {
            if (recursion(edge.pointerNode, end, p_passed, p_set_way)) {
                p_set_way.addFirst(start);
                return true;
            }
            }
        }

        return false;
    }

    public static LinkedList<Node> recursion_one( Node p_start
            , Node p_end) {
        LinkedList<Node> path = new LinkedList<>();
        if (p_start == null || p_end == null) return path;
        recursion(p_start, p_end, new HashSet<>(), path);
        return path;
    }

    public static List<Node> iteration (  Node p_start
                                        , Node p_end) {

        Stack<Node> stack = new Stack<>(); // собственный стек
        HashSet<Node> passed = new HashSet<>();
        stack.push(p_start);
        Node node;


        while (stack.size() != 0) {

            node = stack.peek();
            if (node == p_end) return stack;

            if (!passed.contains(node)) {
                passed.add(node);
            }
            boolean b_hasChild = false;

            for (Edge edge : node.edges) {
                if (!passed.contains(edge.pointerNode)) { // либо узлы, либо ребра
                    stack.push(edge.pointerNode);
                    b_hasChild = true;
                    break;
                }
            }
            if (!b_hasChild) stack.pop();
        }
        return stack;
    }


    public static List<Node> iteration_one( Node p_start
                                                , Node p_end) {
        List<Node> path = new Stack<>();
        if (p_start == null || p_end == null) return path;

        path = iteration(p_start, p_end);

        return path;
    }


    public static void recursion_all( Node                      p_start
                                    , Node                      p_end
                                    , LinkedHashSet<Node>       p_has_child_nodes
                                      // 1. Функция стека, запомнить путь

                                    , List<LinkedHashSet<Node>> p_out_paths) {      // OUT все пути
        if (p_start == p_end) {
            p_out_paths.add((LinkedHashSet<Node>) p_has_child_nodes.clone());
            p_out_paths.get(p_out_paths.size() - 1).add(p_end);
            return;
        }

        p_has_child_nodes.add(p_start);

        for (Edge edge : p_start.edges ) {
            if (!p_has_child_nodes.contains(edge.pointerNode)) {
                recursion_all(edge.pointerNode, p_end, p_has_child_nodes, p_out_paths);
            }
        }
        p_has_child_nodes.remove(p_start);

    }

    //  Не понял зачем passedNodes и passedEdges и зачем из них удалять ноды и узлы
    // и какую проблему это решает ваще
    public static void iteration_all( Node                      p_start
                                    , Node                      p_end
                                    , List<List<Node>>          p_out_paths) {
        // Множества, в которых Узел\ребро имеет непосещенных детей
        HashSet<Node> has_no_visit_child_Nodes = new HashSet<>();
        HashSet<Edge> has_no_visit_child_Edges = new HashSet<>();

        // Стек для цикла и для записи в p_out_paths
        Stack<Node> stack = new Stack<>();
        stack.push(p_start);
        Node cur_node;

        while (stack.size() != 0) {

            cur_node = stack.peek();    // +берем из стека последнюю ноду
            has_no_visit_child_Nodes.add(cur_node);  // +Нам надо запоминать их, потому что мы несколько раз вернемся к 1 узлу

            Node adjacentNode = null;  // +аналогично проверке на hasChildren

            for (Edge edge : cur_node.edges) {
                // passedNodes и passedEdges страхуют от цикла, страхуют от захода в посещенные
                // Но страхуют от этого всего каким образом? Не понятно
                if (!has_no_visit_child_Nodes.contains(edge.pointerNode) && !has_no_visit_child_Edges.contains(edge)) {

                    has_no_visit_child_Edges.add(edge);
                    stack.push(edge.pointerNode);

                    adjacentNode = edge.pointerNode;
                    break;
                }
            }
            if (adjacentNode == null) {
                stack.pop();                    // +прошли всех детей\не было детей? удаляем из стека
                has_no_visit_child_Nodes.remove(cur_node);
                for (Edge e : cur_node.edges) {
                    has_no_visit_child_Edges.remove(e);
                }
            }


            if (adjacentNode == p_end) {
                p_out_paths.add( (List<Node>) stack.clone() );
                stack.pop();
            }


        }
    }
}
