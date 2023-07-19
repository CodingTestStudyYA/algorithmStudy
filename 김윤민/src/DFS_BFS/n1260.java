package DFS_BFS;
import java.util.*;
import java.io.*;
public class n1260 {
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;
	static StringBuilder sb;
	static void dfs(int v) {
		if(visited[v]==true) { 
			return;
		}else {
			visited[v]=true;
			sb.append(v+" ");
			for(int i=0;i<list.get(v).size();i++) {
				dfs(list.get(v).get(i));
			}
		}
	}
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		while(!q.isEmpty()) {
			int n = q.poll();
			if(visited[n]) continue;
			sb.append(n+" ");
			visited[n]=true;
			for(int i=0;i<list.get(n).size();i++) {
				q.add(list.get(n).get(i));
			}
			
		}
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n+1];
		list = new ArrayList<>();
		for(int i=0;i<n+1;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		for(int i=0;i<=n;i++) {
			Collections.sort(list.get(i));
		}
		sb = new StringBuilder();
		dfs(v);
		System.out.println(sb.toString());
		sb = new StringBuilder();
		visited = new boolean[n+1];
		bfs(v);
		System.out.println(sb.toString());
	}

}
