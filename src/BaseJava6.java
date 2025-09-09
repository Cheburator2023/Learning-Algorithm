import java.util.*;

public class BaseJava6 {
    public static void main(String... args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        //System.out.println(quickSelect(nums, k));
        System.out.println(lengthOfLongestSubstring("abcacbcba"));
    }

    public static int quickSelect(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i: nums) {
            list.add(i);
        }
        return list.get(list.size() -k - 1);
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] lastIndex = new int[256]; // Для всех ASCII символов
        Arrays.fill(lastIndex, -1);     // Инициализируем -1

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // Если символ уже встречался И его позиция >= left
            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1; // Двигаем left
            }

            lastIndex[c] = right; // Обновляем последнюю позицию
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.isEmpty()) return 0;

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // ШАГ 1: Проверяем, был ли символ уже в текущем окне
            if (charIndexMap.containsKey(currentChar)) {
                // ШАГ 2: Двигаем left сразу после последнего вхождения
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // ШАГ 3: Обновляем позицию символа
            charIndexMap.put(currentChar, right);

            // ШАГ 4: Обновляем максимальную длину
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
