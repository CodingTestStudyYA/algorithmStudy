package DFS_BFS;

import java.io.*;
import java.util.*;

public class n5014 {
	static int[] map;
	static boolean[] ch;
	static int F;

	public static void bfs(int s, int g, int u, int d) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		ch[s]=true;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (now == g)
				return;
			if (can(now + u)&&ch[now+u]==false) {
				ch[now+u]=true;
				map[now + u] = map[now] + 1;
				q.offer(now + u);
			}
			if (can(now - d)&&ch[now-d]==false) {
				ch[now-d]=true;
				map[now - d] = map[now] + 1;
				q.offer(now - d);
			}
		}
	}

	public static boolean can(int n) {
		if (n >= 1 && n <= F && map[n] == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		map = new int[F + 1];
		ch = new boolean[F+1];
		bfs(S, G, U, D);
		
		if(S==G) {
			System.out.println("0");
		}else if (map[G] == 0)
			System.out.println("use the stairs");
		else {
			System.out.println(map[G]);
		}

	}

}
