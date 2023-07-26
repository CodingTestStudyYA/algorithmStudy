package DP;

import java.io.*;
import java.util.*;

public class n11048 {

	public static int[] dx = { 1, 1, 0 };
	public static int[] dy = { 1, 0, 1 };
	public static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n + 1][m + 1];
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// default 초기값 세팅
		dp[1][1]=map[1][1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				for (int k = 0; k < 3; k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];
					if (can(ny, nx)) {
						dp[ny][nx] = Math.max(map[ny][nx] + dp[i][j], dp[ny][nx]);
					}
				}
			}
		}
		System.out.println(dp[n][m]);

	}

	public static boolean can(int y, int x) {
		if (y >= 0 && y <= n && x >= 0 && x <= m) {
			return true;
		}
		return false;
	}

}
