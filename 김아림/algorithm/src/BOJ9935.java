import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BOJ9935 {

    static String str;
    static String bomb_string;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        bomb_string = br.readLine();
        String answer = solution(str, bomb_string);
        System.out.println((answer.length() == 0) ? "FRULA" : answer);
    }

    private static String solution(String str, String bomb_string) {

        Stack<Character> stack = new Stack<>();
        int blen = bomb_string.length();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= blen) {
                boolean flag = true;
                for (int j = 0; j < blen; j++) {
                    if (stack.get(stack.size() - blen + j) != bomb_string.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    // flag가 true이면 돌면서 제거
                    for (int j = 0; j < blen; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }
}