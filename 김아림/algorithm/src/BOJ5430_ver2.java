
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ5430_ver2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // AC
        // R 뒤집기, D버리기 -> deque 쓰기
        // 순서대로 실행하기

        // 초기값 / 수행할 함수 -> 최종 결과가 뭔지 구하기

        int T = Integer.parseInt(br.readLine());
        String answer = "";

        for (int tc = 1; tc <= T; tc++) {

            String op = br.readLine();
            int N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            sb.append(br.readLine());
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length() - 1);
            String input = sb.toString();

            String[] str = input.split(",");
            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                deque.add(Integer.parseInt(str[i]));
            }

//            System.out.println(deque + " -> " + deque.size() + " : " + deque.peek());

            // op 실행하기
            boolean isReversed = false;
            boolean flag = true;

            for (int i = 0; i < op.length(); i++) {
                char curOperation = op.charAt(i); // 현재 명령어

                if (curOperation == 'R') {
                    isReversed = !isReversed; // 돌려주기
                } else {
                    // 만약 뺄것이 없다면 ? 에러
                    if (deque.size() == 0) {
                        flag = false;
                        answer = "error";
                        break;
                    }

                    if (isReversed)
                        deque.removeLast(); // 맨뒤에거 빼기
                    else
                        deque.removeFirst(); // 맨 앞에거 빼기
                }

            }

            if (flag) {
                StringBuilder answer_sb = new StringBuilder();
                answer_sb.append("[");
                while (!deque.isEmpty()) {
                    if (isReversed) {
                        answer_sb.append(deque.removeLast());
                    } else {
                        answer_sb.append(deque.removeFirst());
                    }
                    if (!deque.isEmpty()) {
                        answer_sb.append(",");
                    }
                }
                answer_sb.append("]");
                answer = answer_sb.toString();
            }

            System.out.println(answer);
        }

    }

}