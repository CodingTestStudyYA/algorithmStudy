package DP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class n1912 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st =new StringTokenizer(br.readLine());
		int[] list = new int[n+1];
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++) {
			list[i]=Integer.parseInt(st.nextToken());
			dp[i]=list[i];//본인 값으로 초기화
		}
		
		int max =list[1];
		for(int i=1;i<=n;i++) {
			//연속 합이기 때문에, 누적 max값을 저장해서 비교하면 됨.
			dp[i]=Math.max(dp[i-1]+list[i], dp[i]);//나 자신값과 앞 누적+나의 값 비교시 큰 값이 저장됨.
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}

}
