import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ3190 {

	static int N, K, L;
	static int[][] map; // 사과가 있는 곳은 1, 아니면 0, 뱀이 있으면 -1
	static PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // 명령어 넣기
	static int second;

	// 하 좌 상 우
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, -1, 0, 1 };
	static String[] s = { "하", "좌", "상", "우" };

	// 뱀이 차지하는 좌표의 머리위치와 꼬리 위치만 저장하면 될듯

	static Deque<int[]> dq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 1;
		}

		L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char op = st.nextToken().charAt(0); // 그대로 넣어도 노상관
			q.offer(new int[] { time, op });
		}

		map[1][1] = -1; // 시작지점
		dq.push(new int[] { 1, 1 });
		doGame();
		System.out.println(second);
	}

	// 하 좌 상 우

	static void doGame() {
		// 시작지점
		// deque 에서 push로 넣고(위에 쌓기)
		// deque 에서 poll로 빼줌 (뒤에 빼기)

		int dir = 3; // 처음에 오른쪽 방향 보고 있음
		second = 0;

		while (true) {

			second++;


			int[] head = dq.peekFirst();
			//몸을 먼저 늘린다
			int ny = head[0] + dy[dir];
			int nx = head[1] + dx[dir];
//			System.out.println("머리 : " + ny + ", " + nx);
//			System.out.println("꼬리 : "+Arrays.toString(dq.peekLast()));

			// 범위를 나가면 중단한다
			if (!(ny >= 1 && ny <= N && nx >= 1 && nx <= N))
				return;
			// 자기 몸에 부딪히면중단한다
			if (map[ny][nx] == -1)
				return;

			// 사과가 없을 경우에
			if (map[ny][nx] != 1) {
				// 꼬리가 위치한 칸은 비워준다
				int[] tail = dq.pollLast();
				map[tail[0]][tail[1]] = 0; // 꼬리를 빼었으니 0으로
			} else {
//				System.out.println("사과 먹음");
			}

			map[ny][nx] = -1; // 가는 위치를 -1로 만들어 주고
			dq.offerFirst(new int[] { ny, nx }); // 머리의 이동

			if(!q.isEmpty()) {
				int[] oper = q.peek();
				if (oper[0] == second) {
//					System.out.println(oper[1] + " " + second);

					q.poll(); // 명령어 제거
					int op = oper[1];

					if (op == 'D') {
//						System.out.println("D");
						dir = (dir + 1) % 4;
//						System.out.println(s[dir]);
					} else {
//						System.out.println("L");
						// 왼쪽으로 돌기

						if (dir == 0)
							dir = 3;
						else
							dir = dir - 1;
					}
				}
			}

		}

	}
}
