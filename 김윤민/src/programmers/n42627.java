package programmers;

import java.io.*;
import java.util.*;
//디스크 컨트롤러
public class n42627 {
	public static void main(String[] args) {
		int[][] works = {{0,3},{1,9},{2,6}};
		System.out.println(solution(works));
	}
	public static int solution(int[][] jobs) {
		/* 1. 대기 2. 실행
		 * 쭉 돌리면서 실행 
		 * 요청시간이 현재 소요된 시간보다 작거나 같은 경우 대기큐에 삽입 ( 시간이 돼서 가능한 경우 )
		 * 대기큐가 비어있는 경우 : 메모리 쉬는시간 
				=> 시간 : 대기큐의 peek값의 요청시간으로 변경 
		 */
		PriorityQueue<Work> wait= new PriorityQueue<>((w1,w2)-> w1.st-w2.st);
		PriorityQueue<Work> run= new PriorityQueue<>((w1,w2)-> w1.w-w2.w);
		for(int i=0;i<jobs.length;i++) {
			wait.offer(new Work(jobs[i][0],jobs[i][1]));
		}
		int nowTime = 0;
		int waitTime = 0;
		int rest=0;
		int cnt=0;
		
		while(cnt<jobs.length) {
			//현재 시간보다 작으면 실행가능 
			while (!wait.isEmpty() && wait.peek().st <= nowTime) {
				run.offer(wait.poll());
            }
            
            if (run.isEmpty()) { //메모리가 쉬고있으면, 
            	rest += wait.peek().st - nowTime; //쉬는시간? 계산. ( 계산에서 빼야하는시간 )
                nowTime = wait.peek().st; //peek시간으로 변경
            }
            else {
            	Work work = run.poll();//있으면 뺴고
                waitTime += nowTime - work.st;//기다린 시간 계산
                nowTime += work.w;//실행하고 난 시간 계산
                cnt++;
            }
		}
        return (waitTime + nowTime - rest) / jobs.length;
    }
	static class Work {
		int st,w;
		Work(int st,int w){
			this.st=st;
			this.w=w;
		}
		
	}
}
