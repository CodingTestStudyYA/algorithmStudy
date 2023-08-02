package DP;

import java.io.*;
import java.util.*;
public class n1149 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] rgb = new int[n][3];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				rgb[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//시작하는 경우가 3가지 R/G/B 각각 진행
		int[][] dp = new int[n][3];
		//default
		for(int i=0;i<3;i++) {
			dp[0][i]=rgb[0][i];
		}
		for(int i=1;i<n;i++) {
			//이전 값들 중에서 내 위치를 제외하고 
			//더 작은것을 나에게 더한다.
			dp[i][0]=Math.min(dp[i-1][1], dp[i-1][2])+rgb[i][0];
			dp[i][1]=Math.min(dp[i-1][0], dp[i-1][2])+rgb[i][1];
			dp[i][2]=Math.min(dp[i-1][1], dp[i-1][0])+rgb[i][2];
		}
		int min = Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			min = Math.min(min, dp[n-1][i]);
		}
		System.out.println(min);
	}

}
