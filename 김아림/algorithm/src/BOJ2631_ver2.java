import org.omg.CORBA.WCharSeqHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2631_ver2 {

    static int[] children;
    static int[] dp;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        children = new int[N];
        dp = new int[N];

        for(int i = 0; i < N; i++){
            children[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, 1); // 자기자신

        for(int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){
                if(children[j] < children[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i] );
                    ans = Math.max(dp[i], ans);
                }
            }
        }

        System.out.println(N - ans);
    }
}
