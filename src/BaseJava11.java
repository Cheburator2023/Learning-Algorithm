import java.util.ArrayDeque;
import java.util.Deque;

public class BaseJava11 {
    public static void main(String[] args) {
        String s1 = "(*))";
        String s2 = "((*)*)";
        String s3 = "(**)*)";
        String s4 = "(****)";
        System.out.println(checkValidString(s1));
    }
    public static boolean checkValidString(String s) {
        if (s == null || s.isEmpty()) return true;

        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen = Math.max(minOpen - 1, 0);
                maxOpen--;
                if (maxOpen < 0) {
                    return false; // Слишком много закрывающих скобок
                }
            } else if (c == '*') {
                minOpen = Math.max(minOpen - 1, 0);
                maxOpen++;
            }
        }
        return minOpen == 0;
    }
}
