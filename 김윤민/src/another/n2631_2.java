package another;

import java.io.*;
import java.util.*;
//줄세우기 복습

public class n2631_2 {
	/*
	 * 최장증가수열로 푸는거였는데 어떻게했더라.. 자기 위치에서 최대로 순서대로 설 수 있는 개수를 구함. 전체 N명에서 그 최대를 뺀 친구들만
	 * 이동하면 되는 문제.=> N - ans(LIS)
	 * 
	 */
	static int N;
	static int[] list;

	public static void main(String[] args) throws Exception {
		// input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		// input end

		// logic

		int[] LIS = new int[N];
		// 초기화
		for (int i = 0; i < N; i++) {
			LIS[i] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (list[j] < list[i]) {// 가지고 있는 누적값을 가져옴.
					LIS[i] = Math.max(LIS[i], LIS[j] + 1);
				}
			}
		}

		int ans = 0;
		for (int i : LIS) {
			ans = Math.max(i, ans);
		}
		System.out.println(N - ans);
	}
}
