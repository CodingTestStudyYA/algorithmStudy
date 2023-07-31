package DP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class n2294 {

	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] money = new int[n];
		int[] dp = new int[10001];
		for(int i=0;i<n;i++) {
			money[i]= Integer.parseInt(br.readLine());
		}
		//solve
		Arrays.fill(dp, 10001);
		for(int i=0;i<n;i++) {
			if(money[i]<=m) {
				dp[money[i]]=1;//money들을 경우의 수 1로 함.
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=money[i];j<=m;j++) {
				//기존에 나에게 저장되어 있는 값은 이미 최소값.
				//money기준으로 비교 이전값이 +1 했을때 더 작은 값을 저장함. 
				dp[j]=Math.min(dp[j], dp[j-money[i]]+1);
				
			}
			
		}
		if(dp[m]!=10001) {
			System.out.println(dp[m]);
		}else {
			System.out.println(-1);
		}
		
	}

}
