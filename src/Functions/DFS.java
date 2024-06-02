package Functions;
import Class.*;

import java.util.HashSet;
import java.util.Stack;
/*


 */
public class DFS { // Deep First Search
    // p_passed - посещенные
    // p_passed - Зашел в узел, проверил был ли ты в нем, если нет, добавил в p_passed
    public static void recursion (Node p_node, HashSet<Integer> p_passed) {

        System.out.println("recursion.node: "+p_node.value);
        p_passed.add(p_node.value);

        for (Edge edge : p_node.edges) {
            if (!p_passed.contains(edge.pointerNode.value)) {
                recursion(edge.pointerNode, p_passed);
            }
        }
    }

    public static void iterator (Node p_node, HashSet<Node> p_passed) {
        Stack<Node> stack = new Stack<>(); // собственный стек
        stack.push(p_node);


        while (stack.size() != 0) {

            p_node = stack.peek();

            if (!p_passed.contains(p_node)) {
                System.out.println("iterator: "+p_node.value);
                p_passed.add(p_node);
            }
            boolean b_hasChild = false;

            for (Edge edge : p_node.edges) {
                if (!p_passed.contains(edge.pointerNode)) { // либо узлы, либо ребра
                    stack.push(edge.pointerNode);
                    b_hasChild = true;
                    break;
                }
            }
            if (!b_hasChild) stack.pop();
        }

    }
}
