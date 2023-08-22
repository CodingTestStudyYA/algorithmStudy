package Implementation;

import java.io.*;
import java.util.*;

public class n17281 {
	// 1 이닝 : 공격 + 수비 -> 3아웃 발생시 이닝 종료, 공수바꿈

	// 경기 시작 전까지 타순(치는 순서) 정해야 함 ( 이후에는 못바꿈)
	// 9번타자까지 공쳤는데 3아웃 없으면 --> 1번이 다시 침
	// 타순은 계속 유지
	// 예 : 2이닝에 6이 마지막 --> 3부터는 7이 선다.

	// 공격
	// 루에 선수가 1 2 3루 거쳐서 홈에 도착시 1점 득
	// 루에 머무를 수 있으나, 이닝 시작시는 주자가 없다 ( 초기화)

	// 1번 선수는 4번 타자로 고정.
	// 나머지 타순을 결정. -> 가장 많은 득점을 하는 타순을 찾고 그때의 득점을 구하자
	// 최대 득점 구해라

	static int N, max = Integer.MIN_VALUE;
	static int[][] result; // 이닝, 결과

	static int[] ans;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end

		// 1번 선수는 4번에 고정임.
		visit = new boolean[9];
		visit[0] = true;
		ans = new int[9];
		ans[3] = 0; // 0 번 선수 고정
		
		comb(0);
		System.out.println(max);
	}

	public static void comb(int cnt) {
		if (cnt == 9) {
			// 총 N 이닝 진행
			// 진행 하는 중에 3아웃 -> 인덱스 이어서 다음 이닝
			int out = 0;
			int index = 0;
			int sum = 0;
			/*
			 * 안타: 1 2루타: 2 3루타: 3 홈런: 4 아웃: 0
			 */
//			for(int i=0;i<9;i++) {
//				System.out.print(ans[i]+" ");
//			}
//			System.out.println();
			for (int i = 0; i < N; i++) { // 총 N이닝
				boolean[] ru = new boolean[3]; // 이닝마다 루 초기화
				out=0;
				// ans에 0~8까지 순서대로 해당 인덱스 번호가 적혀있음.
				// 0~8 : 순서 ans[i] = 그 자리.
				while (true) {
					if (out == 3)
						break;
					int now = ans[index]; // 해당 선수가 이번에 침
					index = index+1>8?0:index+1;
					if (result[i][now] == 1) {// 안타
						if (ru[2])
							sum++; // 3루에 있으면 sum++
						ru[2] = ru[1]; // 앞으로 땡기면서
						ru[1] = ru[0];
						ru[0] = true; // 1루는 있어야함 =true
					} else if (result[i][now] == 2) {// 2루타
						if (ru[2])
							sum++; // 3루에 있으면 sum++
						if (ru[1])
							sum++;	// 2루에 있어도 sum++
						ru[2] = ru[0]; 	// 0루에 있던 친구가 2루로 가고
						ru[1] = true; 	// 현재 타석에 있는 친구가 1루에 감.
						ru[0] = false;	// 1루에는 아무도 없음
					} else if (result[i][now] == 3) {// 3루타
						if(ru[2]) sum++; // 루에 있는 애들이 있으면 다 더해주고
						if(ru[1]) sum++;
						if(ru[0]) sum++;
						ru[2]=true; // 지금 타수는 3루에 감
						ru[1]=ru[0]=false; // 1,2루에는 사람이 없다
					} else if (result[i][now] == 4) {// 홈런
						for(int k=0;k<3;k++) { // 루에 있는 사람들 다 점수겟
							if(ru[k]) sum++;
							ru[k]=false; //루 비워준다
						}
						sum++; // 타수도 점수 겟
					} else if (result[i][now] == 0) {// out
						out++;
					}
				}
			}
			max = Math.max(max, sum);
			return;
		}
		if (cnt == 3) //4번타수는 정해져 있음
			cnt++;
		for (int i = 0; i < 9; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			ans[cnt] = i;
			comb(cnt + 1);
			visit[i] = false;
		}
	}
}