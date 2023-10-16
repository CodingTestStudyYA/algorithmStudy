import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2631 {
    public static void main(String[] args) throws IOException {
        // 이분탐색 아니면 LIS로...
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] dp = new int[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N - ans);
    }
}
