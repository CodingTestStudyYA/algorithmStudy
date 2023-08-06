package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n12865 {
	static int n,m,max;
	static int[][] dp;
	static int[] w;//무게
	static int[] v;//가치
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게
		w =new int[n+1];
		v = new int[n+1];
		dp = new int[n+1][m+1];//i번 인덱스 요소가 선택되었는지 안되었는지에 따른 무게
		for(int i=1;i<=n;i++) {
			st= new StringTokenizer(br.readLine());
			w[i]=Integer.parseInt(st.nextToken());
			v[i]=Integer.parseInt(st.nextToken());
		}
		
		
		//무게와 가치.
		for(int i=1;i<=n;i++) {//i는 인덱스. 요소위치
			for(int j=1;j<=m;j++) {//j는 누적 계산되는 무게를 나타냄.
				if(j<w[i]) {//현재 무게 값보다 내 가방의 무게가 더 크면 
					//로직을 실행할 수 없으니 이전 최적화의 값을 가져온다.
					dp[i][j]=dp[i-1][j];
				}else { //담을 수 있으면, 내무게를 뺀 이전 가치 최댓값에 내 가치를 더하는 것과 이전값과 비교.
					//이전의 값은 이미 최적해를 가지고 있다.
					dp[i][j]=Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
				}
			}
		}
		System.out.println(dp[n][m]);
		
	}
}
