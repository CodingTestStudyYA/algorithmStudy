package DFS_BFS;

import java.io.*;
import java.util.*;

public class n7562 {
	public static int[] dx = { 1, 2, -1, -2, -1, -2, 1, 2 };
	public static int[] dy = { 2, 1, 2, 1, -2, -1, -2, -1 };
	public static int[] now,tg;
	public static int[][] map;
	public static int l;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		now = new int[2];
		tg = new int[2];
		for (int i = 1; i <= T; i++) {
			// 한 변의 길이, 나이트가 현재 있는 칸, 나이트가 이동하려하는 칸.
			l = Integer.parseInt(br.readLine());
			map = new int[l][l];
			st = new StringTokenizer(br.readLine());
			now[0] = Integer.parseInt(st.nextToken());
			now[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			tg[0] = Integer.parseInt(st.nextToken());
			tg[1] = Integer.parseInt(st.nextToken());
			sb.append(bfs(now[0],now[1])).append('\n');
		}
		System.out.println(sb.toString());
	}

	public static int bfs(int y,int x) {
		Deque<Point2> q = new ArrayDeque<>();
		map[y][x]=-1;//왔다 체크
		q.offer(new Point2(x,y,0));
		while(!q.isEmpty()) {
			Point2 n = q.poll();
			if(n.x==tg[1]&&n.y==tg[0]) {//poll했을 때, tg이라면, return
				return n.cnt;
			}
			for(int i=0;i<8;i++) {
				int nx=n.x+dx[i];
				int ny=n.y+dy[i];
				if(ny==tg[0]&&nx==tg[1]) { //다음 위치가 타겟이라면 +1하고 리턴
					return n.cnt+1;
				}
				if(can(ny,nx)) {
					map[ny][nx]=-1;//왔다 체크
					q.offer(new Point2(nx,ny,n.cnt+1));
				}
			}
		}
		return 0;
	}
	public static boolean can(int y, int x) {
		if(y>=0&&x>=0&&y<l&&x<l&&map[y][x]==0) {
			return true;
		}
		return false;
		
	}
	
}
class Point2{
	public int x,y,cnt;
	Point2(int x,int y,int cnt){
		this.x=x;this.y=y;this.cnt=cnt; //cnt저장필요
	}
}