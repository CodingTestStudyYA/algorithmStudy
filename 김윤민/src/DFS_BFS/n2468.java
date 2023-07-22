package DFS_BFS;
import java.util.*;
import java.io.*;

public class n2468 {
	static int[][] map;
	static int[][] d_map;
	static int N,cnt,max;
	static int[] dx= {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	public static int Solution() {
		int result=0;
		for(int i=0;i<=max;i++) {
			cnt=0;
			map = Arrays.stream(d_map).map(int[]::clone).toArray(int[][]::new);
			for(int y=0;y<N;y++) {
				for(int x=0;x<N;x++) {
					if(map[y][x]<i) {
						map[y][x]=0;
					}
				}
			}
			for(int y=0;y<N;y++) {
				for(int x=0;x<N;x++) {
					if(map[y][x]!=0) {
						cnt++;
						bfs(x,y); //i : 높이 
					}
				}
			}
			result=Math.max(result, cnt);
		}
		
		return result;
	}
	public static void bfs(int x,int y) { //point(x,y) / h : 높이 
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x,y));
		map[y][x]=0;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int k=0;k<4;k++) {
				int nx = p.x+dx[k];
				int ny= p.y+dy[k];
				if(can(nx,ny)) {
					map[ny][nx]=0;
					q.offer(new Point(nx,ny));
				}
			}
		}
		
		
	}
	public static boolean can(int x,int y) {
		if(x>=0&&x<N&&y>=0&&y<N&&map[y][x]!=0) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map= new int[N][N];
		d_map = new int[N][N];
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				d_map[i][j]=Integer.parseInt(st.nextToken());
				max = Math.max(d_map[i][j], max);
			}
		}
		
		System.out.println(Solution());

	}

}
