package DFS_BFS;

import java.io.*;
import java.util.*;

public class n1389 {
	public static int n, m;
	public static ArrayList<ArrayList<Integer>> list;
	public static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// 최대 6단계 이내 서로 아는 사람 연결 가능
		// 임의의 두 사람이 최소 몇단계 만에 이어지나?

		// 모든 사람과 케빈 베이컨 게임을 했을때 나오는 단계의 합.
		// 가장 작은 사람을 구하는 프로그램. (복수 가능)

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.get(a).add(b);
			list.get(b).add(a);
		}
		// input end
		
		//logic
		int min=Integer.MAX_VALUE;
		int pos = 0;
		for(int i=1;i<=n;i++) {
			if(min>bfs(i)) { //같을 땐 자동으로 작은게 출력.
				min = bfs(i);
				pos = i; 
			}
		}
		System.out.println(pos);
	}

	public static int bfs(int tgt) { // 최소경로.
		Deque<posCnt> q = new ArrayDeque<>();
		visited = new boolean[n + 1]; //초기화
		q.offer(new posCnt(tgt, 0));
		int min = 0; 
		while (!q.isEmpty()) {
			posCnt now = q.poll();
			if (visited[now.pos] == true) //왔으면 건너뜀
				continue;
			visited[now.pos] = true; //true로 변경
			min = min + now.cnt; //왔을때는 무조건 최소경로이므로 더해줌.
			for (int next : list.get(now.pos)) { //ArrayList iterate.
				if (visited[next] == false) { //거기 안갔으면 q에 추가.
					q.offer(new posCnt(next, now.cnt + 1));
				}
			}
		}

		return min;
	}

	static class posCnt {
		int pos, cnt;

		posCnt(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}
}