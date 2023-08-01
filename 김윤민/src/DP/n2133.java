package DP;

import java.io.*;

public class n2133 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[31];
		// 3 x n
		// 2 x 1, 1 x 2
		// -> 1은 없음
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 3;

		// 짝수의 케이스마다 2의 새로운 경우의 수들이 발생
		// 기본적으로 for문 돌면서 이전 케이스에서 * 3 연산은 발생 3= dp[2]
		// 영역을 나눠서 생각. 6의 경우 4-2 2-4 인데
		// 4-2 -> dp[4]*dp[2]
		// 2-4 -> 2* 2 ( dp[4]가 아닌 2인 이유는 4에서의 새로운 경우에 대한 연산)
		// dp[4]로 연산할 시, 중복 발생 -> 답 안됨
		// 이중 포문의 의미는, 해당 영역에 대한 새로운 경우 2와,
		// 기존 다른 영역의 경우의 수를 곱하여 계산되는 결과.

		for (int i = 4; i <= n; i += 2) {
			dp[i] = dp[i - 2] * 3;
			for (int j = 4; j <= i; j += 2) {
				dp[i] += dp[i - j] * 2;// 2 : 해당 영역 나눔에서 새로운 경우의 수
				// dp[i-j] : 해당 영역의 경우의 수.
			}
		}
		System.out.println(dp[n]);
	}

}
