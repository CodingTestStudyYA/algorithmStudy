package DFS_BFS;
import java.io.*;
import java.util.*;
//보물섬
public class n2589 {
	/*
	 * 육지 L, 바다 W
	 * 육지 상하좌우 이웃한 육지로만 이동
	 * 한칸에 한시간
	 * 보물은 서로 최단거리로 이동, 가장 긴시간이 걸리는 육지 두곳에 있음
	 * 같은것을 두 번 이상 지나가거나, 멀리 돌아가면 안됨.
	 * 보물이 묻혀있는 두 곳간 최단거리로 이동하는 시간
	 */
	static int N,M;
	static char[][] map;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		// input
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		ArrayList<Point> pList = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String s= br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='L') {
					pList.add(new Point(i,j)); //육지 다 담아줌
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (Point point : pList) { //육지 쌩 bfs돌리기 이게맞나 ㅎㅋ
			int result = bfs(point);
			max = Math.max(max, result);
		}
		System.out.println(max);
	}
	static int bfs(Point p) {
		int cntMax=0;
		Deque<Point> dq = new ArrayDeque<>();
		dq.offer(p);
		boolean[][] visit = new boolean[N][M];
		visit[p.y][p.x]=true;
		while(!dq.isEmpty()) {
			Point now = dq.poll();
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(can(ny,nx)&&map[ny][nx]=='L'&&!visit[ny][nx]) {
					visit[ny][nx]=true;
					cntMax = Math.max(cntMax, now.cnt+1); //돌면서 max값 갱신
					dq.offer(new Point(ny,nx,now.cnt+1)); 
				}
			}
		}
		return cntMax;
	}
	static boolean can (int y,int x) {
		if(y<0||x<0||y>=N||x>=M) return false;
		return true;
	}
	static class Point{
		int y,x,cnt;
		Point(int y,int x){
			this.y=y;
			this.x=x;
		}
		Point(int y,int x,int cnt){
			this(y,x);
			this.cnt=cnt;
		}
	}
}
