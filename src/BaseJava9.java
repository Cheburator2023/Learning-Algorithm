import java.util.*;

public class BaseJava9 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        int[] stones = {2,7,4,1,8,1};
        System.out.println(findAnagrams(s,p));
        System.out.println(lastStoneWeight(stones));
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
