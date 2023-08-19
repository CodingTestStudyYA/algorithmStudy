package DFS_BFS;

import java.io.*;
import java.util.*;

public class n17070 {
	// 파이프 - | \ 가능
	// 2개의 연속된 칸을 차지
	// 파이프 밀어서 이동
	// 파이프는 항상 빈 칸만 차지

	// →, ↘, ↓ 방향 밀 수 있고
	// 밀면서 회전 가능 ( 45 도만 )

	// 빈칸은 0 벽은 1
	// 파이프의 한쪽 끝을 N,N으로 이동시키는 경우의 수 합
	static int n, result;
	static int[][] map;
	// Horizontal vertical diagonal
	static final int HORIZONTAL = 1, VERTICAL = 2, DIAGONAL = 3;

	static boolean[][] visit;
	// case 1 가로
	static int[] dy1 = { 0, 1 }; // 오른, 대각
	static int[] dx1 = { 1, 1 };

	// case 2 세로
	static int[] dy2 = { 1, 1 }; // 아래, 대각
	static int[] dx2 = { 0, 1 };

	// case 3 대각선
	static int[] dy3 = { 1, 1, 0 }; // 아래, 대각, 오른
	static int[] dx3 = { 0, 1, 1 };

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					visit[i][j] = true;
			}
		}
		// input end

		// 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지 = (0,0)(0,1)

		// 처음 시작은 0,2
		// 상태 = 가로
		// 상태정보도 기억해야함.
		visit[0][0] = true;
		visit[0][1] = true;
		if(map[n-1][n-1]==1) {
			result=0;
		}else {
			dfs(0, 1, HORIZONTAL);
		}
		System.out.println(result);
	}

	// HORIZONTAL, VERTICAL, DIAGONAL
	// 상태가 대각선일 때는 네 칸 모두 빈칸이어야만 한다.
	public static void dfs(int y, int x, int status) {
		if (y == n - 1 && x == n - 1) {
			result++;
			return;
		}
		if (status == HORIZONTAL) { // 상태가로면 (오른, 대각)
			for (int i = 0; i < 2; i++) {
				int nx = x + dx1[i];
				int ny = y + dy1[i];
				if (can(ny, nx) && visit[ny][nx] == false) {
					visit[ny][nx] = true;
					if (i == 0) {
						dfs(ny, nx, HORIZONTAL);
					} else {
						if(digoCan(y,x)) {
							dfs(ny, nx, DIAGONAL);
						}
					}
					visit[ny][nx] = false;
				}
			}

		} else if (status == VERTICAL) { // 상태가 세로면 (아래, 대각)
			for (int i = 0; i < 2; i++) {
				int nx = x + dx2[i];
				int ny = y + dy2[i];
				if (can(ny, nx) && visit[ny][nx] == false) {
					visit[ny][nx] = true;
					if (i == 0) {
						dfs(ny, nx, VERTICAL);
					} else {
						if(digoCan(y,x)) {
							dfs(ny, nx, DIAGONAL);
						}
					}
					visit[ny][nx] = false;
				}
			}
			
		} else if (status == DIAGONAL) { // 상태가 대각이면, ( 아래, 대각, 오른)
			for (int i = 0; i < 3; i++) {
				int nx = x + dx3[i];
				int ny = y + dy3[i];
				if (can(ny, nx) && visit[ny][nx] == false) {
					visit[ny][nx] = true;
					if (i == 0) {
						dfs(ny, nx, VERTICAL);
					} else if(i==1){
						if(digoCan(y,x)) {
							dfs(ny, nx, DIAGONAL);
						}
					}else {
						dfs(ny,nx, HORIZONTAL);
					}
					visit[ny][nx] = false;
				}
			}

		}

	}

	public static boolean can(int y, int x) {
		if (x < 0 || y < 0 || y >= n || x >= n)
			return false;
		return true;
	}
	public static boolean digoCan(int y,int x) {
		for(int i=0;i<3;i++) {
			int nx= x+dx3[i];
			int ny= y+dy3[i];
			if(!can(ny,nx))return false;
			if(map[ny][nx]!=0) return false;
		}
		return true;
	}
}
