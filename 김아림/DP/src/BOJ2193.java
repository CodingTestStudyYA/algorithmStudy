import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2193 {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[91]; // dp에서는 범위 나오면 무조건 잡고 시작하자... 짜증 ...

        // 무조건 10은 고정이다 그 뒤에 숫자를 채우면 됨
        // 3일 경우 101 100 -> 2가지
        // 4일 경우 1010 1001 1000 -> 3가지
        // 5일 경우 10000 10100 10101 10010 10001 -> 5가지
        // 6일 경우 100000 101000 101010 101001 100101 100010 100001 100100

        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i<= N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);

    }
}
