package DP;

import java.io.*;
import java.util.*;

public class n17070 {
	static int n;
	static int[][] map;
	static int[][][] dp; // y, x, status = 0 : 가로, 1 : 세로, 2 : 대각

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		dp = new int[n][n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end

		// default
		dp[0][1][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 2; j < n; j++) {
				if (i == 0 && j == 1) continue; // 초기 값일 경우 continue;
				if (map[i][j] == 1) continue;

				// 해당 위치에서 dp진행
				// 45만 회전 가능.
				// 가로상태인 경우, 왼쪽가로 + 왼쪽이 대각선인 경우 접근가능
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				if (i == 0) continue;
				dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

				// 세로상태인 경우, 위의 세로 + 위의 대각선인 경우 접근 가능.
				// 대각선인 경우, 왼쪽과 상단이 벽일경우 못함 = continue;
				if (map[i - 1][j] == 1 || map[i][j - 1] == 1)
					continue;
				// 대각선인 경우, 왼쪽상단의 가로,세로,대각선인 경우 모두 더함.
				// 왜냐면,, 대각선은 다 접근가능하니까 다 가져와서 더해줌.
				dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];

			}
		}
		// 마지막 경로 모두 더해주면 됨.
		System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);

	}

	public static boolean can(int y, int x) {
		if (x < 0 || y < 0 || x >= n || y >= n)
			return false;
		return true;
	}

}
