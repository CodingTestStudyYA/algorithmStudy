package DFS_BFS;

import java.io.*;
import java.util.*;

//적록색약
public class n10026 {
	/*
	 * RGB 상하좌우 같은색 = 같은 구역 적록색약 아닐때, 적록색약 일때 구역 수 구분해서 출력 (RG)B
	 */
	static int N;
	static char[][] map1;
	static char[][] map2;
	static int[] dy= {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	public static void main(String[] args) throws Exception {
		// input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map1 = new char[N][N];
		map2 = new char[N][N];
		//map자체를 적록색약인 사람과 아닌사람으로 분리
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map1[i][j] = s.charAt(j);
				map2[i][j] = (map1[i][j] == 'G') ? 'R' : map1[i][j];
			}
		}
		// input end

		// logic
		
		int result1 = getCount(map1);
		int result2 = getCount(map2);
		System.out.println(result1+" "+result2);

	}

	public static int getCount(char[][] arr) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 'Z') { //갔다온곳 -> Z니까 만약 안갔으면 bfs 
					cnt++;
					bfs(i, j, arr);
				}
			}
		}
		return cnt;
	}

	public static void bfs(int y, int x, char[][] arr) {
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(y, x));
		char tgt = arr[y][x]; //영역 색을 기억
		arr[y][x]='Z'; //체크한 곳은 영역색을 지워버리기 때문에 visit이 필요없다.
		while (!q.isEmpty()) {
			Point now = q.poll();
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(can(ny,nx)&&arr[ny][nx]==tgt) {//map내이고, 같은 색이면
					arr[ny][nx]='Z';//Z로 바꾸고
					q.offer(new Point(ny,nx));//offer
				}
			}
		}
	}
	static boolean can(int y,int x) {
		if(y<0||x<0||x>=N||y>=N) return false;
		return true;
	}
	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}