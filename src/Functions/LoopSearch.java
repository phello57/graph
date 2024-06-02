package Functions;
import Class.*;

import java.util.*;

public class LoopSearch {
    // p_passed - посещенные
    // p_passed - Зашел в узел, проверил был ли ты в нем, если нет, добавил в p_passed
    public static void recursion(Node                     p_node
                                , LinkedHashSet<Integer>  p_nodes_cant_be_loop
                                , LinkedHashSet<Node>     p_could_be_loop_nodes
                                , LinkedHashSet<Node>     p_out_path_to_cycle
                                , HashSet<Node> p_out_cycle_start_node) {

        /*
            . Цикл в графе - это возврат к предыдущему узлу
            . Если у узла все дети изучены или детей вообще нет - то узел не может быть зациклен

            p_nodes_cant_be_loop - узлы, у которых все дети не имеют ссылкок в p_could_be_loop_nodes
            p_could_be_loop_nodes - узлы в которых сейчас находится рекурсия и они имеют непройденных детей
         */

        if (p_could_be_loop_nodes.contains(p_node)) {
            p_out_path_to_cycle.addAll((LinkedHashSet<Node>) p_could_be_loop_nodes.clone());

            p_out_cycle_start_node.add(p_node);

            return; // иначе зациклимся
        }

        p_could_be_loop_nodes.add(p_node);

        for (Edge edge : p_node.edges) {
            if (!p_nodes_cant_be_loop.contains(edge.pointerNode.value)) {
                recursion(edge.pointerNode, p_nodes_cant_be_loop, p_could_be_loop_nodes, p_out_path_to_cycle, p_out_cycle_start_node);
            }
        }

        p_nodes_cant_be_loop.add(p_node.value);
        /* -----p_passed.add(p_node.value);-----
            2 узла могут вести к 1 узлу, если мы уже были в этом узле, идти заного в него нет смысла
            для этого мы помечаем его пройденным
         */

        p_could_be_loop_nodes.remove(p_node);
        /* -----p_could_be_loop_nodes.remove(p_node);-----
            Так как рекурсия схлопывается, то мы попадем на предыдущий узел, а он у нас записан и будет
        выявлена рекурсия. Это не должно так работать. Из за этого удаляем его уже после цикла по всем ребрам
        согласно 2 посылке
         */
    }
    public static LinkedHashSet<Node> cutCycles(LinkedHashSet<Node> p_out_full_path, HashSet<Node> p_where_cycle_start) {
        Node start_cycle_node = p_where_cycle_start.iterator().next();

        while (p_out_full_path.iterator().hasNext()) {
            Node node = p_out_full_path.iterator().next();

            if (node == start_cycle_node) {
                return p_out_full_path;
            }
            p_out_full_path.remove(node);
        }
        return p_out_full_path;
    }

    public static void restoreGraphEdges(HashMap<Node, List<Edge>> p_deletedEdges) {

        for (Map.Entry<Node, List<Edge>> entry : p_deletedEdges.entrySet()) {
            Node node = entry.getKey();
            List<Edge> edges = entry.getValue();

            node.edges.addAll(edges);
        }
    }
    /*
    List<LinkedList<Node>> getAllCyclesPaths(HashMap<Integer, Node> graph) {
        HashMap<Node, List<Edge>> deletedEdges = new HashMap();
        List<LinkedList<Node>> cycles = new ArrayList<>();

        for (Map.Entry<Integer, Node> nodePair : graph.entrySet()) {
            Node node = nodePair.getValue();
            for (Map.Entry<Node, Edge> entry : node.parents.entrySet()) {
                Node parentNode = entry.getKey();
                Edge parentEdge = entry.getValue();
                List<List<Node>> out_cyclePaths = new ArrayList<>();

                Path.iteration_all(node, parentNode, out_cyclePaths);

                cycles.addAll(out_cyclePaths);

                parentNode.edges.remove(parentEdge);
                if (!deletedEdges.containsKey(parentNode)) {
                    deletedEdges.put(parentNode, new ArrayList<>());
                }
                deletedEdges.get(parentNode).add(parentEdge);
            }
        }
        restoreGraphEdges(graph, deletedEdges);
        return cycles;
    }
    */
}
