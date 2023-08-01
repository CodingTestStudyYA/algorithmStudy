package DP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class n1912 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st =new StringTokenizer(br.readLine());
		int[] list = new int[n+1];
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++) {
			list[i]=Integer.parseInt(st.nextToken());
			dp[i]=list[i];
		}
		
		int max =list[1];
		for(int i=1;i<=n;i++) {
			dp[i]=Math.max(dp[i-1]+list[i], dp[i]);
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}

}
