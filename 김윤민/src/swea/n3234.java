package swea;

import java.io.*;
import java.util.*;

public class n3234 {
	static StringBuilder sb = new StringBuilder();
	static int n, cnt;
	static int[] list, tmp,leftArr,rightArr;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		// N개의 서로 다른 무게를 가진 무게 추와 양팔저울
		// 양팔 저울에 갑자기 문제가 생겨서 무게 추를 올릴 때
		// 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다.

		// 왼쪽의 총합 >= 오른쪽의 총합

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int C = 1; C <= T; C++) {
			n = Integer.parseInt(br.readLine()); // 무게 추 개수
			list = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			// input end

			// 순서 중요.
			// 순서 n!경우에서 dfs 돌리면 될듯?
			// 순서를 어케 하지.,.,.,.
			tmp = new int[n];
			cnt=0;
			visit = new boolean[n];
//			//---------
//			leftArr = new int[n];
//			rightArr = new int[n];
//			makeSeq(0,0,0);
			//--------
			dfs2(0, 0);
			sb.append("#").append(C).append(" ").append(cnt).append('\n');
		}
		System.out.println(sb);
	}
	//순열은 인자 하나 -> 해결 가능
	//조합은 인자 두개 -> 두개 필요
	public static void dfs2(int idx, int count) {
		if (count == n) {
			dfs(1, tmp[0], 0);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				tmp[count] = list[i];
				dfs2(idx + 1, count + 1);
				visit[i] = false;
			}

		}
	}

	public static void dfs(int idx, int left, int right) {
		if (left < right)
			return;
		// 올릴 때 순서대로.
		// 1, 2, 4 -> 왼 1 오 2 인상태이면 X ->모두 return
		// 첫 요소는 무조건 왼쪽 고정.
		// 두번째 idx 부터 dfs진행.
		if (idx == n) {
			cnt++;
			return;
		}
		// 나까지 오른쪽에 간 상태에서 왼쪽이 더 크다면,
		// 다음 idx dfs진행.
		// 아니면 걍 return
		// 4 1 2 
		// left = 4 
		// now = 1 
		int tmpR = right + tmp[idx];
		if (left >= tmpR) { // 나를 더해도 왼쪽이 크다면 오른쪽에 추를 던지고 DFS 진행
			dfs(idx + 1, left, tmpR);
		} // 오른쪽이 더크면 안되니까 왼쪽에 추를 던지고 DFS 진행.
		dfs(idx + 1, left + tmp[idx], right);

	}
	static void makeSeq(int idx, int left, int right){ // idx 현재 추, right 지금까지 right무게, left 지금까지 left의 무게
		
        // 가지치기
        if(right > left) return;

        // 최종 결과
        if(idx == n){ // 최종적으로 전부 배치했을때
            cnt ++;
            return ;
        }

        // 순열 만들기 .... => 왜 순열이 안되나...
        for(int i = 0; i < n; i++){
            if(visit[i]) continue; // 중복 제거
            visit[i] = true;
            makeSeq(idx+1, left+list[i], right); // 왼쪽에 두기
            if(left>=right+list[i]) {
            	makeSeq(idx+1, left, right + list[i]); // 오른쪽에 두기
            }
            visit[i] = false;
        }
    }
}
