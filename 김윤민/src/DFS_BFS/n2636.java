package DFS_BFS;

import java.io.*;
import java.util.*;
//치즈
public class n2636 {
	/*
	 * 이게 맞나 처음에 BFS돌려서 blank표시, 2중 for문 돌려서 만약 사방 중에 b가 있으면 치즈 외곽 외곽 치즈들만 BFS 돌림.
	 * 근데 돌리는 와중에 만약에 주위에 공기구멍있으면 그 구멍도 다 b로 변경 (또 bfs?)
	 */
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static Deque<Point> cheese;
	static StringBuilder sb =new StringBuilder();
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end
		// logic
		cheese = new ArrayDeque<>();
		bfs(0, 0); // blank 기록하고
		int ans = 0;
		int prevSize = cheese.size(); //치즈 이전 사이즈 기록
		while (true) {
			if (cheese.size() == 0) {
				break;
			}
			ans++;
			prevSize = cheese.size();
			cheeseOut();
		}
		sb.append(ans).append('\n').append(prevSize);
		System.out.println(sb);
	}

	// blank : 2
	//치즈 밖의 공간(산화공간2로 변경)
	static void bfs(int y, int x) { 
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(y, x));
		boolean[][] check = new boolean[N][M];
		while (!q.isEmpty()) { //bfs진행
			Point now = q.poll();
			if (check[now.y][now.x])
				continue;
			check[now.y][now.x] = true;
			map[now.y][now.x] = 2; //2로 변경
			for (int i = 0; i < 4; i++) { //4방에
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (can(ny, nx) && !check[ny][nx]) { //범위고, 가지 않았고,
					if (map[ny][nx] == 0) { //blank면 q에 offer ( 2로 변경하기 위해서) 
						q.offer(new Point(ny, nx));
					}
					//1이면 치즈 외곽공간
					//cheese 큐에 저장되어 있지 않은경우에만 offer
					if (map[ny][nx] == 1&&!cheese.contains(new Point(ny,nx))) { 
						cheese.offer(new Point(ny, nx));
						check[ny][nx] = true; //해당 외곽치즈 이미 offer했다고 true
					}

				}
			}
		}
	}

	// 치즈 외곽 순차적 산화
	static void cheeseOut() {
		visit = new boolean[N][M];
		int size = cheese.size(); //현재 치즈 위치에서 
		while (size > 0) {
			size--;
			Point now = cheese.poll();
			if (visit[now.y][now.x])
				continue;
			// 들어와 있는 애는 무조건 산화되는 애.
			visit[now.y][now.x] = true;
			map[now.y][now.x] = 2; //blank로 바꿔주고
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (can(ny, nx) && !visit[ny][nx]) {
					if (map[ny][nx] == 0) { //4방에 만약 0이있다면 그 공간은 산화공간으로 채워져야함.
						bfs(ny, nx);
						visit[ny][nx] = true;
					}else if(map[ny][nx]==1&&!cheese.contains(new Point(ny,nx))) {
						cheese.offer(new Point(ny,nx));
					}
				}
			}
		}
	}

	static boolean can(int y, int x) {
		if (y < 0 || x < 0 || x >= M || y >= N)
			return false;
		return true;
	}

	static class Point {
		int x, y;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			if(this.x==p.x&&this.y==p.y) return true;
			 return false;
		}
		
	}
}
