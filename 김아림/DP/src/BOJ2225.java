import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ2225 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[][] dp; // i개의 정수로 j표현하기
    static int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[201][201]; // dp풀땐 걍 무조건 정해서 넣어주기

        for(int i = 1; i<= K; i++){
            dp[i][0] = 1 ;
        }

        for(int i =1; i<=K; i++){
            for(int j =1; j<=N; j++){
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % mod;
            }
        }

        System.out.println(dp[K][N]);
    }
}
