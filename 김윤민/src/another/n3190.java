package another;

import java.io.*;
import java.util.*;

//사과먹으면 길이가 늘어난다
//벽이나 자기자신의 몸과 부딪히면 게임이 끝남
// 이동한 칸에 사과가 있으면, 그 칸에 있던 사과 없어지고 꼬리는 움직이지 않는다
// 사과 없으면, 몸 길이 줄여서 꼬리칸을 비워준다.(몸길이 안변함)

// 몇 초에 끝나는지 계산하라.
public class n3190 {
	static int N, K, L, dir;
	static int[][] map;
	static Map<Integer, Character> dirList;
	static int[] dy = { 0, 1, 0, -1 };// 오 아래 왼 위
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 10;
		}
		L = Integer.parseInt(br.readLine());
		dirList = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			dirList.put(time, d);
		}
		int time = 0;
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(new Point(1, 1));
		dir = 0;
		int x, y;
		x = y = 1;
		while (true) {
			time++;
			x = x + dx[dir]; //다음으로 갈 위치 저장
			y = y + dy[dir]; 
			if (can(y, x) && !snake.contains(new Point(y, x))) { // 맵을 벗어나지 않고, 자기 자신의 몸이 아니면
				if (map[y][x] != 0) {//사과면 사과 먹고 길이 +1
					map[y][x] = 0; //map 사과만 없애주고 poll안함. 왜냐면 몸통길이 +1이니까 꼬리 뺄 필요 없다.
				} else {
					snake.poll(); //사과 없으면 꼬리 poll
				}
				snake.add(new Point(y, x));  //머리 이동 위치 추가
			} else {
				break; // 맵을 벗어나거나, 자신의 몸에 부딪히면 break;
			}
			if (dirList.containsKey(time)) { // 해당 초가 포함되면 끝난 뒤에 머리 돌려야함.
				setDir(dirList.get(time)); //방향에 따라 머리돌림
			}
		}
		System.out.println(time);
	}

	static boolean can(int y, int x) {
		if (y < 1 || x < 1 || y > N || x > N)
			return false;
		return true;
	}

	static void setDir(char d) {
		if (d == 'L') {
			dir= (dir + 3) % 4;
		} else if (d == 'D') {
			dir= (dir + 5) % 4;
		}
		
	}

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			if (p.x == this.x && this.y == p.y) {
				return true;
			}
			return false;
		}

	}
}
