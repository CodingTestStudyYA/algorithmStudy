import java.util.*;
import java.io.*;

public class BOJ1912 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int[] dp; // dp[0] -> arr[0]으로 시작하는 숫자들 중 가장 큰 숫자
    static int MAX;
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        MAX = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            if(MAX < dp[i]) {
                MAX = dp[i];
            }
        }

        System.out.println(MAX);

    }
}