package DFS_BFS;

//보물섬(복습)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class n2589_2 {
	static int N, M;
	static char[][] map;
	static int[][] ans;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		ans = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		// input end
		
		//logic
		System.out.println(solution());
	}

	public static int solution() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					int tmp = bfs(i, j);
					result = Math.max(result, tmp);
				}
			}
		}
		return result;
	}

	public static int bfs(int y, int x) {
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(y, x));
		int max = 0;
		visit = new boolean[N][M];
		visit[y][x] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(can(ny,nx)&&!visit[ny][nx]&&map[ny][nx]=='L') {
					visit[ny][nx]=true;
					q.offer(new Point(ny,nx,now.w+1));
					max = Math.max(max, now.w+1);
				}
			}
		}
		return max;
	}

	static boolean can(int y, int x) {
		if (y < 0 || x < 0 || x >= M || y >= N)
			return false;
		return true;
	}

	static class Point {
		int y, x, w;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		Point(int y, int x, int w) {
			this(y, x);
			this.w = w;
		}
	}

}
