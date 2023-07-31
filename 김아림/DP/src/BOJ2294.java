import java.io.*;
import java.util.*;

public class BOJ2294 {
    static int N, K;
    static int[] coin_value;
    static int[] dp; //

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin_value = new int[N+1];
        dp = new int[K+1];

        for(int i = 1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            coin_value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= K; i++) {
            dp[i] = 100001;
        }

        dp[0] = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = coin_value[i]; j <= K; j++) {
                dp[j]= Math.min(dp[j], dp[j - coin_value[i]] +1);
            }
        }

        if(dp[K] == 100001) {
            System.out.print(-1);
        }else {
            System.out.print(dp[K]);

        }
    }

}