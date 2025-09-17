import java.util.*;

public class BaseJava10 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3};
        int[][] intervals = {{1, 3}, {0, 9}, {8, 10}, {15, 18}};
        int[][] intervals1 = {{0, 4}, {1, 5}, {8, 10}, {15, 18}};
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 20;
//        System.out.println(search(nums, 0));
//        printArrays(merge(intervals));
//        System.out.println();
//        printArrays(merge(intervals1));
        System.out.println(searchMatrix1(matrix,target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1) {
            return false;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1) {
            return false;
        }

        int row = 0, col = matrix[0].length - 1;

        while (0 <= row && row <= matrix.length - 1 && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--;
            }
            else {
                row++;
            }
        }
        return false;
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);
        for (int[] interval : intervals) {
            if (interval[0] <= current[1]) { // перекрывается ли текущий интервал из результата с рассматриваемым интервалом
                // если начало нового интервала меньше или равно концу текущего, значит, они перекрываются
                current[1] = Math.max(current[1], interval[1]); // расширяем конец текущего интервала только если новый интервал заканчивается позже
            } else {
                current = interval;
                result.add(current);
            }
        }
        return result.toArray(new int[(result.size())][]);
    }

    private static void printArrays(int[][] nums) {
        for (int[] i : nums) {
            System.out.print(Arrays.toString(i));
        }
    }

    public static int missingNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return expectedSum - actualSum;
    }

    public static int missingNumber1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        for (int i = 0; i < nums.length + 1; i++) {
            if (set.add(i)) {
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
        while (left <= right) {
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
