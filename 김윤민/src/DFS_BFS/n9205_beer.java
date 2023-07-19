package DFS_BFS;
import java.io.*;
import java.util.*;
public class n9205_beer {
	static Point[] list;
	static int n;
	static boolean flag;
	static boolean[] visited;
	public static void dfs(int nowx,int nowy) {
		if(flag) return;
		if((Math.abs(nowx-list[n+1].x)+Math.abs(nowy-list[n+1].y))<=1000){ //n+1:페스티벌 
			flag =true;
			return;
		}
		for(int i=1;i<n+1;i++) {
			if(visited[i])continue;
			if((Math.abs(nowx-list[i].x)+Math.abs(nowy-list[i].y))<=1000){
				visited[i]=true;
				dfs(list[i].x,list[i].y);
			}
		}	
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		for(int i=0;i<cnt;i++) {
			flag = false;
			n = Integer.parseInt(br.readLine());
			visited = new boolean[n+2];
			//상근이 , 편의점, 페스티벌
			StringTokenizer st;
			list = new Point[n+2];
			//0 : 상근이 집
			for(int j=0;j<n+2;j++) {
				st =new StringTokenizer(br.readLine());
				list[j] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			//logic
			dfs(list[0].x,list[0].y);
			
			if(flag) System.out.println("happy");
			else System.out.println("sad");
		}

	}
	

}
	

