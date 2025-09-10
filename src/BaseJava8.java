import java.util.*;

public class BaseJava8 {
    public static void main(String[] args) {
//        String parentheses = "({[]})";
//        String parentheses1 = "([]";
//        System.out.println(isValidParentheses(parentheses));
//        System.out.println(isValidParentheses(parentheses1));
//        int[] nums = {1, 2, 3, 1};
//        int[] nums1 = {1, 2, 3, 1, 2, 3};
//        int[] nums2 = {1, 1};
        int[] nums = {7, 1, 9, 3, 4, 1, 5, 6, 9};
        int[] nums1 = {2,1};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
//        System.out.println(containsDuplicate1(nums, 3));
//        System.out.println(containsDuplicate1(nums1, 2));
//        System.out.println(containsDuplicate1(nums2, 1));
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
//        char[] s1 = {'h', 'e', 'l', 'l', 'o', '!'};
//        reverseString(s);
//        reverseString(s1);
//        System.out.println(Arrays.toString(s));
//        System.out.println(Arrays.toString(s1));
        System.out.println(bestTimeToBuyAndSellStockII(nums));
        System.out.println(fibonacciNumber(2));
    }

    public static int fibonacciNumber(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return n;
        }
        return fibonacciNumber(n - 1) + fibonacciNumber(n - 2);
    }

    public static int fibonacciNumber1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }

        return b;
    }

    public static int bestTimeToBuyAndSellStockII(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                maxProfit += nums[i] - nums[i-1];
            }
        }
        return maxProfit;
    }

    public static int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int result = -1;
        int high = nums.length - 1;
        for (int low = 0; low < high ; low++) {
            int mid = low + (high - low)/2;
            if(nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {

            }
        }
        return result;
    }

    public static boolean isValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()){
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char lastCharInStack = stack.pop();
                if (c == ')' && lastCharInStack != '(') {
                    return false;
                } else if (c == '}' && lastCharInStack != '{') {
                    return false;
                } else if (c == ']' && lastCharInStack != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean containsDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int length = nums.length;
        Map<Integer,Integer> map = new HashMap<>(length, 1.0f);
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                int indexDiff = i - map.get(nums[i]);
                if (indexDiff <= k) {
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }

    public static boolean containsDuplicate1(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int length = nums.length;
        Set<Integer> set = new HashSet<>(length, 1.0f);
        for (int rigth = 0; rigth < length; rigth++) {
            int left = rigth - k - 1;
            if (rigth > k) {
                set.remove(nums[left]);
            } else if (!set.add(nums[rigth])){
                return true;
            }
        }
        return false;
    }

    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        for (int i = 0; i < s.length/2 ; i++) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
