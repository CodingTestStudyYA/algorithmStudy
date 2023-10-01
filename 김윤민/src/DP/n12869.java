package DP;

import java.io.*;
import java.util.*;

/*
 * 뮤탈리스크
 */
public class n12869 {
	/*
	 * SCV는 남아있는 체력존재, 뮤탈리스크를 공격 X
	 * 
	 * 뮤탈리스크가 공격을 할 때, 한 번에 세 개의 SCV를 공격할 수 있다. 9 / 3 / 1
	 * 
	 * 모든 SCV를 파괴하기 위해 공격해야 하는 횟수의 최솟값
	 */
	static int N, min=Integer.MAX_VALUE;
	/*
	 * 체력을 큰거부터 9-3-1로 깎아야하므로 무조건 내림차순으로 깎는다. => 최소값으로 도달 가능
	 * 반대로 1-3-9로 하면 최대값이 저장됨 -> 왜냐면 한번 온거는 true로 치고 다시는 계산하지 않기 때문에!
	 */
	static int[][] noc = { { 9, 3, 1 },{ 9, 1, 3 },{ 3, 9, 1 },{ 3, 1, 9 },{ 1, 9, 3 },{ 1, 3, 9 }};
	static int[] arr;
	/*
	 * dp 체력이 60보다 작거나 같은 자연수 -> 60개 배열 ( 3개 ? ) 1을 잃는게 최솟값 -> 60번 반복 = 61개의 배열 선정 
	 * ------------------------------------------------ 
	 * => dp + dfs
	 */
	static boolean[][][] visit;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[3];//3고정
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visit = new boolean[61][61][61];
		if (N == 1) {
			min = arr[0] / 9;
			if (arr[0] % 9 != 0) {
				min++;
			}
		}else {
			dfs(new Integer[] {arr[0],arr[1],arr[2]},0);
		}
		System.out.println(min);

	}

	// dfs + dp
	static void dfs(Integer[] array, int cnt) {
		/*
		 * 최소 횟수를 따지는 문제이기 때문에, 남은 피가 {1,3,9} 일 때랑, {9,3,1}일떄랑 같은 경우임 -> 분리해서 계산해줄 필요가 없다.
		 * 따라서 정렬을 하고 dfs계속 진행
		 */

		Arrays.sort(array, Collections.reverseOrder());
		int scv1 = array[0];
		int scv2 = array[1];
		int scv3 = array[2];
		if (scv1 == 0 && scv2 == 0 && scv3 == 0) { // 모두 0이면 계산할 필요가 없음 = return
			min = Math.min(cnt, min);
			return;
		}

		if (visit[scv1][scv2][scv3]) { //이미 계산을 한 경우면 return
			return;
		}
		visit[scv1][scv2][scv3] = true; 
		
		if (min < cnt) //min보다 크면 계산해줄 필요 없음 -> return
			return;
		//min보다 작으면 경우의 수 dfs
		for (int i = 0; i < 6; i++) {
			int a = minus(scv1, noc[i][0]);
			int b = minus(scv2, noc[i][1]);
			int c = minus(scv3, noc[i][2]);
			dfs(new Integer[] { a, b, c }, cnt + 1);
		}

	}

	

	static int minus(int x, int y) {
		return (x - y) < 0 ? 0 : x - y;
	}

	
}
