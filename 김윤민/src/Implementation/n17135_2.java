package Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class n17135_2 {
	static int N, M, D;
	static int[][] map;
	static boolean[] visit;
	static int[] status;
	static ArrayList<Point> enemy;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		enemy = new ArrayList<>();
		visit = new boolean[M];
		status = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					enemy.add(new Point(i, j));
			}
		}
		perm(0, 0);
		System.out.println(max);

	}

	static void perm(int cnt, int idx) {
		if (cnt == 3) {
			int ans = game(status);
			max = Math.max(max, ans);
			return;
		}
		for (int i = idx; i < M; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			status[cnt] = i;
			perm(cnt + 1, i);
			visit[i] = false;
		}
	}

	/*
	 * game 거리가 D이하 -> 가장 가까운 적 공격 적들을 ArrayList에 담아서 관리 궁수의 위치 : [N][?] 한 턴이 끝나면
	 * 적들의 y값 : y = y+1 -> if(y==N) -> remove (뒤부터 돌것) enemy만큼 돌아서 죽어있지 않을때만
	 * result++;
	 */
	static int game(int[] arr) { // arr : 궁수의 위치
		int result = 0;
		ArrayList<Point> e = new ArrayList<>();
		for (Point p : enemy) {
			e.add(new Point(p.y,p.x));
		}
		// 턴 계속 돌기
		while (!e.isEmpty()) {
			for (int i = 0; i < 3; i++) { // 궁수들의 각 위치마다
				ArrayList<Point> canDeath = new ArrayList<>(); // 죽을수 있는 적을 담는다.
				int x = arr[i]; // 궁수의 x위치
				int y = N; // 궁수의 y위치
				for (int j = 0; j < e.size(); j++) { // enemy를 탐색하면서
					Point now = e.get(j);
					now.d = Math.abs(y - now.y) + Math.abs(x - now.x);
					if (now.d <= D) { // 죽일수있는 적을 찾아서
						canDeath.add(now); // list에 담는다.
					}
				}
				Collections.sort(canDeath); // 죽일수 있는 적들중에서 가장 가깝고 왼쪽에 오도록 정렬
				if (!canDeath.isEmpty()) { // 죽일수 있는 적이 있는 상태에서
					Point D = canDeath.get(0); // 가장 가까운 적을 고르고
					for (Point p : canDeath) {
						// 해당하는 적인데,
						if (p.y == D.y && p.x == D.x) {
							if(!p.death) {//죽지 않았으면
								p.death = true; //죽은 상태로 바꾸고
								result++; // result+1 
							}
							break;
						}
					}
					
					
				}
			} 
			// 한 라운드가 끝났는데
			ArrayList<Point> round = new ArrayList<>();
			for (Point p : e) {
				if (p.y + 1 == N) {
					continue;
				}
				if (p.death) {
					continue;
				}
				round.add(new Point(p.y + 1, p.x));
			}
			e = round; // 갱신
		}

		return result;
	}

	static class Point implements Comparable<Point> {
		int y, x, d;
		boolean death;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		Point(int y, int x, boolean death) {
			this(y, x);
			this.death = death;
		}

		@Override
		public int compareTo(Point o) {
			return this.d == o.d ? this.x - o.x : this.d - o.d;
		}
	}
}
