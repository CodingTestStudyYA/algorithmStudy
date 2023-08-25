package swea;

import java.io.*;
import java.util.*;

public class n5644 {
	static int[] dy = { 0, -1, 0, 1, 0 }; // 이동 x , 상, 우, 하, 좌
	static int[] dx = { 0, 0, 1, 0, -1 };
	
	static int[] pathA, pathB, playerA, playerB;
	static StringBuilder sb = new StringBuilder();
	static int M, A; // 이동시간, BC의 개수
	static BC[] bcList;
	static int sum;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int C = 1; C <= T; C++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수

			pathA = new int[M+1]; //본인 자리를 포함한 M개의 경로
			pathB = new int[M+1]; 
			playerA = new int[2];
			playerB = new int[2];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				pathA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				pathB[i] = Integer.parseInt(st.nextToken());
			}

			bcList = new BC[A];
			for (int i = 0; i < A; i++) {
				// x y c p
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcList[i] = new BC(x, y, c, p);
			}
			// input end

			// 초기화
			sum=0;
			//logic 시작
			playerA[0]=playerA[1]=1;//x,y좌표
			playerB[0]=playerB[1]=10;
			
			
			move();
			
			sb.append("#").append(C).append(" ").append(sum).append('\n');

		}
		System.out.println(sb);
	}
	static void move() {
		for(int t=0;t<M+1;t++) {
			playerA[0]+=dx[pathA[t]]; //처음엔 자기 자리
			playerA[1]+=dy[pathA[t]];
			playerB[0]+=dx[pathB[t]];
			playerB[1]+=dy[pathB[t]];
			
			sum+=getCharge();
		}
	}
	static int can(int idx,int y,int x) { //거리가 되면 power 리턴
		return Math.abs(bcList[idx].y-y)+Math.abs(bcList[idx].x-x)<=bcList[idx].c? bcList[idx].p:0;
	}
	static int getCharge() { //해당 위치에서 충전값 계산
		int max=0;
		int size = bcList.length;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				int all = 0;
				int A = can(i,playerA[1],playerA[0]);
				int B = can(j,playerB[1],playerB[0]);
				 
				if(i==j) { //충전소가 같다면
					all = Math.max(A,B);
					//얻을 수 있는 두 값중 큰 것을 가져온다.
					//0이 될수도 있고 power가 될 수도 있다.
				}else {
					all = A+B;//다르면 상관없으니까 둘 다 get
				}
				max = Math.max(all, max);
			}
		}
		return max;
	}
	static class BC {
		int y, x, c, p;

		BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}

}
