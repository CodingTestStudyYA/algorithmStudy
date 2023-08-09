package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static int N;
	static int[][] matrix; 
	
	static int[][] dp_max; // i,j까지 선택했을때 최대값  -> 마지막 줄 칸들에는 경우의 최대값들 모임 
	static int[][] dp_min; // i,j까지 선택했을때 최소값 -> 마지막 줄 칸들에는 경우의 최소값들 모임 
	
	static int max = Integer.MIN_VALUE; // 점수 최대값  
	static int min = Integer.MAX_VALUE; // 점수 최소값 

	public static void main(String[] args) throws IOException {
		// 내려가기 게임 첫줄 -> 마지막 줄
		// 바로 아래칸으로 가거나 아래칸의 주위칸으로 감 -> 아닌칸들은 갈 수 없음 (대각선 한칸 차이만 가능)
		// 갈수 있는 곳은 총 아래 기준 세방향인데 이때 범위를 넘어가면 x

		// 가게 되면 점수를 얻음. 최소, 최대 점수 구하기
		// N 줄 -> 총 N번 갈 수 있음
		
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][3];
		dp_max = new int[N][3];
		dp_min = new int[N][3];
		
		for(int i = 0; i <N; i++) {
			for(int j = 0; j<3; j++) {
				dp_min[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i <N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(i == 0) {
					dp_max[0][j] = matrix[0][j];
					dp_min[0][j] = matrix[0][j];
				}
			}
		}
		
		// matrix 첫째줄은 해당 점수가 최고 점수임 (이전 없음)
		// 다음줄로 넘어가면서 경우의 수에 따라 최대 점수를 구하기 
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<3; j++) {
				// 해당칸에 도달 
				for(int s = 0; s < 3; s++) {
					// 갈 수 있는 다음칸으로 이동하면서 
					int ny = i + dy[s]; 
					int nx = j + dx[s]; 
					if(ny >= 0 && ny < N && nx >= 0 && nx <3) {
						// 현재의 나 + 다음칸의 점수 -> 다음칸까지의 총 점 
						// 이때 최대를 구해야하므로 Math.max
						dp_max[ny][nx] = Math.max(dp_max[ny][nx], dp_max[i][j] + matrix[ny][nx]); 
						dp_min[ny][nx] = Math.min(dp_min[ny][nx], dp_min[i][j] + matrix[ny][nx]); 
						// 원래 넣었던 값 혹은 이전값에 더한 값 -> 윗줄에서 해당 칸으로 오는 경우 최대로 나오는 점수를 저장함 
					}
				}
				if(i == N-1) {
					min = Math.min(min, dp_min[i][j]); 
					max = Math.max(max, dp_max[i][j]);
				}
			}
		}
		
		System.out.println(max + " " + min);

	}

}
