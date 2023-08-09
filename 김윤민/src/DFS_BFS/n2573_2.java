package DFS_BFS;

import java.io.*;
import java.util.*;

//빙산
public class n2573_2 {
	public static int n, m, max;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };
	public static int[][] map; //map2는 항상 클론.
	public static int[][] map2;
	public static boolean[][] visited;

	// 각 높이는 1년마다 동서남북 0이 저장된 칸 개수만큼 줄어든다.
	// 높이는 0보다 낮아지지 X
	// 빙산이 두 덩어리 이상으로 분리되는 최초의 시간.
	// 다 녹을때 까지 두덩어리 이상X -> 0 출력
	public static int Solution() {
		int cnt=0,year=0;
		while(cnt<2) {
			map2 = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
			
			visited=new boolean[n][m];
			cnt=0;
			for(int i=1;i<n-1;i++) { //섬의 개수 센다.
				for(int j=1;j<m-1;j++) {
					if(map2[i][j]!=0) {
						cnt++;
						dfs(i,j);
					}
				}
			}
			if(cnt==0) { //다 녹았으면 0으로 리턴
				year=0;
				break;
			}
			if(cnt>=2) break; //만약 2개 이상이면 break
			//아니라면 1년 bfs진행
			bfs();
			year++;
			
		}
		return year;
	}

	public static void bfs() { //1년 감소
		map2 = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		Deque<Point> q = new ArrayDeque<>();
		visited = new boolean[n][m];
		
		for(int i=1;i<n-1;i++) {//빙산인것만 offer
			for(int j=1;j<m-1;j++) {
				if(map2[i][j]!=0) {
					q.offer(new Point(i,j));
				}
			}
		}
		while (!q.isEmpty()) {
			Point now = q.poll();
			if(visited[now.y][now.x]) continue;
			visited[now.y][now.x]=true;
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(can(ny,nx)) {//가능한 경로인데,
					if(map[ny][nx]==0) {//주위에 0이 있으면
						//-1을 하는데 0보다 클때만 감소
						map2[now.y][now.x]=map2[now.y][now.x]-1>=0?map2[now.y][now.x]-1:0;
					}
				}
			}
		}
		map = map2;
	}
	public static void dfs(int y,int x) {
		map2[y][x]=0;
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			if(can(ny,nx)&&map[ny][nx]!=0) {
				dfs(ny,nx);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// input end
		
		//logic
		System.out.println(Solution());

	}

	public static boolean can(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < m) {
			return true;
		}
		return false;
	}
	static class Point {
		public int x, y;

		Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}

}
