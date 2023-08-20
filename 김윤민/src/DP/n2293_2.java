package DP;

import java.io.*;
import java.util.*;
public class n2293_2 {

	public static void main(String[] args) throws Exception{
		// input
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] list = new int[n];
		int[] dp = new int[100001];
		for(int i=0;i<n;i++) {
			list[i]=Integer.parseInt(br.readLine());
		}
		//default
		dp[0]=1; // 자기자신의 값을 가지는 경우. = 1
		
		//각 money기준마다 '각각' dp를 진행.
		for(int i=0;i<n;i++) {
			for(int j=list[i];j<=k;j++) {
				dp[j]=dp[j]+dp[j-list[i]];//기존 경우의 수와 lit[i]를 더할때의 새로운 경우의 수를 합함.
			}
		}
		System.out.println(dp[k]);

	}

}
