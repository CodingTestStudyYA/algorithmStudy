import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dp;
    static int mod = 9901;


    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        // 3가지 경우 그게 뭔데.....

        // 우리에 넣지 않는 경우
        // 왼쪽 우리에 넣는 경우
        // 오른쪽 우리에 넣는 경우
        dp = new int[N+1][3]; // N 사이즈 우리에 사자 넣기

        // 첫열에는 사자가 존재한다 함
        dp[1][0] = 1; // 첫째줄에는 사자 무조건 둬야함 -> 아님 배치하는 경우이기 때문에 첫째줄에도 사자 없을 수 있음
        dp[1][2] = dp[1][1] = 1; // 왼쪽, 오른쪽은 한 경우씩

        for(int i =2; i<=N; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] +dp[i-1][2]) % mod; // 없는 경우
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod; // 왼쪽에 뒀으면 아예 안두거나 + 오른쪽에 두는 경우
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % mod; // 오른쪽에 뒀으면 아예 안두거나 + 왼쪽에 두는 경우
        }

        int sum = 0;
        for(int i = 0; i<3; i++){
            sum+= dp[N][i]; // 최종 N 경우의 수
        }

        System.out.println(sum %mod);

    }
}
