package Tasks;

import java.util.ArrayList;

public class Tasks {
    public static boolean is_valid_cell(int[][]         p_matrix
                                        , boolean[][]   p_passed
                                        , int           p_row
                                        , int           p_col) {
        if ( (p_row >= 0 && p_row < p_passed.length)        // Не выходим за строки
            && (p_col >= 0 && p_col < p_passed[0].length)     // Не выходим за колонки
            && p_passed[p_row][p_col] != true                       // в пройденном массиве текущая ячейка false
            && p_matrix[p_row][p_col] == 0                     // текущая ячейка в матрице ноль
        ) return true;
        return false;
    }
    public static int get_island_size(int[][]       p_matrix
                                    , boolean[][]   p_passed
                                    , int           p_row
                                    , int           p_col) {
        if (!is_valid_cell(p_matrix, p_passed, p_row, p_col)) return 0;

        p_passed[p_row][p_col] = true;

        int ret_size = 1;
        ret_size += get_island_size(p_matrix, p_passed, p_row - 1, p_col - 1);      // верх лево
        ret_size += get_island_size(p_matrix, p_passed, p_row - 1, p_col);                // центр верх
        ret_size += get_island_size(p_matrix, p_passed, p_row - 1, p_col + 1);      // верх право
        ret_size += get_island_size(p_matrix, p_passed, p_row, p_col - 1);              // центр лево
        ret_size += get_island_size(p_matrix, p_passed, p_row, p_col + 1);              // центр право
        ret_size += get_island_size(p_matrix, p_passed, p_row + 1, p_col - 1); // лево низ
        ret_size += get_island_size(p_matrix, p_passed, p_row + 1, p_col);           // центр низ
        ret_size += get_island_size(p_matrix, p_passed, p_row + 1, p_col + 1); // право низ

        return ret_size;
    }
    public static ArrayList<Integer> get_island_size(int[][] p_matrix) {
        ArrayList<Integer> ret_arr = new ArrayList<>();
        boolean[][] passed = new boolean[p_matrix.length][p_matrix[0].length];
        /*
        Проходим по каждому элементу в матрице, вызываем функцию
        Если наткнулись на ноль, начинаем вокруг него вызывать эту же функцию в каждой ячейке
            Но сразу начнутся проблемы:
                1- Можем выйти за пределы матрицы
                2- Будем обходить те узлы, которые уже обходили
            Решение:
                1- Проверять что бы не выходили
                2- Создать дубляж матрицы с булеан значениями, помечать клетки с нулем
         */
        for (int i_row = 0; i_row < p_matrix.length; i_row++) {
            for (int i_col = 0; i_col < p_matrix[i_row].length; i_col++) {
                int cur_val = get_island_size(p_matrix, passed, i_row, i_col);
                if (cur_val > 0) ret_arr.add(cur_val);

            }
        }

        return ret_arr;
    }


}
