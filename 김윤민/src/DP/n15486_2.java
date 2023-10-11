package DP;

import java.io.*;
import java.util.*;
//퇴사2(복습)
public class n15486_2 {
	/*
	 * 남은 N일 동안 최대한 많은 상담
	 * 하루에 하나씩 상담
	 * 상담을 완료하는데 걸리는 시간 T, 받는금액 P
	 * 
	 */
	static int N;
	static int[] day;
	static int[] money;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		day = new int[N+2];
		money = new int[N+2];
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day[i]= Integer.parseInt(st.nextToken());
			money[i]=Integer.parseInt(st.nextToken());
		}
		//input end
		
		int[] dp = new int[N+2];
		int max = Integer.MIN_VALUE;
		//완료된 시점은 i+1이므로 저장은 i+day[i]에 저장.
		for(int i=1;i<=N+1;i++) {
			if(max<dp[i]) {
				max=dp[i];
			}
			if(i+day[i]>N+1) continue;
			//이전에서는 그냥 max값을 저장해두면 됨.
			dp[i+day[i]]=Math.max(dp[i+day[i]], max+money[i]);
		}
//		for (int i : dp) {
//			System.out.println(i);
//		}
		System.out.println(dp[N+1]);
	}
}