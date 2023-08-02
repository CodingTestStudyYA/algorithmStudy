package boj;

import java.util.*; 
import java.io.*;

public class BOJ1149 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N; 
	static int[][] map;  
	static int[][] dp; 
	static int MIN_VALUE = Integer.MAX_VALUE ;
	public static void main(String[] args) throws IOException {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dp = new int[N][3];
		map = new int[N][3];
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// i번째집을 j색으로 칠하는데 드는 비용 
				if(i == 0) {
					dp[0][j] = map[0][j]; // 첫번째 집을 칠하는 경우
				}
			}
		}
		
		
		int sum = 0; 
		for(int i = 1; i<N; i++) {
			for(int j = 0; j<3; j++) {
				dp[i][j] =  Math.min(dp[i-1][(j+1)%3]+ map[i][j] ,dp[i-1][(j+2)%3] +map[i][j]);
			}
		}
		
		for(int i = 0; i<3; i++) {
			if(MIN_VALUE > dp[N-1][i]) {
				MIN_VALUE = dp[N-1][i];
			}
		}
		
		System.out.println(MIN_VALUE);
	}
}
