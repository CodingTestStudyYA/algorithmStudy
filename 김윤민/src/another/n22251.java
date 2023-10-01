package another;

import java.io.*;
import java.util.*;

public class n22251 {
	/*
	 * N층, K자리 수, P개 반전
	 */
	static int N, K, P, X;
	static boolean[][] num;
	static int[][] numD;

	static int[] now;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		now = new int[K];
		int tmpX = X;
		for (int i = K - 1; i >= 0; i--) {
			now[i] = tmpX % 10;
			tmpX /= 10;
		}
		// input end

		// logic
		init();
		// N층까지 되는지 한번 보자
		int ans = 0;
//		for (int i = 1; i <= N; i++) {
//			if (i == X)
//				continue;// 같으면 넘기고
//			if (check(i)) {
//				
//				ans++;
//			}
//		}
		check(3);
		System.out.println(ans);
	}

	static boolean check(int tar) {
		int[] target = new int[K];
		int result = tar;
		for (int i = K - 1; i >= 0; i--) {
			target[i] = tar % 10;
			tar /= 10;
		}
		int canCount = 0;
		for (int i = 0; i < K; i++) {// K자리까지 되는지 판별
			if (target[i] != now[i]) {
				System.out.print(target[i] + "," + now[i] + " : ");
				int tmpCnt = canCount + numD[target[i]][now[i]];
				if (tmpCnt > P) {
					return false;
				} else {

					canCount = tmpCnt;
					System.out.println(canCount);

				}
			}
		}
		System.out.println(result + "," + canCount);
		return true;
	}

	static void init() {
		num = new boolean[10][8];
		numD = new int[10][10];// 각 숫자에 따라 다른 개수(차이)
		// 0 : 1 2 3 5 6 7
		Arrays.fill(num[0], true);
		num[0][4] = false;

		// 1 : 3 6
		num[1][3] = num[1][6] = true;

		// 2 : 1 3 4 5 7
		Arrays.fill(num[2], true);
		num[2][2] = num[2][6] = false;

		// 3: 1 3 4 6 7
		Arrays.fill(num[3], true);
		num[3][2] = num[3][5] = false;

		// 4 : 2 3 4 6
		num[4][2] = num[4][3] = num[4][4] = num[4][6] = true;
		

		// 5 : 1 2 4 6 7
		Arrays.fill(num[5], true);
		num[5][3] = num[5][5] = false;

		// 6 : 1 2 4 5 6 7
		Arrays.fill(num[6], true);
		num[6][3] = false;

		// 7 : 1 3 6
		num[7][1] = num[7][3] = num[7][6] = true;

		// 8 : all
		Arrays.fill(num[8], true);

		// 9 : 1 2 3 4 6 7
		Arrays.fill(num[9], true);
		num[9][5] = false;

		// num 상태 채우기 끝
		// 숫자마다 차이 구하는 numD

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int z = 1; z <= 7; z++) {
					if (num[i][z] != num[j][z]) {
						numD[i][j] += 1;
					}
				}
			}

		}
	}
}
