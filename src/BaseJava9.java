import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseJava9 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s,p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length()){
            return result;
        }

        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        for(char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int n = s.length();
        int k = p.length();

        while (right < k) {
            windowCount[s.charAt(right) - 'a']++;
            right++;
        }

        if(Arrays.equals(pCount, windowCount)) {
            result.add(left);
        }

        while(right < n) {
            windowCount[s.charAt(left) - 'a']--;
            windowCount[s.charAt(right) - 'a']++;
            right++;
            left++;
            if(Arrays.equals(pCount, windowCount)) {
                result.add(left);
            }
        }
        return result;
    }
}
