package swea;

import java.io.*;
import java.util.*;
//특이한 자석
public class n4013 {
	static int k, cnt;
	static int[][] magnet;
	static int[] point; // 직접 돌리는게 아니고 맨 위 위치만 가리킴.
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int c = 1; c <= tc; c++) {
			// 자석 회전시키는 횟수 k
			k = Integer.parseInt(br.readLine());
			StringTokenizer st;
			magnet = new int[4][8];
			point = new int[4];// 초기 0
			for (int i = 0; i < 4; i++) { // 4개의 자석 8개의 날
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// N : 0 , S = 1
			// 자석 num, 회전 1 : 시계, -1 :반시계
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;//0~3 이므로 -1해쥼
				int rotate = Integer.parseInt(st.nextToken());
				// cnt, visit초기화
				visit = new boolean[4];
				cnt = 1;
				// logic
				magnet_Rotate(num, rotate);
			}

			// 회전 끝났을 때,
			// N : 0 / S : 1 2 4 8
			// 점수 총합?
			int n = 1;
			int result = 0;
			for (int i = 0; i < 4; i++) {
				int pos = point[i];
				if (magnet[i][pos] == 1) {
					result += n;
				}
				n *= 2;
			}
			sb.append("#").append(c).append(" ").append(result).append('\n');
		}
		System.out.println(sb);

	}

	// N : 0 , S = 1
	// 자석 num, 회전 1 : 시계, -1 :반시계
	static void magnet_Rotate(int num, int dir) {
		// 위치는 num.
		// num-1 위치와 num+1위치 자석을 확인
		visit[num] = true;
		boolean flag = false;
		if (cnt == 1)
			flag = true; // 첫번째 자석은 무조건 돈다.
		// case 1 : num-1
		if (num - 1 >= 0) {
			int prevMagnet = magnet[num - 1][searchPos(point[num - 1], 2)];
			if (prevMagnet != magnet[num][searchPos(point[num], 6)]) { // 다른거면 돌 수 있다.
				flag = true; // 다 하고나서 pos바꿔야하므로 flag만 true로 변경
				if (!visit[num - 1]) { // 왔다간 자석이 아니라면
					int ndir = dir == 1 ? -1 : 1; // 반대방향으로
					magnet_Rotate(num - 1, ndir);// dfs진행
				}
			}
		}

		// case 2 : num+1
		if (num + 1 < 4) {
			int nextMagnet = magnet[num + 1][searchPos(point[num + 1], 6)];
			if (nextMagnet != magnet[num][searchPos(point[num], 2)]) { // 다른거면 돌 수 있다.
				flag = true;
				if (!visit[num + 1]) {
					int ndir = dir == 1 ? -1 : 1;
					magnet_Rotate(num + 1, ndir);
				}

			}
		}
		// 왼 오른쪽 다 돌렸으면
		// 내꺼 빨간 화살표 위치 변경!
		point[num] = flag ? dir == 1 ? searchPos(point[num], -1) : searchPos(point[num], 1) : point[num];

	}

	static int searchPos(int pos, int plus) {
		if (plus > 0) {
			return (pos + plus) % 8;
		} else {
			return pos + plus < 0 ? pos + plus + 8 : pos + plus;

		}

	}
}
