package DP;
import java.io.*;

public class n10844 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n =Integer.parseInt(br.readLine());
		int mod = 1000000000;
		int[][] dp = new int[n+1][10];
		dp[1][0]=0;//0으로 시작하면 계단수 X
		for(int i=1;i<10;i++) {
			dp[1][i]=1;
		}
		
		for(int i=2;i<=n;i++) {
			for(int j=0;j<10;j++) {
				if(j==0) {
					dp[i][0]=dp[i-1][1]%mod;
				}else if(j==9) {
					dp[i][9]=(dp[i-1][8])%mod;
				}else {
					dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%mod;
				}
				
			}
		}
		int sum=0;
		for(int j=0;j<10;j++) {
			sum=(sum+dp[n][j])%mod;
			//sum+=dp[n][j]%mod; -> 오류남.
			//왜???????????낼 교수님께 여쭤봐야지
		}
		System.out.println(sum%mod);

	}

}
