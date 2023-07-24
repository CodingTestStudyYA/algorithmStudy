package DP;
import java.io.*;
import java.util.*;
public class n11057 {
	public static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//0 ~ 9 : 10 / j
		//n : i
		
		//n의 길이마다 끝나는 수를 기준으로 한다.
		//n=1 ; 길이가 1일때 0으로 끝나는 경우의 수는 1. --> n=1; 0~9 : 1
		//n=2 ; 오르막수는 0 : 1 / 1 : 2 / 2 : 3 / 3 : 4 
		// 00 : 1 -> 인접한 수가 같아도 오르막수
		
		// dp[i][j]=dp[i][j-1]+dp[i-1][j];
		
		//왜냐면 dp[i-1][j]는 이미 오르막임이 인증된수
		// dp[i][j-1]은 앞이 가진 오르막수는 나도 오르막이 될수 있음
		// 예시 : 00의 오르막수 -> 01 의 오르막수가 가능 !
		dp = new int[n+1][10];
		
		for(int j=0;j<10;j++) {
			dp[1][j]=1;
		}
		for(int i=2;i<=n;i++){
			for(int j=0;j<10;j++) {
				if(j==0) {
					dp[i][j]=1;
				}else {
					dp[i][j]=dp[i-1][j]+dp[i][j-1];
					dp[i][j]=dp[i][j]%10007;
				}
			}
		}
		int result=0;
		for(int j=0;j<10;j++) {
			result+=dp[n][j];
		}
		System.out.println(result%10007);
		
	}

}
