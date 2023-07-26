package DP;
import java.io.*;
import java.util.*;
public class n1890 {
	public static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		long[][] dp = new long[n][n];
		StringTokenizer st;
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//default값 설정
		int now = map[0][0];
		if(can(now,0)) {
			dp[now][0]=1;
			dp[0][now]=1;
		}
		//현재 dp값이 0이 아니면 경우의 수 추가 ( 0인 경우 : 이자리로 올 수 없다. )
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(dp[i][j]==0) continue;
				int tmp=map[i][j];
				if(tmp==0) break; //0이 나온다면 종점. break해서 빠져나와준다.
				if(can(tmp,i)) {
					dp[i+tmp][j]=dp[i+tmp][j]+dp[i][j];//현재위치의 경우의 수를 더해준다.
				}
				if(can(tmp,j)) {
					dp[i][j+tmp]=dp[i][j+tmp]+dp[i][j];
				}
			}
		}
		System.out.println(dp[n-1][n-1]);
	}
	public static boolean can(int now,int iorj) {
		int total = now+iorj;
		if(total>=0&&total<n) {
			return true;
		}
		return false;
	}

}
