import java.util.*;
import Class.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        int[][] file = new int[][]{
                {1, 2, 2}
                , {2, 3, 2}
                , {3, 4, 0}
                , {4, 1, 2}
                //, {4, 5, 10}

                , {1, 5, 2}
                , {5, 6, 100}
                , {6, 7, 0}
                , {7, 5, 0}

        };


        int i_what_we_want_find = 7;

        HashMap<Integer, Node> main_hash = Graph.create(file);

        System.out.println("----------- вывод графа recursion:");

        HashSet<Integer> passed = new HashSet<>();
        DFS.recursion(main_hash.get(1), passed);

        System.out.println("----------- вывод графа stack:");
        HashSet<Node> passed_2 = new HashSet<>();
        DFS.iterator(main_hash.get(1), passed_2);

        System.out.println("----------- поиск one way:");
        HashSet<Node> set_passed_3 = new HashSet<>();
        LinkedList<Node> set_way = new LinkedList<>();
        boolean is_way_found = Path.recursion(main_hash.get(1), main_hash.get(i_what_we_want_find), set_passed_3, set_way);
        System.out.println("is way found? - "+ is_way_found);
        for (Node node : set_way) {
            System.out.println("LinkedList: "+ node.value);
        }

        System.out.println("----------- поиск one way функция-обертка:");
        LinkedList<Node> set_way2 = new LinkedList<>();
        set_way2 = Path.recursion_one(main_hash.get(1), main_hash.get(i_what_we_want_find));
        for (Node node : set_way2) {
            System.out.println("LinkedList: "+ node.value);
        }

        System.out.println("----------- поиск one way через эмуляцию стека:");
        List<Node> stack = new Stack<>();
        stack = Path.iteration_one(main_hash.get(1), main_hash.get(i_what_we_want_find));
        for (Node node : stack) {
            System.out.println("Stack: "+ node.value);
        }

        System.out.println("----------- рекурсивный поиск всех путей:");
        LinkedHashSet<Node> passed_3 = new LinkedHashSet<>();
        List<LinkedHashSet<Node>> paths_0 = new ArrayList<LinkedHashSet<Node>>();
        Path.recursion_all(main_hash.get(1), main_hash.get(i_what_we_want_find), passed_3, paths_0);

        int i = 0;
        for (LinkedHashSet list : paths_0) {
            System.out.println("    "+ i +":");
            for (Object node : list) {
                System.out.println(((Node) node).value);
            }
            i++;
        }


        System.out.println("----------- итеративный поиск всех путей:");

        List<List<Node>> paths_2 = new ArrayList<List<Node>>();
        Path.iteration_all(main_hash.get(1), main_hash.get(i_what_we_want_find), paths_2);

        i = 0;
        for (List list : paths_2) {
            System.out.println("    "+ i +":");
            for (Object node : list) {
                System.out.println(((Node) node).value);
            }
            i++;
        }

        int[][] file2 = new int[][]{
                {1, 2, 2}
                , {1, 3, 2}
                , {2, 4, 0}
                , {4, 1, 2}

        };



        HashMap<Integer, Node> main_hash2 = Graph.create(file2);

        System.out.println("----------- проверяем на цикл:");
        LinkedHashSet<Node> out_path_to_cycle = new LinkedHashSet<>();
        LinkedHashSet<Node> has_children = new LinkedHashSet<>();
        LinkedHashSet<Integer> set_passed = new LinkedHashSet<>();
        HashSet<Node> out_node_start_cycle = new HashSet<>();



        LoopSearch.recursion(main_hash2.get(1), set_passed, has_children, out_path_to_cycle, out_node_start_cycle);

        LoopSearch.cutCycles(out_path_to_cycle, out_node_start_cycle);

        for (Node node : out_path_to_cycle) {
            System.out.println("    --Cycle: " + node.value);
        }
        System.out.println("    --Cycle: " + out_node_start_cycle.iterator().next().value);

        /*
            Итеративная реализация поиска цикла
            -- нет необходимости
         */


        /*
            Поиск всех циклов
         */
        System.out.println("----------- поиск всех циклов:");
        List<List<Node>> cycles = new ArrayList<>();
        cycles = LoopSearch.getAllCyclesPaths(main_hash);
        i = 0;
        for (List list : cycles) {
            System.out.println("    "+ i +":");
            for (Object node : list) {
                System.out.println(((Node) node).value);
            }
            i++;
        }

        /*
            Топологическая сортировка

         */
        System.out.println("----------- Topological Sort:");
        ArrayList<Node> cycles_in_graph = LoopSearch.topologicalSort(main_hash);
        if (cycles_in_graph == null) {
            System.out.println("Graph has cycles");
        } else {
            System.out.println("Graph has no cycles");
        }

        /* Задача 1. */

    }
}

