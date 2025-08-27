import java.util.*;

public class BaseJava3 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        Character[]nums2 = {'a','c','b','v','a'};
        String[]nums3 = {"ABC","abc"};
        int[]nums4 = {1, 2, 3, 4, 5, 6, 7};
        int[]nums7 = {};
        int[] nums5 = {123,234,-823,456,-875,789,-327,234,123,345,-0,567,823};
        int[] nums6 = {123,234,-823,456,-875,789,-327,234,123,345,-0,567,823};
        String s = "A man, a plan, a canal: Panama";
        String s1 = "race a car";
        String s2 = "";
        String s3 = null;
//        System.out.println(findDuplicateInArray(nums1));
//        System.out.println(findDuplicateInArray(nums2));
//        System.out.println(findDuplicateInArray(nums3));
//        System.out.println(findDuplicateInArray(nums4));
//        System.out.println(isPalendrome2(s));
//        System.out.println(isPalendrome2(s1));
//        System.out.println(isPalendrome2(s2));
//        System.out.println(isPalendrome2(s3));
//        System.out.println(Arrays.toString(mergeTwoArrays(nums5, nums6)));
//        System.out.println(findUniqueChar("aaAddDggGA"));
//        System.out.println(findUniqueCharMap("abcdefgabcdeg"));
//        System.out.println(isValidBracketSequence("([{(||)}])"));
//        System.out.println(isValidBracketSequence("([)}"));
//        System.out.println(countCharInString("AAAAAAABBBBBHHHYYYYUU"));
//        System.out.println(Arrays.toString(crossTwoArrays(nums1,nums4)));
//        System.out.println(Arrays.toString(crossTwoArrays(nums5,nums6)));
//        System.out.println(Arrays.toString(arrayCiclicShift(nums1, 2)));
//        System.out.println(Arrays.toString(arrayCiclicShift(nums1, 5)));
//        System.out.println(Arrays.toString(arrayCiclicShift(nums4, 1)));
//        System.out.println(Arrays.toString(arrayCiclicShift(nums4, -7)));
//        System.out.println(Arrays.toString(arrayCiclicShift(nums7, 0)));
        System.out.println(findPeakInArray(nums5));
    }

    public static <T>T findDuplicateInArray(T[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        HashSet<T> set = new LinkedHashSet<>();
        T duplicate = null;
        for (T num : nums) {
            if (set.contains(num)) {
                duplicate = num;
                break;
            }
            set.add(num);
        }
        return duplicate;
    }

    public static boolean isPalendrome(String s) {
        if (s == null) {
            return false;
        }
        String toLowerCaseAndReplace = s.toLowerCase().replaceAll("[^a-zа-яё]", "");
        for (int i = 0; i < toLowerCaseAndReplace.length(); i++) {
            if (toLowerCaseAndReplace.charAt(i) != toLowerCaseAndReplace.charAt(toLowerCaseAndReplace.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalendrome2(String s) {
        if (s == null) {
            return false;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // Пропуск не-букв
            if (!Character.isLetter(s.charAt(left))) left++;
            else if (!Character.isLetter(s.charAt(right))) right--;
                // Сравнение
            else if (Character.toLowerCase(s.charAt(left))
                    != Character.toLowerCase(s.charAt(right))) return false;
            else { left++; right--; }
        }
        return true;
    }

    public static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
        if (arr1 == null) return arr2;
        if (arr2 == null) return arr1;
        int[] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        Arrays.sort(result);
        return result;
    }

    public static int[] mergeTwoArrays1(int[] arr1, int[] arr2) {
        if (arr1 == null) return arr2;
        if (arr2 == null) return arr1;
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        // Сливаем массивы, пока не достигнем конца одного из них
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] >= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        // Добавляем оставшиеся элементы из arr1 (если есть)
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        // Добавляем оставшиеся элементы из arr2 (если есть)
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static char findUniqueChar(String s) {
        if (s == null || s.length() == 0) {
            return '\0';
        }
        for (int i = 0; i < s.length(); i++) {
            char result = s.charAt(i);
            if (s.indexOf(result) == s.lastIndexOf(result)) {
                return result;
            }
        }
        return '\0';
    }

    public static char findUniqueCharMap(String s) {
        if (s == null || s.length() == 0) {
            return '\0';
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) == 1) {
                return c;
            }
        }

        return '\0';
    }

    // Валидация скобочной последовательности
    public static boolean isValidBracketSequence(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                   (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Подсчет символов в строке
    public static String countCharInString(String s) {
        StringBuilder sb = new StringBuilder();
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    // Поиск пересечения двух массивов
    public static int[] crossTwoArrays(int[] arr1, int[] arr2) {
        if (arr1 == null) return arr2;
        if (arr2 == null) return arr1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : arr2) {
            if (map.containsKey(i)) {
                list.add(i);
                map.put(i, map.getOrDefault(i, 0) - 1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    // Циклический сдвиг массива
    public static int[] arrayCiclicShift(int[] arr, int k) {
        if (arr == null) return null;
        if (arr.length == 0) return new int[0];

        k = k % arr.length;
        if (k < 0) k += arr.length;
        if (k == 0) return arr;

        int[] result = new int[arr.length];
        System.arraycopy(arr, arr.length - k, result, 0, k);
        System.arraycopy(arr, 0, result, k, arr.length - k);
        return result;
    }

    // Поиск "пика" в массиве. Возвращает индекс элемента в массиве
    public static int findPeakInArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
