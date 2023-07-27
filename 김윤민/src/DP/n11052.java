package DP;
import java.io.*;
import java.util.*;
public class n11052 {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] map = new int[n+1];
		StringTokenizer st =new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		int[][] dp =new int[n+1][n+1];
		//default값.
		dp[1][1]=map[1];
		//max를 누적시켜가며 dp 적용 해야 함. 
		for(int i=1;i<=n;i++) {
			int max=0;
			for(int j=1;j<=i;j++) {
				if(j==i) { max=Math.max(max, map[i]);}
				else if(j==1) max=Math.max(max, map[1]*i);
				else {
					max=Math.max(dp[i-j][i-j]+dp[j][j], max);
				}
				//항상 max를 저장하기 때문에, dp[i][i]의 위치에는 i의 최댓값이 담겨져 있음.
				dp[i][j]=max;
			}
		}
		
		System.out.println(dp[n][n]);
	}

}
