package swea;

import java.io.*;
import java.util.*;

//죵나..어렵내..

public class n5653 {
	// 방향
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	// 0 쥬금, 1 : 활성, 2 : 비활성
	static final int DEAD=0, ACTIVE=1, INACTIVE=2;
	static int t,n,m,K;
	
	static List<Cell> cell;
	static PriorityQueue<Cell> pq;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st;
		
		t=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=t; tc++) {
			st=new StringTokenizer (br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			cell=new ArrayList<>();
			//생명력 내림차순으로 입력되도록 pq 생성
			pq=new PriorityQueue<>( (p1, p2) -> p2.power-p1.power );
			visited=new boolean[n+2*K][m+2*K];
			
			
			for (int i=0; i<n; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<m; j++) {
					int n=Integer.parseInt(st.nextToken());
				
					if (n!=0) { //세포면
						cell.add(new Cell(i+K,j+K,n,n)); //배열이라고 생각하고 위치랑 나머지 등등 저장
						visited[i+K][j+K]=true;
					}

				}
			}
			simulation();
			System.out.println("#"+tc+" "+count());
				
		}
	}
	
	static void simulation () {
		for (int k=1; k<=K; k++) { //k만큼 시뮬 진행.
			
			// 직전에 INACTIVE -> ACTIVE 상태로 변경된 cell들 
			while (!pq.isEmpty()) {
				Cell c=pq.poll();
				int y=c.y;
				int x=c.x;
				
				if (!visited[y][x]) {
					visited[y][x]=true;
					cell.add(c);
				}
			}
			
			for (int i=0; i<cell.size(); i++) {
				if (cell.get(i).state==DEAD) continue; //셀 이미 죽었으면 탐색x
				//비 활성상태인데 시간이 k면 활성상태로 바꿔줘야함.
				else if (cell.get(i).state==INACTIVE && cell.get(i).time==k) {
					cell.get(i).state=ACTIVE;				// X시간동안 활성상태
					cell.get(i).time=k+cell.get(i).power;	// 현재 시간 보다 X 시간이 지난 후에 죽는 상태로 만들어줄 것
					
					//활성인상태에서 번식.
					for (int d=0; d<4; d++) {
						// k+1+power 후에 변경상태가 될 것
						int ny=cell.get(i).y+dy[d];
						int nx=cell.get(i).x+dx[d];
						
						pq.add(new Cell(ny,nx, k+1+cell.get(i).power, cell.get(i).power));
					}
					
				} else if (cell.get(i).state==ACTIVE && cell.get(i).time==k) {
					//이미 활성상태인데 시간이 다됐으면 죽어야함.
					cell.get(i).state=DEAD;
					cell.get(i).time=0;
					cell.get(i).power=0;
				}
				
			}
		}
	}
	
	static int count () { //죽은 세포 제외하고 count
		int cnt=0;
		for (int i=0; i<cell.size(); i++) {
			if (cell.get(i).state==ACTIVE || cell.get(i).state==INACTIVE)
				cnt++;
		}
		return cnt;
	}
	
	
	static class Cell {
		int y,x,time,state,power;
		
		Cell (int y, int x, int time, int power) {
			this.y=y;
			this.x=x;
			this.time=time;
			this.power=power;
			this.state=INACTIVE;
		}		
	}
}
// 복습할 때 봐도 모르겠다면 아래 블로그 참조..
//https://velog.io/@kimmy/SWEA-5653-%EC%A4%84%EA%B8%B0%EC%84%B8%ED%8F%AC%EB%B0%B0%EC%96%91-java
//다시보자...