package Implementation;

import java.io.*;
import java.util.*;

// 컨베이어 벨트 위의 로봇
public class n20055_2 {

	/*
	 * 내리는 위치에 도달하면 그 즉시 내린다. 올리거나 이동하면 그즉시 내구도 1감소 1. 벨트 회전 2. 가장 먼저 올라간 로봇부터,
	 * 오른쪽으로 이동가능하면 이동 => 다음 칸에 로봇이 없고 내구도 1이상. 3. 올리는 위치에 내구도가 1이상이면 올린다. 4. 0인 칸
	 * 개수가 k개 이상이라면 과정 종료. => 종료시 몇단계가 진행중이었는지?
	 * 
	 */
	static int N, K;
	static int[] D;
	static boolean[] robot;

	public static void main(String[] args) throws Exception {
		// input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D =new int[N*2];
		robot = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N*2;i++) {
			D[i]= Integer.parseInt(st.nextToken());
		}
		int round = 0;
		
		int top = 0;
		
		while(true) {
			round++;
			//1. 벨트 회전
			top = getPos(top,-1);
			for(int i=N-1;i>=1;i--) {
				robot[i]=robot[i-1];
			}
			robot[0]=false; //처음과 끝에는 없는게 정상
			robot[N-1]=false;
			//2. 가장 먼저 올라간 로봇부터 오른쪽으로 이동 가능하면 이동.
			for(int i=N-1;i>=1;i--) { //가장 먼저 올라간 로봇 : 가장 오른쪽에 있는 로봇
				//N-1은 어짜피 로봇이 없으므로 N-2부터 턴
				if(!robot[i]&&robot[i-1]&&D[getPos(top,i)]>=1) {
					int tmpPos = getPos(top,i);
					robot[i-1]=false;
					if(i!=N-1) {
						robot[i]=true;
					}
					D[tmpPos]--;
				}
			}
			//3. 처음에 로봇 올리기
			if(D[top]>=1) {
				robot[0]=true;
				D[top]--;
			}
			//4. 0이 K개 이상이면 break;
			int zero =0;
			for(int i=0;i<N*2;i++) {
				if(D[i]==0) {
					zero++;
				}
				if(zero>=K) break;
			}
			
			if(zero>=K) break;
			
		}
		System.out.println(round);
		
	}

	static int getPos(int top, int add) {
		if (top + add < 0) {
			return (top + (2 * N)) + add;
		} else if (top + add >= 2 * N) {
			return (top + add) % (2 * N);
		}
		return top + add;
	}
}
