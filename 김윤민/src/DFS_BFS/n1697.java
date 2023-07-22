package DFS_BFS;

import java.util.*;

public class n1697 {
	static int[] map;

	public static void bfs(int n, int k) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		if (n == k)
			return;
		while (!q.isEmpty()) {
			int now = q.poll();
			if(now==k) break;
			if (can(now - 1)) {
				map[now - 1] = map[now]+1;
				q.offer(now - 1);
			}
			if (can(now + 1)) {
				map[now + 1] =map[now]+1;
				q.offer(now + 1);
			}
			if (can(now * 2)) {
				map[now * 2] = map[now]+1;
				q.offer(now * 2);
			}
		}
	}

	public static boolean can(int n) {
		if (n <= 100000 && n >= 0&&map[n]==0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] list = new int[2];
		list[0] = sc.nextInt();
		list[1] = sc.nextInt();

		
		map = new int[100001];
		bfs(list[0], list[1]);

		System.out.println(map[list[1]]);

	}
}
