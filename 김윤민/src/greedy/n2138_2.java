package greedy;

import java.io.*;
import java.util.*;

public class n2138_2 {
	static int N;
	static boolean[] now1;
	static boolean[] now2;
	static boolean[] tgt;

	public static void main(String[] args) throws Exception {
		// input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		now1 = new boolean[N]; // zero O
		now2 = new boolean[N]; // zero X
		tgt = new boolean[N];
		String s1 = br.readLine();
		String s2 = br.readLine();
		for (int i = 0; i < N; i++) {
			now1[i] = s1.charAt(i) - '0' == 0 ? false : true;
			now2[i] = s1.charAt(i) - '0' == 0 ? false : true;
			tgt[i] = s2.charAt(i) - '0' == 0 ? false : true;
		}
		// input end

		// logic

		// 1. 0인덱스의 전구를 반전시키고 시작하는 경우
		now1 = change(now1, 0);
		int result1 = greedy(now1, 1);
		// 2. 0인덱스의 전구를 반전시키지 않고 시작하는 경우
		int result2 = greedy(now2, 0);

		int answer = Math.min(result1, result2); // 최솟값을 가져온다.
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	public static int greedy(boolean[] now, int result) {
		// 무조건 두번째 부터 비교
		for (int i = 1; i < N; i++) {
			if (now[i - 1] != tgt[i - 1]) { // 이전의 전구가 같지 않다면
				now = change(now, i); // 전구를 바꿔주고
				result++; // 횟수 추가
			}
		}
		// 만약 같지않다면 MAXVALUE 리턴
		for (int i = 0; i < N; i++) {
			if (now[i] != tgt[i]) {
				return Integer.MAX_VALUE;
			}
		}

		return result;
	}

	public static boolean[] change(boolean[] now, int idx) {
		for (int i = idx - 1; i <= idx + 1; i++) {
			if (i < 0 || i >= N)
				continue;
			now[i] = !now[i];
		}
		return now;
	}
}
