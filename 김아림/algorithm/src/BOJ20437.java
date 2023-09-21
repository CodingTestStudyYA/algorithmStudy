
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ20437 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            // 입력받은 문자열
            char[] arr = br.readLine().toCharArray();
            int[] charArr = new int[26];
            int K = Integer.parseInt(br.readLine());

            // 한개면 하나만 찾기
            if (K == 1) {
                sb.append("1 1");
                if (test_case != T) {
                    sb.append("\n");
                }
                continue;
            }

            // 한개가 아니면
            for (int i = 0; i < arr.length; i++) {
                // 알파벳 a -> 0으로 해서 값 ++ 하기
                charArr[arr[i] - 'a']++;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length - K + 1; i++) {
                char word = arr[i];
                if (charArr[word - 'a'] < K) continue;
                int cnt = 1;
                for (int j = i + 1; j < arr.length; j++) {
                    if (word == arr[j]) {
                        cnt++;
                    }
                    if (cnt == K) {
                        if (min > j - i + 1) {
                            min = j - i + 1;
                        }
                        if (max < j - i + 1) {
                            max = j - i + 1;
                        }
                        charArr[word - 'a']--;
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE) {
                sb.append("-1");
            } else {
                sb.append(min + " " + max);
            }
            if (test_case != T) {
                sb.append("\n");
            }
        }
        System.out.printf("%s", sb.toString());
    }
}
