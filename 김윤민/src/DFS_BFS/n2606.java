package DFS_BFS;

import java.util.*;
import java.io.*;

public class n2606 {
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] ch;
	public static int bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(i);
		ch[i]=true;
		int cnt=0;
		while(!q.isEmpty()) {
			ArrayList<Integer> now_list = list.get(q.poll());
			for(int k:now_list) {
				if(ch[k]==false) {
					cnt++;
					ch[k]=true;
					q.offer(k);
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		ch = new boolean[n+1];
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
		System.out.println(bfs(1));
	}

}
