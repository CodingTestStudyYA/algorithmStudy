package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//자두나무
public class n2240 {
	/*
	 * 자두가 허공에 있을때 잡아야함. 초마다 2개중 1개의 나무에서 열매. 그 나무 아래있으면 열매 먹
	 * 
	 * T초동안 떨어지는데 최대 W만큼만 움직 매 초마다 어느나무에서 떨어지는지 알때 받을수 있는 자두의개수는?
	 * 
	 * 초기 : 1번
	 * 
	 */
	static int T, W;
	static int[] arr;

	/*
	 * 3차원배열 T,W,(그대로,옮길때)
	 * 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[][][] dp = new int[T + 1][W + 1][3];
		// 초기값 1초일때는 세팅 필요
		if (arr[1] == 1) { // 안움직이고 자두 바로 먹음
			dp[1][0][1] = 1; // 1번나무 냠
			dp[1][1][2] = 0;
		} else { // 움직이고 자두 먹음
			dp[1][0][2] = 1;
			dp[1][0][1] = 0;
		}

		for (int i = 2; i <= T; i++) {
			if (arr[i] == 1) { // 1번 자두
				// 초기값
				dp[i][0][1] = dp[i - 1][0][1] + 1; // 안움직이고 계속 1번자리에 있던경우+1
				dp[i][0][2] = dp[i - 1][0][2]; // 그대로
				for (int w = 1; w <= W; w++) {
					dp[i][w][1] = Math.max(dp[i - 1][w][1], dp[i - 1][w - 1][2]) + 1; // 움직이기전 vs 안움직였을때
					dp[i][w][2] = Math.max(dp[i - 1][w - 1][1], dp[i - 1][w][2]);
				}
			} else {// 2번 자두
				dp[i][0][1] = dp[i - 1][0][1];
				dp[i][0][2] = dp[i - 1][0][2] + 1;

				for (int w = 1; w <= W; w++) {
					dp[i][w][1] = Math.max(dp[i - 1][w][1], dp[i - 1][w - 1][2]);
					dp[i][w][2] = Math.max(dp[i - 1][w - 1][1], dp[i - 1][w][2]) + 1;
				}
			}
		}
		int result = 0;
		for (int w = 0; w <= W; w++) {
			 result = Math.max(result, Math.max(dp[T][w][1], dp[T][w][2]));
		}
		System.out.println(result);
	}
}
