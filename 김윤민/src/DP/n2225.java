package DP;

import java.io.*;
import java.util.*;
public class n2225 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int num = 1000000000;
		int[][] dp = new int[k+1][n+1]; //전체 dp k값과 함께 병행.
		for(int i=0;i<=n;i++) {
			dp[1][i]=1;//1개인데 i인 경우는 자기자신. 1로 초기화
		}
		for(int i=2;i<=k;i++) {
			for(int j=0;j<=n;j++) {
				if(j==0) {
					dp[i][j]=1; //j=0인데 개수가 i인거는 0+0+...의 경우로 1개밖에 없음.
				}else {
					 //solve ( 3중 for문 안써야될거같은데 ㅜ)
					for(int z=0;z<=j;z++) {
						int tmp = dp[i-1][z]*dp[1][j-z]%num;//2개의 경우의 수로 나눠서 생각. - 나머지 개수는 무조건 1개니까 점진적으로 곱해주며 add
						dp[i][j]=(dp[i][j]+tmp)%num;
					}
				}
			}
		}
		System.out.println(dp[k][n]);
	}

}
