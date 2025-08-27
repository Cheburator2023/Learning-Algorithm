import java.util.*;
import java.util.stream.Collectors;

public class BaseJava1 {
    public static void main(String[] args) {
        int[] nums = {-232,-876,-123,-456,-789,-234,-345,-567, -233};
        int[] nums2 = {123,234,-823,456,-875,789,-327,234,123,345,-0,567,823};
        int[] nums3 = new int[0];
        int[] nums4 = {2};
        int[] nums5 = null;
        int[] nums6 = {3,3,3,3};
        int[][] grid = {{1, 2}, {3, 4}};
        String s = "Мама купила Карлу кораллы";
        System.out.println(secondMaxItemInArray(nums4));
        System.out.println(sumItemsOfNumber(-12345));
        System.out.println(isHappyNumber(223322));
        System.out.println(isHappyNumber(12363821));
        System.out.println(Arrays.toString(deleteDuplicateItems(nums2)));
        System.out.println(Arrays.toString(primeNumberArray(30)));
        System.out.println(reverseWordsInSentence("Java is awesome"));
        System.out.println(maximumDelimiter(-48,88));
        System.out.println(countMaxEnteringCharInString("A1B2c3", '1'));
        System.out.println(countMaxEnteringCharInString2("A1B2c3", '1'));
        System.out.println(isAnagram("abcba","badab"));
        System.out.println(isAnagram1("dormitory","dirty room" ));
        System.out.println(isAnagram2("dormitory","dirty room" ));
        System.out.println(isAnagramOptimized("dormitory","dirty room"));
        printTableOfMultiply(20);
    }

    public static int secondMaxItemInArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            }
            if (num > max2 && num < max1) {
                max2 = num;
            }
        }
        return max2;
    }

    public static int secondMaxItemInArray2(int[] nums){
        if (nums == null || nums.length < 2) {
            return Integer.MIN_VALUE;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max2 = max1; // Старый max1 становится max2
                max1 = num;
            } else if (num > max2 && num != max1) {
                max2 = num;
            }
        }
        return (max2 == Integer.MIN_VALUE && !allElementsEqual(nums)) ?
                Integer.MIN_VALUE : max2;
    }

    private static boolean allElementsEqual(int[] nums) {
        int first = nums[0];
        for (int num : nums) {
            if (num != first) return false;
        }
        return true;
    }

    public static int sumItemsOfNumber (int num) {
        if (num / 10 == 0) {
            return num;
        }
        int sum = 0;
        while (Math.abs(num) > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static boolean isHappyNumber(int num) {
        int originalNum = num;
        int length = (int) Math.log10(originalNum) + 1;
        if (length % 2 != 0) return false;

        // Сумма последней половины цифр
        int sumLastHalf = 0;
        for (int i = 0; i < length / 2; i++) {
            sumLastHalf += originalNum % 10;
            originalNum /= 10;
        }

        // Сумма первой половины цифр (оставшиеся)
        int sumFirstHalf = 0;
        for (int i = 0; i < length / 2; i++) {
            sumFirstHalf += originalNum % 10;
            originalNum /= 10;
        }

        return sumFirstHalf == sumLastHalf;
    }

    public static int[] deleteDuplicateItems(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        HashSet<Integer> set = new LinkedHashSet<>();
        Arrays.stream(nums).forEach(num -> set.add(num));

        return set.stream().mapToInt(num -> num).toArray();
    }

    public static int[] primeNumberArray(int num) {
        List<Integer> result = new ArrayList<>();
        if (num >= 2) result.add(2);
        for (int i = 3; i <= num; i += 2) {
            if (isPrimeNumber(i)) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static boolean isPrimeNumber(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;

    }

    public static String reverseWordsInSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }
        String[] strings= sentence.split("\\s+");
        Collections.reverse(Arrays.asList(strings));

        return String.join(" ", strings);
    }

    public static int maximumDelimiter(int num1, int num2) {
        int a = Math.abs(num1);
        int b = Math.abs(num2);
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int countMaxEnteringCharInString(String string, char target) {
        if (string == null || string.isEmpty()) return 0;
        String[] strings = string.toLowerCase().split("");
        String targets = String.valueOf(target).toLowerCase();
        return (int) Arrays.stream(strings).filter(n -> n.equals(targets)).count();
    }

    public static int countMaxEnteringCharInString2(String string, char target) {
        if (string == null || string.isEmpty()) return 0;
        String stringToLowerCase = string.toLowerCase();
        char targets = Character.toLowerCase(target);
        int count = 0;
        for (char c : stringToLowerCase.toCharArray()) {
            if (c == targets) count++;
        }
        return count;
    }

    public static boolean isAnagram(String s1, String s2) {
        isEmpty(s1,s2);
        String s1LowerCase = getLowerCase(s1);
        String s2LowerCase = getLowerCase(s2);
        if (s1LowerCase.length() != s2LowerCase.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for ( char c : s1LowerCase.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for ( char c : s2LowerCase.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) < 0) return false;

        }
        return true;
    }

    public static boolean isAnagram1(String s1, String s2) {
        isEmpty(s1,s2);
        String s1LowerCase = getLowerCase(s1);
        String s2LowerCase = getLowerCase(s2);
        if (s1LowerCase.length() != s2LowerCase.length()) return false;
        int[] sortedStr1 = s1LowerCase.chars().sorted().toArray();
        int[] sortedStr2 = s2LowerCase.chars().sorted().toArray();
        return Arrays.equals(sortedStr1, sortedStr2);

    }

    public static boolean isAnagram2(String s1, String s2) {
        isEmpty(s1,s2);
        String s1LowerCase = getLowerCase(s1);
        String s2LowerCase = getLowerCase(s2);
        if (s1LowerCase.length() != s2LowerCase.length()) return false;
        char[] chars1 = s1LowerCase.toCharArray();
        char[] chars2 = s2LowerCase.toCharArray();

        bubbleSort(chars1);
        bubbleSort(chars2);

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }

    private static void bubbleSort(char[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // Флаг для оптимизации - проверка на уже отсортированный массив
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap adjacent elements
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Если на этой итерации не было перестановок - массив отсортирован
            if (!swapped) break;
        }
    }

    private static boolean isEmpty(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.isEmpty() || s2.isEmpty()) return false;
        return s1.equals(s2);
    }

    private static String getLowerCase(String str) {
        return str.toLowerCase().replaceAll("[^a-zа-яё]", "");
    }

    public static boolean isAnagramOptimized(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        String clean1 = s1.replaceAll("\\P{L}", "").toLowerCase();
        String clean2 = s2.replaceAll("\\P{L}", "").toLowerCase();

        if (clean1.length() != clean2.length()) return false;

        int[] counts = new int[256]; // Для ASCII
        for (char c : clean1.toCharArray()) counts[c]++;
        for (char c : clean2.toCharArray()) {
            if (--counts[c] < 0) return false;
        }
        return true;
    }

    public static void printTableOfMultiply(int num) {
        if (num <= 0) {
            System.out.println("Число должно быть положительным");
            return;
        }
        if (num == 1) {
            System.out.println("1");
            return;
        }
        int maxWidth = String.valueOf(num * num).length() + 1;
        for (int i = 0; i < num; i++) {
            if (i == 0) {
                StringBuilder str = new StringBuilder("     ");
                for (int j = 0; j < num; j++) {
                    str.append(String.format("%" + maxWidth + "d", j+1));
                }
                System.out.println(str);
                System.out.println("   +" + "-".repeat(Math.max(0, num * maxWidth)));
            }
            StringBuilder row = new StringBuilder();
            row.append(String.format("%2d | ", i+1));
            for (int j = 1; j <= num; j++) {
                row.append(String.format("%" + maxWidth + "d", (i+1) * j));
            }
            System.out.println(row);
        }
    }
}
