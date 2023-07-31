package DP;

import java.io.*;
import java.util.*;

public class n2293 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] money = new int[n];
		for(int i=0;i<n;i++) {
			money[i]=Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[m+1];
		//default
		dp[0]=1;
		
		for(int i=0;i<n;i++) {
			for(int j=money[i];j<=m;j++) {
				dp[j]+=dp[j-money[i]];
				//누적합.
				//money값 부터 경우의 수 누적.
				//1 -> 10까지 dp[1]=dp[1]+dp[1-1]; = 나 자신
				//dp[2] = dp[2]+dp[1]; -> money[i]를 기준으로 iterate함. 
				//dp[4]=dp[4]+dp[3];
			}
		}
		System.out.println(dp[m]);
		
	}

}