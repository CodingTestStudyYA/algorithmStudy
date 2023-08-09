package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[][] og_matrix;
	static int[][] matrix;
	static Queue<Point> q;

	static boolean[][] visit;
	static int ans, melt;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		og_matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				og_matrix[i][j] = Integer.parseInt(st.nextToken());
				matrix[i][j] = og_matrix[i][j];

			}
		}

		while (true) {

			ans = 0;
			visit = new boolean[N][M];
			q = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (og_matrix[i][j] > 0 && !visit[i][j]) {
						q.offer(new Point(i, j));
						visit[i][j] = true;
						BFS();
						ans++;
					}
				}
			}

			if (ans > 1)
				break;
			if (ans == 0)
				break;
			

			melt();
			melt++;
//			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(og_matrix[i][j] + " ");
//				}
//				System.out.println();
//			}
//			

		}

		if (ans == 0)
			System.out.println(0);
		else
			System.out.println(melt);
	}

	static void BFS() {

		while (!q.isEmpty()) {

			Point cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;

			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (!visit[ny][nx] && og_matrix[ny][nx] > 0) {
						q.offer(new Point(ny, nx));
						visit[ny][nx] = true;
					}
				}
			}
		}
	}

	static void melt() {
		
	
		for(int i = 0; i< N; i++) {
			for(int j =0; j < M; j++) {
				og_matrix[i][j] = matrix[i][j]; 
			}
		}
		
		for(int x = 0; x< N; x++) {
			for(int j =0; j < M; j++) {
				if (og_matrix[x][j] > 0) {
					int cy = x; 
					int cx = j;
					
					for (int i = 0; i < 4; i++) {
						int ny = cy + dy[i];
						int nx = cx + dx[i];
						if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
							if (og_matrix[ny][nx] == 0 && matrix[cy][cx] > 0) {
								matrix[cy][cx]--;
							}
						}
					}
				}
			}
		}
	

		for(int i = 0; i< N; i++) {
			for(int j =0; j < M; j++) {
				og_matrix[i][j] = matrix[i][j]; 
			}
		}
	}
}

class Point {
	int x, y;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
