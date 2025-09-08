import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseJava7 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7};
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(isContainsDuplicate2(nums));
        System.out.println(isContainsDuplicate2(nums1));
        System.out.println(isContainsDuplicate2(nums2));

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
}
