import java.util.*;

public class BaseJava9 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        //char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        //char[][] grid1 = {{'1'}, {'1', '1', '0', '0', '0'}, {'0', '1', '0'}, {'1', '0',}, {'1'}};
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        //System.out.println(findAnagrams(s,p));
        //System.out.println(numIslands(grid));
        //System.out.println(numIslands(grid1));
        System.out.println(findRepeatedDnaSequences(s1));
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

        for (int i = 0; i < n ; i++) {
            int m = grid[i].length;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dFS(char[][] grid, int width, int depth) {
        Queue<Character> queue = new ArrayDeque<>();
        int n = grid.length;

        queue.add(grid[width][depth]);
        grid[width][depth]--;

        while (!queue.isEmpty()) {
            int m = grid[width].length;
            queue.poll();
            if ((width - 1 >= 0) && grid[width - 1][depth] == '1') {
                queue.add(grid[width - 1][depth]);
                grid[width - 1][depth]--;
                width--;
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
