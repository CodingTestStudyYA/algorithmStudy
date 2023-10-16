package DP;
//줄세우기
import java.io.*;
import java.util.*;
public class n2631 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		//LIS구한뒤 N-LIS
		int ans =0;
		int[] dp = new int[N];
		//초기화
		for(int i=0;i<N;i++) {
			dp[i]=1;
		}
		//LIS 구하기 
		for(int i=0;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(arr[j]<arr[i]) {
					dp[i]=Math.max(dp[i],dp[j]+1);
				}
			}
		}
		for (int i : dp) {
			ans = Math.max(i, ans);
		}
		System.out.println(N-ans);
	}
}
