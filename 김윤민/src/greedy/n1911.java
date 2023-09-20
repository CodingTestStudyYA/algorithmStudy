package greedy;

import java.io.*;
import java.util.*;

public class n1911 {
	/*
	 * N개의 물웅덩 L길이 널빤지
	 * 
	 * 물웅덩이 시작-끝 위치
	 */
	static int N, L;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		// 시작위치 오름정렬
		PriorityQueue<Pool> pq = new PriorityQueue<>((e1, e2) -> e1.st == e2.st ? e1.ed - e2.ed : e1.st - e2.st);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int result = 0; // 개수
		int canLength = 0;// 덮을 수 있는 최대 위치
		// 하나씩 빼면서
		for (int i = 0; i < N; i++) {
			Pool p = pq.poll();
			if(p.ed < canLength) //최대 위치보다 ed가 작으면 이미 덮여져 있으므로 continue
                continue;

            if(canLength < p.st)	//현재 웅덩이 시작 위치 기준 최대 위치로 변경
                canLength = p.st;

            int extra = (p.ed - canLength) % L;	//널빤지 범위 넘어가는 값 구하기
            result += (p.ed - canLength) / L;	//사용할 널빤지 개수 구하기
            canLength = p.ed;
            //널빤지 범위 넘어갈 때
            if(extra != 0) {
                result+=1; //하나 더 추가
                canLength += L - extra; //최대 위치 조정
            }
		}
		System.out.println(result);
	}

	static class Pool {
		int st, ed;

		Pool(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
	}
}
