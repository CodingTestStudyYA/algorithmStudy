package swea;

import java.io.*;
import java.util.*;

public class n7793 {
	// 스킬 : 매초 상하좌우 부식시키며 확장
	// 지은이, 여신 공간 = 스킬로부터 자유로움
	// N행 M열로 이뤄진 지도 내에서 수연이는 1초에 동서남북 이동 가능
	// 돌은 아예 접근 X
	// 스킬을 피해서 여신이 있는 공간에 도달하고싶다
	// 최소시간 + 악마의 손아귀 X
	static int N, M, result;
	static char[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static Deque<Point> dv;

	public static void main(String[] args) throws Exception {
		// input
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int C = 1; C <= TC; C++) {
			// N, M
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			Point suyeon = new Point(0, 0);
			
			// 초기화
			result = 0;
			dv = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'S')
						suyeon = new Point(i, j); //수연이 위치 기억
					if (map[i][j] == '*')
						dv.add(new Point(i, j)); //악마 Q에 넣음
				}
			}
			// input end
			// 돌 : X , 수연이 S, 여신 D, 악마 * 평범 .

			bfs(suyeon);
			sb.append("#").append(C).append(" ");
			if (result == 0)
				sb.append("GAME OVER");
			else
				sb.append(result);
			sb.append('\n');
		}
		System.out.println(sb);

	}

	public static void bfs(Point e) { // 수연이 위치에서 시작
		Deque<Point> q = new ArrayDeque<>();
		q.offer(e);
		while (!q.isEmpty()) {
			// 악마가 먼저 퍼져 나감.
			int dSize = dv.size(); // 0이면 자동으로 for문 실행 안함.
			for (int i = 0; i < dSize; i++) {
				Point nowDv = dv.poll(); 
				for (int j = 0; j < 4; j++) {
					int nx = nowDv.x + dx[j];
					int ny = nowDv.y + dy[j];
					if (can(ny, nx)) { // 범위내이고 돌이아니면
						// 평범한 지역이고, 수연이의 위치일 때만 퍼져 나가도록
						if (map[ny][nx] == '.' || map[ny][nx] == 'S') {
							map[ny][nx] = '*';
							dv.offer(new Point(ny, nx));
						}
						// 만약 그냥 바로 바꿔버리면 * 공간도 또 덮어써서 시간초과남 -> 지역 구분 필요

					}
				}
			}
			// bfs를 돌면서 가중치 부여 = > 가중치 : 시간
			int hSize = q.size();
			for (int i = 0; i < hSize; i++) {
				Point human = q.poll();
				for (int j = 0; j < 4; j++) {
					int ny = human.y + dy[j];
					int nx = human.x + dx[j];
					if (can(ny, nx) && map[ny][nx] != '*') {// 범위내, 돌X, 악마위치가 아닐때 접근 가능
						if (map[ny][nx] == 'D') {
							result = human.time + 1;
							return;
						}
						if (map[ny][nx] == '.') {
							map[ny][nx] = 'S';
							q.offer(new Point(ny, nx, human.time + 1)); //해당 시간이 최소시간
						}

					}
				}
			}

		}

	}

	public static boolean can(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M || map[y][x] == 'X')
			return false;
		return true;
	}

	static class Point {
		int y, x, time;

		Point(int y, int x) { // 악마만 사용하는 생성자
			this.y = y;
			this.x = x;
		}

		Point(int y, int x, int time) {// 사람만 접근 가능한 생성자
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}
}