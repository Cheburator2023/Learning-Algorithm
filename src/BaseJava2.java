import java.math.BigInteger;
import java.util.*;

public class BaseJava2 {
    public static void main(String[] args) {
        int[] nums2 = {123,234,-823,456,-875,789,-327,234,123,345,-0,567,823};
        int[] nums = {1,1,2};
        int[] nums3 = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        countEvenOddNumber(-123L);
        System.out.println(isSymmetricNumber(  1));
        System.out.println(secondMinItemInArray(nums3));
        System.out.println(factorial(10));
        System.out.println(Arrays.toString(generatePrimeNumber(1000)));
        System.out.println(findPrimes(100));
        System.out.println(isNumberOfArmstrong(153));
        System.out.println(Arrays.toString(findArmstrongNumber(10000)));
    }

    // Подсчёт чётных и нечётных цифр в числе
    public static void countEvenOddNumber(long n) {
        if (n == 0) {
            System.out.println("Чётные: 1");
            System.out.println("Нечётные: 0");
            return;
        };
        int countEven = 0;
        int countOdd = 0;
        while (n != 0){
            if (n % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }
            n /= 10;
        }
        System.out.println("Чётные: " + countEven);
        System.out.println("Нечетные: " + countOdd);
    }

    // Проверка на "симметричное число"(палиндром)
    public static boolean isSymmetricNumber(int n) {
        if (n < 0) {
            return false;
        }
        if (n < 10 ) {
            return true;
        }
        int savedNumber = n;
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + n % 10;
            n /= 10;
        }
        return savedNumber == reverse;
    }

    // Поиск второго минимального элемента в массиве
    public static int secondMinItemInArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            }
            if (num < min2 && num > min1) {
                min2 = num;
            }
        }
        return min2;
    }

    // Подсчёт факториала
    public static BigInteger factorial(int n) {
        if (n < 0) {
            System.out.println("Факториал отрицательного числа не определён");
            return BigInteger.ZERO;
        }
        if (n == 0) {
            return BigInteger.ONE;
        }
        if (n > 1000) {
            System.out.println("Слишком большое число");
            return BigInteger.ZERO;
        }
        return BigInteger.valueOf(n).multiply(factorial(n-1));
    }

    // Генерация всех простых чисел до N (решето Эратосфена)
    public static int[] generatePrimeNumber(int n) {
        if (n <= 0) { return new int[0]; }
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1]= false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        int a = 0;
        for (boolean b : isPrime) {
            if (b) {
                primes.add(a);
            }
            a++;
        }

        return primes.stream().mapToInt(i -> i).toArray();
    }

    public static List<Integer> findPrimes(int N) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    // Поиск всех уникальных элементов в массиве
    public static int[] uniqueItemsInArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        HashSet<Integer> map = new LinkedHashSet<>(nums.length);
        for (int num : nums) {
            map.add(num);
        }
        return map.stream().mapToInt(i -> i).toArray();
    }

    public static <T>T[] uniqueItemsInArray2(T[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        HashSet<T> map = new LinkedHashSet<>(nums.length);
        Collections.addAll(map, nums);
        return map.toArray(Arrays.copyOf(nums, nums.length));
    }

    // Проверка на "число Армстронга"
    public static boolean isNumberOfArmstrong(int n) {
        if (n < 0) {
            return false;
        }
        int origin = n;
        int count = String.valueOf(n).length();
        long sum = 0L;
        while (n != 0) {
            int digit = n % 10;
            sum += (long) Math.pow(digit, count);
            n /= 10;
        }
        return sum == origin;
    }

    public static int[] findArmstrongNumber(int n) {
        if (n < 0) {
            return new int[0];
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isNumberOfArmstrong(i)) {
                nums.add(i);
            }
        }
        return nums.stream().mapToInt(i -> i).toArray();
    }
    // Подсчёт количества битов в двоичном представлении числа

    // Поиск самой длинной последовательности единиц в двоичном числе

    // Валидация строки с скобками
}
