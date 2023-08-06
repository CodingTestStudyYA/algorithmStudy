import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] value;
    static int[] weight;
    static int[][] dp; // i는 지금 까지 번호, j는 배낭 무게 => dp[i][j]는 지금까지의 가치
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        value = new int[N+1];
        weight = new int[N+1];
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<=N; i++){
            int w = weight[i];
            int v = value[i];
            for(int j = 1; j<=K; j++){ // 배낭에 넣기 가능한 무게 점점 올리면서 K까지
                if(j - weight[i] >= 0) { // 채우기 가능
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w]+ v); // 가치 높은거 구하기.
                }
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);

    }
}
