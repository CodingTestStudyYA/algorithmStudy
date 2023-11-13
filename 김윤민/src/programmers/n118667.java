package programmers;

import java.io.*;
import java.util.*;

//두 큐 합 같게 만들기
public class n118667 {
	public static void main(String[] args) {

	}
	/* pop - insert = 1회
	 * 최소로 몇번을 해야지 두 큐의 합이 같을 수 있나?
	 * 
	 * 뺐다 넣었다를 총 (q1+q2)*2 까지가 최대. -> 힌트 조건.
	 */
	public int solution(int[] queue1, int[] queue2) {
		int ans = 0;

		long total = 0;// 전체 합
		long sum1 = 0;// 큐 하나의 합
		Deque<Integer> q1 = new ArrayDeque<>();
		Deque<Integer> q2 = new ArrayDeque<>();
		
		//초기화
		for (int i = 0; i < queue1.length; i++) {
			total += queue1[i] + queue2[i];
			sum1 += queue1[i];
			q1.add(queue1[i]);
			q2.add(queue2[i]);
		}
		
		if (total % 2 != 0) { //전체 합이 홀수면 똑같게 못만듬 -> 7이면? 3.5 3.5 안됨.
			ans = -1;
		}else {//짝수면
			//원소 하나가 10^9까지 되기 때문에 long타입으로 지정.
			long goal = total / 2; // 큐 하나에서 총 얼마가 되어야 하는가?
			
			while (true) {//다돌려
				//가지치기 조건.
				if (ans > (queue1.length + queue2.length) * 2) {//전체횟수가 총길이*2라면 영원히 될 수 없음.
					ans = -1;
					break;
				}
				if (sum1 == goal) //q1의 합이 goal이라면 끝
					break;
				else if (sum1 > goal) { //아니면 상황에 맞춰서 계산
					sum1 -= q1.peek(); 
					q2.add(q1.poll());
				} else {
					sum1 += q2.peek();
					q1.add(q2.poll());
				}
				ans++;
			}
		}
		

		return ans;
	}
}
