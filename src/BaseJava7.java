import java.util.*;

public class BaseJava7 {
    public static void main(String[] args) {
        int[] nums = {7, 1, 9, 3, 4, 1, 5, 6, 9};
        int[] nums1 = {2,1};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
//        System.out.println(isContainsDuplicate2(nums));
//        System.out.println(isContainsDuplicate2(nums1));
//        System.out.println(isContainsDuplicate2(nums2));
//        System.out.println(isAnagram1("cat", "tac"));
//        System.out.println(isAnagram1("cat", "dog"));
//        System.out.println(isAnagram1("cat", "c at"));
        System.out.println(bestTimeToBuyAndSellStock(nums));
        System.out.println(bestTimeToBuyAndSellStock(nums1));
        System.out.println(bestTimeToBuyAndSellStock1(nums));
        System.out.println(bestTimeToBuyAndSellStock1(nums1));

    }

    public static int bestTimeToBuyAndSellStock(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    maxProfit = Math.max(maxProfit,nums[j] - nums[i]);
                }
            }
        }
        return maxProfit;
    }

    public static int bestTimeToBuyAndSellStock1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            minPrice = Math.min(minPrice, nums[i]);
            int profit = nums[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    public static boolean isContainsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isContainsDuplicate1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        String string = Arrays.toString(nums);
        string = string.replaceAll("[^\\d,]", "").replace(",", "");

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (string.indexOf(c) != string.lastIndexOf(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContainsDuplicate2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.get(complement) != null) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char[] charS = getChars(s);
        char[] charT = getChars(t);
        if (charS.length != charT.length) {
            return false;
        }
        Arrays.sort(charS);
        Arrays.sort(charT);
        return Arrays.equals(charS, charT);
    }

    private static char[] getChars(String s) {
        return s.replaceAll("\\P{L}", "").toLowerCase().toCharArray();
    }

    public static boolean isAnagram1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char[] charS = getChars(s);
        char[] charT = getChars(t);
        if (charS.length != charT.length) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(charS.length, 1.0f);
        for (char c : charS) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : charT) {
            if (!map.containsKey(c)){
                return false;
            }
            if (map.get(c) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char[] charS = getChars(s);
        char[] charT = getChars(t);
        if (charS.length != charT.length) {
            return false;
        }
        int[] counts = new int[26];
        for (char c : charS) {
            counts[c - 'a']++; // 'a' - 'a' = 0, 'b' - 'a' = 1, ..., 'z' - 'a' = 25
        }
        for (char c : charT) {
            if (--counts[c - 'a'] < 0) {// Тут тоже нужно вычитать 'a'
                return false;
            }
        }
        return true;
    }
}
