package Implementation;

import java.io.*;
import java.util.*;

//미세먼지 안녕!
public class n17144 {
	/*
	 * 공기청정기 - 1열, 두행 차지
	 * 
	 * 1. 미세먼지 확산 ( 4방향 ) 단 공기청정기가 있으면 확산 x, 범위가 아니면 확산 x 확산되는 양 : A/5 현재 칸에 남은 양은 A
	 * - ( A/5 ) * ( 확산된 방향 개수 ) 2. 공기청정기 작동 위쪽 : 반시계로 순환, 아래쪽 : 시계로 순환 바람이 불면 미세먼지가
	 * 바람의 방향대로 한칸씩 이동 공기청정기로 들어간 미세먼지 - 정화
	 * 
	 * T초가 지난 후 방에 남아있는 미세먼지 양
	 */
	static int R, C, T;
	static int[][] map;
	static int[] dy = { 0, 1, 0, -1 }; // 오 아래 왼 위
	static int[] dx = { 1, 0, -1, 0 };
	static Deque<Dust> dust;
	static int[] cleaner;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cleaner = new int[2];
		dust = new ArrayDeque<>();
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					dust.add(new Dust(i, j, map[i][j])); //먼지면 추가
				if (map[i][j] == -1) { //-1이면 공기청정기 기억
					cleaner[cnt] = i;
					cnt++;
				}
			}
		}
		for(int i=0;i<T;i++) {
			dustExpand();
			dustClear();
			getDust();
		}
		System.out.println(getResult());
	}

	
	static void printMap() { //디버깅용 ㅎㅋ
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	/*
	 * 사이즈만큼 poll해서 확산. 단, 기존에 값이 존재하는 경우 더해줘야함.
	 */
	static void dustExpand() {
		while(!dust.isEmpty()) { //먼지 다 빼서 확산
			Dust now = dust.poll();
			if(now.w<5) continue; //5 미만이면 어짜피 확산 못됨 -> 그대로
			int spreadDust = now.w/5;
			int cnt=0;
			for(int i=0;i<4;i++){
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				//공기청정기가 아니고 map안이면 확산 가능
				if(can(ny,nx)) {  
					map[ny][nx]+=spreadDust;//해당 위치에 확산
					cnt++;
				}
			}
			map[now.y][now.x]-= spreadDust*cnt; //확산된 만큼 빼준다.
		}
	}

	static void dustClear() {
		// 1. 위쪽 청소
		// 위 오른 아래 왼 : 3 0 1 2
		int idx = 3;
		int y = cleaner[0];
		int x = 0;
		boolean flag = false;
		while (true) {
			int ny = y + dy[idx];
			int nx = x + dx[idx];
			if (ny < 0 || nx < 0 || ny >= R || nx >= C) { //범위 벗어나면 idx바꾸고 continue;
				idx = (idx + 1) % 4 == 0 ? 0 : idx + 1;
				continue;
			}
			if (ny == cleaner[0] && nx == 0) { //공기청정기면 break;
				map[y][x] = 0;
				break;
			} else {
				map[y][x] = map[ny][nx]; //아니면 이동될 걸 덮어씀.
				y = ny;
				x = nx;
				//한번만 바꾸기 위해서 flag를 사용한다.
				if (!flag && y == cleaner[0]) { //공기청정기와 같은 y값을 가질때, idx바꿔야하므로
					flag = true;//true로 바꾸고
					idx = (idx + 1) % 4 == 0 ? 0 : idx + 1;//idx이동
				}
			}
		}

		// 2. 아래쪽 청소
		// 아래 오른 위 왼 : 1 0 3 2
		idx = 1;
		y = cleaner[1];
		x = 0;
		flag = false;
		while (true) {
			int ny = y + dy[idx];
			int nx = x + dx[idx];
			if (ny < 0 || nx < 0 || ny >= R || nx >= C) {
				idx = (idx - 1) < 0 ? 3 : idx - 1;
				continue;
			}
			if (ny == cleaner[1] && nx == 0) {
				map[y][x] = 0;
				break;
			} else {
				map[y][x] = map[ny][nx];
				y = ny;
				x = nx;
				if (!flag && y == cleaner[1]) {
					flag = true;
					idx = (idx - 1) < 0 ? 3 : idx - 1;
				}
			}
		}
		map[cleaner[0]][0]=-1;
		map[cleaner[1]][0]=-1;
	}
	static void getDust() { //먼지겟

		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]>0) dust.add(new Dust(i,j,map[i][j]));
			}
		}
		
	}
	static int getResult() {
		int result=0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++){
				if(map[i][j]>0) result+=map[i][j];
			}
		}
		return result;
	}
	static boolean can(int y, int x) {
		if (y < 0 || x < 0 || y >= R || x >= C)
			return false;
		// 공기청정기에 있는곳으로는 확산 못함.->false리턴
		for (int i = 0; i < 2; i++) {
			if (cleaner[i] == y && x == 0) {
				return false;
			}
		}
		return true;
	}

	static class Dust {
		int y, x, w;

		Dust(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
	}
}


