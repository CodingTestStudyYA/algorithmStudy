package DFS_BFS;
import java.util.*;
import java.io.*;
public class n14503_robot {
	static int n,m,cnt=1;//본인 자리 count 1
	static int[][] board;
	static int[] dr = {-1,0,1,0};//북,동,남,서
	static int[] dc = {0,1,0,-1};
	public static void DFS(int r,int c,int d) {//좌표, 방향
		//청소 될때 (d를 기준으로 iterator 해야함.)
		int td=d;
		for(int i=0;i<4;i++) {
			td = position(td);
			int tr = r+dr[td];
			int tc = c+dc[td];
			if(tr>=1&&tc>=1&&tr<n-1&&tc<m-1&&board[tr][tc]==0) {
				board[tr][tc]=-1;//청소 함
				cnt++;
				DFS(tr,tc,td);
				return;
			}
			
		}
		//청소 할 곳이 없을때
		if(td==d) {
			td= d>1? d-2:d+2; //후진 direct 조정
			int tr = r+dr[td];
			int tc = c+dc[td];
			if(tr>=1&&tc>=1&&tr<n-1&&tc<m-1&&board[tr][tc]!=1){//벽이 아니면 후진
				DFS(tr,tc,d);
			}	
		}
		//뒤가 벽이면 return
		return;
		
	}
	public static int position(int d) {
		if(d-1<0) return 3;
		else return d-1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		//좌표, 방향(0: 북, 1:동, 2:남, 3:서) (r,c), d
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		board[r][c]=-1;
		DFS(r,c,d);
		System.out.println(cnt);
	}

}