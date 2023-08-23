package DFS_BFS;

import java.io.*;
import java.util.*;

public class n17471 {
	static ArrayList<Integer> aList; //a 선거 구역
	static ArrayList<Integer> bList; //b 선거 구역

	static ArrayList<ArrayList<Integer>> graph;
	static int N, min, all;
	static int[] person;

	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1-N 구역, - 2개의 선거구 중 하나에 포함
		// 한 선거구에 포함되어있는 구역은 모두 연결되어있어야 함.
		// 두 선거구 인구 차이 최소
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		person = new int[N];// 인구 수 저장
		for (int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			all += person[i]; //전체 인구 저장
		}
		// 인접 정점들 추가
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>()); 
			for (int j = 0; j < M; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		// input end
		
		aList = new ArrayList<>();
		bList = new ArrayList<>();
		min = Integer.MAX_VALUE;
		comb(0);
		if (min == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(min);
	}
	//dfs로 탐색
	public static void dfs(ArrayList<Integer> list, int idx) {
		visit[idx] = true;
		int size = graph.get(idx).size();
		for (int i = 0; i < size; i++) {
			int now = graph.get(idx).get(i) - 1;
			if (visit[now])
				continue;
			if (list.contains(now)) {
				dfs(list, now);
			}
		}
	}

	public static void comb(int idx) {
		if (idx == N) { // 모두 선택됐으면
			// 두 구역 모두 다 최소 하나씩은 있어야 함.
			if (aList.size() == 0 || aList.size() == N)
				return;
			// 모두 인접해있는지 확인
			visit = new boolean[N];
			dfs(aList, aList.get(0));
			for (int i = 0; i < aList.size(); i++) {
				if (visit[aList.get(i)] == false)
					return;
			}	
			dfs(bList, bList.get(0));
			for (int i = 0; i < bList.size(); i++) {
				if (visit[bList.get(i)] == false)
					return;
			}
			//인접 확인 끝
			// 인접해있는지 확인됨
			int one = 0, two = 0;
			for (int i = 0; i < aList.size(); i++) {  //a구역 인원수 체크
				one = one + person[aList.get(i)];
			}
			two = all - one; //b구역 인원수 확인
			min = Math.min(min, Math.abs(one - two)); // 작은거 저장
			return;
		}
		aList.add(idx);
		comb(idx + 1);
		bList.add(idx);
		aList.remove((Integer) idx);
		comb(idx + 1);
		bList.remove((Integer) idx);
	}

}
