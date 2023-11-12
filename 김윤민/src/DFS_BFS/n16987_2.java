package DFS_BFS;
import java.io.*;
import java.util.*;
//계계치(복습)
public class n16987_2 {
	/* 1. 왼손에 계란
	 * 2. 깨지지 않은 다른 계란 하나를 친다.
	 * 		깨졌거나, 남은 계란이 없으면 안치고 넘어감.
	 *      손에 든 계란 내려놓음
	 * 3. 가장 최근에 든 계란 +1 위치 를 손에 들고 다시 친다.
	 * 맨 끝에가면 종료.
	 * 
	 * 최대 몇개 깨?
	 * 
	 * 
	 */
	static int N, result;
	static int[][] egg;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		egg = new int[N][2]; // 내구도, 무게 
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			egg[i][0] = Integer.parseInt(st.nextToken());
			egg[i][1] = Integer.parseInt(st.nextToken());
		}
		//input end
		
		//logic
		dfs(0, 0);
		System.out.println(result);
	}
	static void dfs(int idx, int breakEgg) {
		result = Math.max(result, breakEgg); //dfs들어가면서 깨진 계란 갱신
		if(idx == N) return; // 위치가 맨 끝이면 순회 X
		if(egg[idx][0] <= 0) dfs(idx+1, breakEgg); // 손에 든 계란이 깨진 경우 다음 계란부터 반복
		else {			
			for(int i=0; i<N; i++) { //계란 골라서 내려쳐
				// 내계란이거나 깨진 계란이면 넘어가기
				if(i == idx || egg[i][0] <= 0) continue;
				//내려칠때 
				// 서로의 내구도 계산
				egg[idx][0]-=egg[i][1];
				egg[i][0]-=egg[idx][1];
				
				int newBreakEgg = breakEgg;
				if(egg[idx][0]<=0) newBreakEgg++;//내계란 깨졌으면 개수 세기
				if(egg[i][0]<=0) newBreakEgg++; //친계란 깨졌으면 개수 세기
				
				dfs(idx+1, newBreakEgg); //반복!
				
				// 복구
				egg[idx][0]+=egg[i][1];
				egg[i][0]+=egg[idx][1];
			}
		}
	}
}
