import java.util.ArrayList;
import java.util.List;

public class BaseJava6 {
    public static void main(String... args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(quickSelect(nums, k));
    }

    public static int quickSelect(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i: nums) {
            list.add(i);
        }
        return list.get(list.size() -k - 1);
    }
}
