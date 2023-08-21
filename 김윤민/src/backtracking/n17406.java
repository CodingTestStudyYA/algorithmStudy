package backtracking;

import java.io.*;
import java.util.*;

public class n17406 {
	// 배열 A : 각 행 모든수의 합 중 최솟값

	// 회전연산 -> r,c,s
	static int N, M, K, min = Integer.MAX_VALUE;
	static int stX, stY, edX, edY;
	static int[][] original,map;
	static int index;
	static int[] dy = { 1, 0, -1, 0 }; // 아래 오른 위 왼
	static int[] dx = { 0, 1, 0, -1 };
	static cmd[] cmdList;
	static int[] result; //index저장
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		original = new int[N+1][M+1]; //오리지널
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cmdList = new cmd[K]; // 명령 저장
		result = new int[K];// 순서 저장
		visit = new boolean[K];// 왔는지 안왔는지
		// logic
		// 개빡친다 문제 잘읽을걸
		// 회전 cmd는 임의로 순서를 바꿔도 됨. ㅋㅋㅋ;ㅁㄴㅇ리ㅏㅓㅁ나ㅣㅇ러ㅣ ==> 조합사용
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cmdList[i] = new cmd(r, c, s);
			// input end

		}
		// logic
		comb(0);
		sb.append(min);
		System.out.println(sb);
		// 시작위치의 아래 map을 저장해놔야 함.
		// 시작위치 => visit =true

	}

	public static void comb(int cnt) {
		if (cnt == K) {
			map =Arrays.stream(original).map(int[]::clone).toArray(int[][]::new); //카피
			
			for (int pos = 0; pos < K; pos++) { //명령어만큼 반복
				cmd nCmd = cmdList[result[pos]]; //지금 명령어 꺼냄
				// 기준이 r,c임. => 정사각형이므로
				// 따라서 회전은 s만큼만 하면 됨.
				for (int S = 1; S <= nCmd.s; S++) {
					// 시작 X, Y | 종료 X, Y 지정
					stX = nCmd.c - S;
					stY = nCmd.r - S;
					edX = nCmd.c + S;
					edY = nCmd.r + S;
					// 초기화
					index = 0;
					int tmp = map[stY][stX]; // 현재 위치값이 없어지므로 이것만 기억
					dfs(stY, stX);
					map[stY][stX + 1] = tmp; // 다음 위치에 저장해준다.
				}
			}
			for (int i = 1; i <= N; i++) { //min 갱신
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += map[i][j];
				}
				min = Math.min(min, sum);
			}
			return;
		}
		for (int i = 0; i < K; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			result[cnt] = i;
			comb(cnt + 1);
			visit[i] = false;
		}
	}

	public static void dfs(int y, int x) {
		// 왔으면 true,
		if (index == 4) // index = 4==> 다 바꿨다.
			return;
		int ny = y + dy[index];
		int nx = x + dx[index];
		if (can(ny, nx)) { // 해당 범위 내면
			map[y][x] = map[ny][nx]; // 이동 될 값을 덮어써준다.
			dfs(ny, nx); // dfs진행하면서 덮음
		} else { // 범위를 벗어난 경우 ==> index 이동
			index++;
			// 인덱스 다시 지정해준 뒤 dfs돌림
			dfs(y, x);
			return;
		}
	}

	public static boolean can(int y, int x) {
		if (y < stY || x < stX || y > edY || x > edX)
			return false;
		return true;
	}

	static class cmd {
		int r, c, s;

		cmd(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

}
