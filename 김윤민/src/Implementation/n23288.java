package Implementation;
import java.util.*;
import java.io.*;

//가장 처음에 주사위의 이동 방향은 동쪽
/*	1. 이동방향으로 한칸 굴러간다.
 *		칸이 없으면, 이동방향 반대로한 다음 굴러간다.
 *	2. 도착한 칸( x,y ) 에 대한 점수를 획득
 *		사방으로 인접한 칸들 중 B를 가진 칸의 수 == C
 * 		점수 = C * B 
 *	3. 주사위 아랫면에 있는 정수 A와 칸 B를 비교해서 이동방향 결정
 *		A>B : 90도 시계방향
 * 		A<B : 90도 반시계방향
 * 		A=B : 방향변화 없음
 */

public class n23288 {
	static int N, M, K;
	static int[][] map;
	static int[] dice = {1,3,4,5,2,6}; // top, east, west, south, north, bottom
	static int dir = 0; // 동남서북 0~3
	static int[] dx = { 1, 0,-1,0 }; // 동남서북
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end
		// K : 이동횟수
		// 각 이동에서 획득하는 점수의 합
		int x = 0, y = 0;
		int cnt = 0;
		int result = 0;
		while (cnt < K) {
			int ny = y + dy[dir]; //해당방향으로 이동
			int nx = x + dx[dir];
			if (can(ny, nx)) { //가능하면 
				cnt++;
				if(dir==0) turnEast(); //주사위 굴린다 동남서북
				else if(dir==1) turnSouth();
				else if(dir==2) turnWest();
				else if(dir==3) turnNorth();
				
				int A = dice[5]; // bottom값
				int B = map[ny][nx]; // 해당 점수B
				y = ny; //위치 갱신
				x = nx;
				int C = bfs(ny, nx);
				result = result + (B * C); //결과값 누적 계산
				if (A > B) { // 90도 시계방향
					setDir(1);
				} else if (A < B) { // 90도 반시계방향
					setDir(-1);
				} // 같으면 방향 전환 없음
				
			} else {
				converse(); // 방향 전환
			}
		}
		System.out.println(result);

	}
	
	static int bfs(int y, int x) { //기본 bfs 인접 칸들 중 같은 숫자인 칸만 셈.
		Deque<Point> q = new ArrayDeque<>();
		int num = map[y][x]; //해당 점수B
		boolean[][] visit = new boolean[N][M];
		q.offer(new Point(y, x)); 
		int count = 1; //count 1부터 시작
		visit[y][x]=true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				if (can(ny, nx) &&map[ny][nx]==num&&visit[ny][nx]==false) {
					count++;
					visit[ny][nx]=true;
					q.offer(new Point(ny, nx));
				}
			}
		}
		return count;
	}

	static void setDir(int round) { //1: 시계, -1 : 반시계
		if(round ==1) { //동남서북동 , 동 0 남 1 서 2 북 3
			dir= (dir+1)%4;
		}else if(round ==-1) { //동북서남동
			dir = (dir+3)%4;
		}
		
	}

	static void converse() { // 역방향 동0 남 1 서 2 북 3
		if(dir<2) dir=dir+2;
		else dir = dir-2;
	}

	// 주사위 이동
	static void turnEast() { 
		int top = dice[0];
		int bottom = dice[5];
		int west = dice[2];
		int east = dice[1];
		dice[0] = west;
		dice[1] = top;
		dice[2] = bottom;
		dice[5] = east;
	}

	static void turnWest() {
		int top = dice[0];
		int bottom = dice[5];
		int west = dice[2];
		int east = dice[1];
		dice[2] = top;
		dice[0] = east;
		dice[5] = west;
		dice[1] = bottom;
	}

	static void turnNorth() {
		int top = dice[0];
		int bottom = dice[5];
		int south = dice[3];
		int north = dice[4];
		dice[0]=south;
		dice[3]=bottom;
		dice[4]=top;
		dice[5]=north;
	}

	static void turnSouth() {
		int top = dice[0];
		int bottom = dice[5];
		int south = dice[3];
		int north = dice[4];
		dice[4] = bottom;
		dice[0] = north;
		dice[5] = south;
		dice[3] = top;
	}
	static boolean can(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M)
			return false;
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
