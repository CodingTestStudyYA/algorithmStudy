package Implementation;
import java.io.*;
import java.util.*;

public class n17135 {
	static int N, M, D;
	static int n,max;
	static int[] bow;
	static boolean[] visit;
	static ArrayList<Enemy> eList;
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		eList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if(st.nextToken().equals("1")) { //적만 add
					eList.add(new Enemy(i,j));
				}
			}
		}
		
		visit = new boolean[M];//comb를 위한 visit
		bow = new int[3]; // 궁수 배치
		comb(0); //순열
		System.out.println(max); // max출력
	}

	static void comb(int cnt) { //순열
		if (cnt == 3) {
			// bow에 인덱스가 담겨 있음
			simulation(bow); 
			return;
		}
		for (int i = 0; i < M; i++) {
			if (visit[i])
				continue;
			bow[cnt] = i;
			visit[i] = true;
			comb(cnt + 1);
			visit[i] = false;
		}
	}

	static void simulation(int[] bowList) {
		PriorityQueue<Enemy> q = new PriorityQueue<>(); //거리가 되는 애들만 offer
		ArrayList<Enemy> enemy = new ArrayList<>(); // 해당 순열에서만 사용하는 적들의 리스트
		for(Enemy e : eList) {
			enemy.add(new Enemy(e.y,e.x)); //객체 공유 안해야해서 새로운 객체 생성 후 add - enemy.add(e)하면 객체를 공유해서,
			//이후 연산에 영향 O, -> 꼭 new Enemy 해야함.
		}
		int cnt=0;//죽은사람 수 셈
		while(true) {
			//round 마다 앞으로 땡겨가자
			for(int i=0;i<3;i++) {
				//죽은 사람 clear
				q.clear();
				int bx =  bow[i];// 궁수의 x위치
				for(int j=0;j<enemy.size();j++) {	
					Enemy e = enemy.get(j);// 남은 적들을 순회하면서
					e.d = Math.abs(N-e.y)+Math.abs(bx-e.x); // 거리 구해줌
					if(e.d<=D) { //거리가 D 이하이면 죽일 수 있다.
						q.offer(e); // 큐에 offer
					}
				}
				if(!q.isEmpty()) {
					q.poll().live = false; //가장 우선순위가 먼저 ( 가깝고 왼쪽 )을 죽임 live=false;
					//객체이므로 poll을 하고 안의 live를 변경해도 값이 유지가 됨.
				}
			}
			for(int i=enemy.size()-1;i>=0;i--) {
				Enemy now = enemy.get(i); //남은 적들을 순회하면서
				if(now.live==false) { // 죽은애들은
					enemy.remove(i); // 지워주고 
					cnt++;	// count++
				}else if(now.y==N-1) { // N-1까지 왔으면 다음은 벽 => remove
					enemy.remove(i); // 벽에 부딪혀 죽음
				}else {
					now.y++; //아니면 한칸 전진
				}
			}
			if(enemy.size()==0) break; //size=0 : 게임 끝남
		}
		max = Math.max(max, cnt);
	}

	static class Enemy implements Comparable<Enemy>{
		int y, x,d;
		boolean live;

		Enemy(int y, int x) {
			this.y = y;
			this.x = x;
			this.live = true;
		}
		@Override
		public int compareTo(Enemy o) {
			if(this.d==o.d) {
				return this.x-o.x;
			}
			return this.d-o.d;
		}
		
	}

}
