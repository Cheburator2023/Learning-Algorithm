import java.util.*;

public class BaseJava9 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        //System.out.println(findAnagrams(s,p));
        //System.out.println(numIslands(grid));
        System.out.println(numIslands(grid));
        System.out.println(findRepeatedDnaSequences(s1));
        int[] stones = {2,7,4,1,8,1};
        int[] stones1 = {2,2,1,3,5,3};
        System.out.println(findAnagrams(s,p));
        System.out.println(lastStoneWeight(stones));
        System.out.println(lastStoneWeight(stones1));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new  ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        Map<String,Integer> map = new HashMap<>(s.length(), 1.0f);

        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            int count = map.getOrDefault(substring, 0) + 1;
            map.put(substring, count);
            if (map.get(substring) == 2) {
                    result.add(substring);
            }
        }
        return result;
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) {
            return count;
        }

        int n = grid.length;

        for (int i = 0; i < n; i++) {
            int m = grid[i].length;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    if (n > 200 || m > 200) {
                        bfs(grid, i, j);
                    } else {
                        dfs(grid, i, j);
                    }
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Проверка границ и условия воды/уже посещенной ячейки
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1') {
            return;
        }

        // Помечаем ячейку как посещенную (изменяем на '0')
        grid[i][j] = '0';

        // Рекурсивно посещаем всех соседей (4 направления)
        dfs(grid, i - 1, j); // Вверх
        dfs(grid, i + 1, j); // Вниз
        dfs(grid, i, j - 1); // Влево
        dfs(grid, i, j + 1); // Вправо
    }

    private static void bfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Создаем очередь для BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0'; // Помечаем как посещенную

        // Направления: вверх, вправо, вниз, влево
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // Проверяем всех соседей
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Если сосед в пределах сетки и это земля
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == '1') {

                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // Помечаем как посещенную
                }
            }
        }
    }

    private static void bFS(char[][] grid, int width, int depth) {
        Queue<Character> queue = new ArrayDeque<>(); // (2) Неверный тип очереди
        // Нужно хранить координаты ячеек (int[] или два числа), а не сами значения (Character).
        int n = grid.length;

        queue.add(grid[width][depth]); // (3) Добавляем значение, а не координаты
        // В очередь нужно класть координаты, чтобы потом по ним находить соседей.
        grid[width][depth]--; // (4) Опасное изменение
        // grid[width][depth]--; для char со значением '1' (код 49) даст '0' (код 48). Это работает, но ненадёжно.
        // Лучше явно присваивать '0'.

        while (!queue.isEmpty()) {
            int m = grid[width].length; // (5) m вычисляется внутри цикла для width, depth?
            // m (длина строки) должна вычисляться для конкретной ячейки. Строка int m = grid[width].length;
            // использует width и depth, которые меняются в цикле — это приводит к ошибкам.
            queue.poll(); // (6) Извлекли, но не сохранили результат
            // Результат извлечения не сохраняется и не используется — это бессмысленная операция.

            // (7) Ошибка алгоритма: меняются width и depth
            if ((width - 1 >= 0) && grid[width - 1][depth] == '1') {
                queue.add(grid[width - 1][depth]);
                grid[width - 1][depth]--;
                width--; // КРИТИЧЕСКАЯ ОШИБКА: изменение текущих координат
                // Изменение переменных width и depth в процессе обхода.
                // Это сбивает все дальнейшие проверки соседей и приводит к бесконечным циклам или выходу за границы.
            } else if ((width + 1 < n) && grid[width + 1][depth] == '1') {
                queue.add(grid[width + 1][depth]);
                grid[width + 1][depth]--;
                width++;
            } else if ((depth - 1 >= 0) && grid[width][depth - 1] == '1') {
                queue.add(grid[width][depth - 1]);
                grid[width][depth - 1]--;
                depth--;
            } else if ((depth + 1 < m) && grid[width][depth + 1] == '1') {
                queue.add(grid[width][depth + 1]);
                grid[width][depth + 1]--;
                depth++;
            }
        }
    }

    public static int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        if (stones.length == 1) {
            return stones[0];
        }
        Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            heap.add(stone);
        }
        while (heap.size() > 1) {
            int stone1 = heap.poll();
            if (!heap.isEmpty()) {
                int stone2 = heap.poll();
                int newStone = Math.abs(stone1 - stone2);
                if (newStone > 0) {
                    heap.add(newStone);
                }
            }
        }
        return heap.isEmpty() ? 0 : heap.poll();
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length()){
            return result;
        }

        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        for(char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int n = s.length();
        int k = p.length();

        while (right < k) {
            windowCount[s.charAt(right) - 'a']++;
            right++;
        }

        if(Arrays.equals(pCount, windowCount)) {
            result.add(left);
        }

        while(right < n) {
            windowCount[s.charAt(left) - 'a']--;
            windowCount[s.charAt(right) - 'a']++;
            right++;
            left++;
            if(Arrays.equals(pCount, windowCount)) {
                result.add(left);
            }
        }
        return result;
    }
}
