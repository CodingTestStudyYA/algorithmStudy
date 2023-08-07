import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15486 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] T, P, dp; // i번째 날 까지의 최대 가치
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        T = new int[N + 1];
        P = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // i는 날짜가 지남을 의미함!
        // d[i]는 i날짜 까지 지났을때 낼 수 있는 최대의 이익을 의미함
        for (int i = 0; i <= N; i++) {
            MAX = Math.max(MAX, dp[i]);

            int take_days = i + T[i]; // 걸리는 날짜

            if (take_days <= N) { // 해당 면담이 끝나는 시간이 퇴직 전이면 -> 퇴직 당일도 가능함 *
                dp[take_days] = Math.max(dp[take_days], MAX + P[i]); // 해당 면접의 돈이랑 해당 값이랑 비교해줌요
            }

        }

        System.out.println(MAX);

    }
}
