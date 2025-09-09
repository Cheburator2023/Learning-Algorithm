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
//        System.out.println(containsDuplicate1(nums, 3));
//        System.out.println(containsDuplicate1(nums1, 2));
//        System.out.println(containsDuplicate1(nums2, 1));
        String[] s = {"h", "e", "l", "l", "o"};
        String[] s1 = {"h", "e", "l", "l", "o", "!"};
        reverseString(s);
        reverseString(s1);
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

    public static void reverseString(String[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, rigth = s.length - 1;
        for (int i = 0; i < s.length/2 ; i++) {
            String temp = s[left];
            s[left++] = s[rigth];
            s[rigth--] = temp;
        }
        System.out.println(Arrays.toString(s));
    }
}
