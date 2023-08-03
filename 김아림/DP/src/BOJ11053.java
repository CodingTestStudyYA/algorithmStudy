import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class BOJ11053 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] map;
    static int[] dp; // i번째까지 왔을때 순열의 최대 길이
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        map = new int[N+1];
        dp = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());


        for(int i = 1; i<=N; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        for(int i =1; i<=N; i++){
            if(max < map[i]) { // 포함되는 경우
                dp[i] = dp[i-1] + 1 ; // 그전까지 가장 긴 수열의 길이 + 1
                max = map[i]; // 수열이려면 그전 값 보다 커야하므로 
            }else dp[i] = dp[i-1]; // 그전까지 가장 긴 수열의 길이(나는 포함 안함)
        }

        System.out.println(dp[N]);
    }
}

