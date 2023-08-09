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
	
	static int[][] dp_max; // i,j���� ���������� �ִ밪  -> ������ �� ĭ�鿡�� ����� �ִ밪�� ���� 
	static int[][] dp_min; // i,j���� ���������� �ּҰ� -> ������ �� ĭ�鿡�� ����� �ּҰ��� ���� 
	
	static int max = Integer.MIN_VALUE; // ���� �ִ밪  
	static int min = Integer.MAX_VALUE; // ���� �ּҰ� 

	public static void main(String[] args) throws IOException {
		// �������� ���� ù�� -> ������ ��
		// �ٷ� �Ʒ�ĭ���� ���ų� �Ʒ�ĭ�� ����ĭ���� �� -> �ƴ�ĭ���� �� �� ���� (�밢�� ��ĭ ���̸� ����)
		// ���� �ִ� ���� �� �Ʒ� ���� �������ε� �̶� ������ �Ѿ�� x

		// ���� �Ǹ� ������ ����. �ּ�, �ִ� ���� ���ϱ�
		// N �� -> �� N�� �� �� ����
		
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
		
		// matrix ù°���� �ش� ������ �ְ� ������ (���� ����)
		// �����ٷ� �Ѿ�鼭 ����� ���� ���� �ִ� ������ ���ϱ� 
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<3; j++) {
				// �ش�ĭ�� ���� 
				for(int s = 0; s < 3; s++) {
					// �� �� �ִ� ����ĭ���� �̵��ϸ鼭 
					int ny = i + dy[s]; 
					int nx = j + dx[s]; 
					if(ny >= 0 && ny < N && nx >= 0 && nx <3) {
						// ������ �� + ����ĭ�� ���� -> ����ĭ������ �� �� 
						// �̶� �ִ븦 ���ؾ��ϹǷ� Math.max
						dp_max[ny][nx] = Math.max(dp_max[ny][nx], dp_max[i][j] + matrix[ny][nx]); 
						dp_min[ny][nx] = Math.min(dp_min[ny][nx], dp_min[i][j] + matrix[ny][nx]); 
						// ���� �־��� �� Ȥ�� �������� ���� �� -> ���ٿ��� �ش� ĭ���� ���� ��� �ִ�� ������ ������ ������ 
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
