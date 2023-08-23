package Implementation;

import java.io.*;
import java.util.*;
//컨베이어 벨트 위의 로봇
public class n20055 {
	static int N, K;
	static int[] belt;// 벨트 내구성
	static boolean[] robot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		belt = new int[N * 2];
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		robot = new boolean[N];
		// input end
		// 내구도가 0인게 K이상일 때, 종료
		int index = 0;
		int count = 0;
		while (true) {
			count++;
			index = index - 1 < 0 ? 2 * N - 1 : index - 1; // 벨트 이동
			for (int i = N - 1; i >= 1; i--) { // 벨트 회전에 의한 로봇 이동
				robot[i] = robot[i - 1];
			}

			robot[0] = false;
			robot[N-1]=false; //N칸에 있어도 바로 내려야하므로 false로 해줌.
			
			for (int i = N - 1; i >= 1; i--) {
				// 벨트가 회전하려는 방향으로 한 칸 이동가능하면 이동
				if (robot[i] == false && robot[i - 1]) {
					// 현재 내구도가 1이상이면 이동해도 됨.
					int idx = (i + index) % (2 * N);
					if (belt[idx] >= 1) { // 1이상이면 이동 가능
						robot[i] = true;
						robot[i - 1] = false;
						belt[idx]--;
					}
				}
			}
			if (belt[index] > 0) { //첫 칸의 내구도가 0보다 크면
				robot[0] = true; // 첫 칸에 로봇 올려두고
				belt[index]--; //해당 벨트 내구도 감소
			}
			int zero = 0;
			for (int i = 0; i < 2 * N; i++) { //zero count
				if (belt[i] == 0)
					zero++;
			}
			if (zero == K)
				break; // 내구도가 0이된 벨트가 K개면 break

		}
		System.out.println(count);

	}
}