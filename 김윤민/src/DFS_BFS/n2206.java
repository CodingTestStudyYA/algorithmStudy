package DFS_BFS;

import java.io.*;
import java.util.*;

//벽부수고 이동하기
public class n2206 {
	// BFS, 못하면 -1 출력

	/*
	 * visit 3차원 배열 [1][y][x]=> 벽 부순 상태 [0][y][x]=> 벽 안부순 상태
	 */
	static int N, M;
	static int[][] map;
	static boolean[][][] visit;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		// input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		// input end
		int result = BFS();
		System.out.println(result);

	}

	// 0 : 이동가능, 1 : 이동 불가능
	static int BFS() {
		int result = -1;
		visit = new boolean[2][N][M];
		visit[0][0][0] = true;
		visit[1][0][0] = true;
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 0, false));
		while (!q.isEmpty()) {
			Point now = q.poll();
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(!can(ny,nx)) continue;
				if(map[ny][nx]==0) { //갈수있으면
					if(now.flag) {//만약 부순적이 있는데
						if(!visit[1][ny][nx]) {//간적이 없다면
							if(ny==N-1&&nx==M-1) return now.cnt+1; //근데 끝이라면 now.cnt+1리턴
							q.add(new Point(ny,nx,now.cnt+1,true));//아니면 q에 offer
							visit[1][ny][nx]=true;//부순곳true처리
						}
					}else {//안부쉈는데
						if(!visit[0][ny][nx]) {//온적이 없으면
							if(ny==N-1&&nx==M-1) return now.cnt+1;
							q.add(new Point(ny,nx,now.cnt+1,false));
							visit[0][ny][nx]=true;
						}
					}
				}else if(map[ny][nx]==1) {//벽이면 부숴본다.
					if(!now.flag) { //부순적 없을때만!
						if(ny==N-1&&nx==M-1) return now.cnt+1;
						q.add(new Point(ny,nx,now.cnt+1,true));
						visit[1][ny][nx]=true;
					}
				}
			}	
		}
		return result;
	}
	static boolean can(int y,int x) {
		if(y<0||x<0||y>=N||x>=M) return false;
		return true;
	}
	static class Point {
		int y, x, cnt;
		boolean flag;

		Point(int y, int x, int cnt, boolean flag) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.flag = flag;
		}
	}
}
