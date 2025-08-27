import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] nums = {-876,-123,-456,-789,-234,-345,-567};
        int[] nums2 = {123,-823,456,-875,789,-327,234,345,-0,567};
        int[] nums3 = new int[0];
        int[][] grid = {{1, 2}, {3, 4}};
        String s = "Мама купила Карлу кораллы";
        System.out.println(findMaxItem(nums));
        System.out.println(findAvgItem(nums));
        System.out.println(findUnderZeroItem(nums2));
        System.out.println(isPalendrome("madam"));
        System.out.println(findItemInArray(nums3, 0));
//        translateCtoF();
        System.out.println(multiplyItemInArray(grid, 1));
        System.out.println(countWordsInSentence(s));
        System.out.println(generateRandomPassword(9));
        System.out.println(isPerfectNumber(6));
        System.out.println(Arrays.toString(perfectNumberArray(10000)));
        System.out.println(Arrays.toString(primeNumberArray(10000)));
    }

    public static int findMaxItem(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static double findAvgItem(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (double) sum / nums.length;
    }

    public static int findUnderZeroItem(int[] nums) {
        return (int) Arrays.stream(nums).filter(n -> n < 0).count();
    }

    public static boolean isPalendrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-zа-яё]", "");
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static int findItemInArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void translateCtoF() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите температуру в Цельсиях: ");
        if (sc.hasNextInt()) {
            int num = sc.nextInt();
            System.out.println("Температура в Фаренгейтах: " + (num * 9/5 + 32));
        } else {
            System.out.println("Вы ввели не целое число");
            sc.next();
        }
        sc.close();
    }

    public static int multiplyItemInArray(int[][] nums, int target) {
        int product = 1;
        for (int[] num : nums) {
            for (int i : num) {
                product *= i * target;
            }
        }
        return product;
    }

    public static int countWordsInSentence(String sentence) {
        return sentence.trim().split("\\s+").length;
    }

    public static String generateRandomPassword(int length) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(s.charAt(random.nextInt(s.length())));
        }
        return password.toString();
    }

    public static boolean isPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i <= num/2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    public static boolean isPrimeNumber(int num) {
        int sum = 0;
        for (int i = 1; i <= num/2; i++) {
            if (num % i == 0) {
                sum += num / i;
            }
        }
        return sum == num;
    }

    public static int[] perfectNumberArray(int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 6; i < num; i += 2) {
            if (isPerfectNumber(i)) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int[] primeNumberArray(int num) {
        List<Integer> result= new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (i % 2 != 0) {
                if (isPrimeNumber(i)) {
                    result.add(i);
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}