import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseJava10 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,9,10,0,1,2,3};
        System.out.println(search(nums, 0));
        System.out.println(5^8);
        System.out.println(1^3);
        System.out.println(6^4);
    }
    public static int missingNumber(int[] nums) {
        if(nums == null) {
            return -1;
        }
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return expectedSum - actualSum;
    }

    public static int missingNumber1(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        for (int i = 0; i < nums.length + 1; i++) {
            if(set.add(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int missingNumber2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left<=right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) { // Левая половина отсортирована
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Ищем в левой половине
                } else {
                    left = mid + 1; // Ищем в правой половине
                }
            } else { // Правая половина отсортирована
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Ищем в правой половине
                } else {
                    right = mid - 1; // Ищем в левой половине
                }
            }

        }
        return -1;
    }
}
