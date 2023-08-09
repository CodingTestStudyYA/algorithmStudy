package DP;

import java.io.*;
import java.util.*;
//내려가기
public class n2096 {

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end
		
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
		int[][] dp_max = new int[n][3];
		int[][] dp_min = new int[n][3];

		// N줄 0~9 숫자 세개씩.
		// 다음 줄로 내려갈 때 바로아래 or 바로아래의 수와 붙어있는 수로만 이동

		// default
		for (int i = 0; i < 3; i++) {
			dp_max[0][i] = map[0][i]; // 첫 줄은 각자 자신 값으로 초기화.
			dp_min[0][i] = map[0][i];
		}
		
		// 밑에서 위를 봤을때,
		// 왼쪽이면 내 위, 내 위의 오른쪽
		// 중앙이면 세개 다.
		// 오른쪽이면 내위, 내위의 왼쪽에서 접근 가능
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==0) {
					dp_max[i][0]=Math.max(dp_max[i-1][0], dp_max[i-1][1])+map[i][j];
					dp_min[i][0]=Math.min(dp_min[i-1][0], dp_min[i-1][1])+map[i][j];
				}else if(j==1) {
					dp_max[i][1]=Math.max(dp_max[i-1][2], Math.max(dp_max[i-1][0], dp_max[i-1][1]))+map[i][j];
					dp_min[i][1]=Math.min(dp_min[i-1][2], Math.min(dp_min[i-1][0], dp_min[i-1][1]))+map[i][j];
				}else {
					dp_max[i][2]=Math.max(dp_max[i-1][2], dp_max[i-1][1])+map[i][j];
					dp_min[i][2]=Math.min(dp_min[i-1][2], dp_min[i-1][1])+map[i][j];
				}
			}
			
		}
		// 얻을 수 있는 최대, 최소점수
		for(int i=0;i<3;i++) {
			min = Math.min(min, dp_min[n-1][i]);
			max = Math.max(max, dp_max[n-1][i]);
		}
		System.out.println(max+" "+min);
	}

}
