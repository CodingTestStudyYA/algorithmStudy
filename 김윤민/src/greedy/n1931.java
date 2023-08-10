package greedy;

import java.io.*;
import java.util.*;
//회의실 배정
public class n1931 {
	static int n;
	static Time[] list;
	static StringBuilder sb;
	//회의실 시작시간과 종료시간이 같을 수 있다.
	public static void main(String[] args) throws Exception{
		//input
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		//회의 수
		n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Time> q = new PriorityQueue<>();
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			q.offer(new Time(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		//input end
		
		//회의 시작  끝을 기준으로 이미 정렬되어 있음. (priorityQueue)
		Time prev = q.poll();//초기값 설정. 맨 처음 강의는 고정.
		int cnt=1;
		while(!q.isEmpty()) {
			Time tmp = q.poll();//하나씩 빼면서
			if(tmp.st>=prev.ed) {//만약 이전 종료 시간보다 내 시작시간이 크다면 cnt++
				cnt++;
				prev = tmp;
			}
		}
		System.out.println(cnt);
		
		
		
		
	}
	static class Time implements Comparable<Time>{
		int st,ed;
		Time(int st,int ed){
			this.st=st;
			this.ed=ed;
		}
		@Override
		public int compareTo(Time o) {
			//ed를 기준으로 정렬.
			//만약 종료 시점이 같다면, 작은거 먼저 오도록 설정.
			//왜?
			//만약 케이스 1,3 3,3 이 들어온다면 cnt=2
			//큰거를 기준으로 정렬한다면, cnt=1됨.
			if(o.ed==this.ed) {
				return this.st - o.st;
			}
			return this.ed-o.ed;
		}
	}
}
