package DP;

import java.io.*;
import java.util.*;

public class n2096_2 {
	static int N;
	static int[][] maxDp;
	static int[][] minDp;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxDp = new int[N][3];
		minDp = new int[N][3];
		map = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 초기화
		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = map[0][i];
			minDp[0][i] = map[0][i];
		}
		
		//아래에서 위를 바라봤을때로 생각
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				switch (j) {
				case 0: // 0,1
					maxDp[i][j] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + map[i][j];
					minDp[i][j] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + map[i][j];
					break;
				case 1: //0 1 2
					maxDp[i][j] = Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + map[i][j];
					minDp[i][j] = Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + map[i][j];
					break;
				case 2://1 2
					maxDp[i][j] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + map[i][j];
					minDp[i][j] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + map[i][j];
					break;
				}
			}
		}
		int min=Integer.MAX_VALUE;
		int max =0;
		for(int i=0;i<3;i++) {
			min = Math.min(minDp[N-1][i], min);
			max = Math.max(maxDp[N-1][i], max);
		}
		System.out.println(max+" "+min);
	}

}
