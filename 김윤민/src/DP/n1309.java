package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n1309 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//dp[n][3]
		int[][] dp = new int[n+1][3];
		// n=1 일때 3의 경우의 수가 존재
		// 첫 칸에 사자가 있는 경우, o x 
		// 둘째 칸에만 사자가 있는 경우, x o
		// 사자가 그냥 없는 경우, x x
		
		//default
		//n=1인 경우, 세 경우 모두 다 경우의 수가 1이므로 초기화 진행.
		for(int j=0;j<3;j++) {
			dp[1][j]=1;
		}
		int num=9901;
		int result=0;
		for(int i=2;i<=n;i++) {
			//앞칸 기준으로 dp진행
			dp[i][0]=(dp[i-1][1]+dp[i-1][2])%num;  
			//첫 칸에 사자 : 이전이 둘째칸 사자 경우 + 아예 없는 경우
			dp[i][1]=(dp[i-1][0]+dp[i-1][2])%num;
			//둘째 칸에 사자 : 이전이 첫째칸 사자인 경우 + 아예 없는 경우
			dp[i][2]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%num;
			//없는 경우 : 이전의 모든 경우가 다 올 수 있다.
		}
		for(int j=0;j<3;j++) {
			result=(result+dp[n][j])%num;
		}
		System.out.println(result);
		
	}
}
