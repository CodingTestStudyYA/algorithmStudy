package algo;

import java.util.*;
import java.io.*; 

public class BOJ11000 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 최소의 강의실 사용 - 모든 수업 가능 
	static Queue<Integer> q ;
	// 끝나는 시간을 담는 우선순위 큐 
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine()); 
		Lecture[] lectures = new Lecture[N]; 
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken() )); 
		}

		int cnt = 1;
		
		Arrays.sort(lectures, new Comparator<Lecture>() {

			@Override
			public int compare(Lecture o1, Lecture o2) {
				return o1.start - o2.start;
			}
			
		});
		
		q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		});
		
//		System.out.println(Arrays.toString(lectures));

		for(int i = 0; i<N-1; i++) {
			// queue에 끝나는 시간을 하나씩 넣어두고 회의 택해서 peek하고 하나씩 보기..
			// 이때 queue에서 끝나는 시간은 자동으로 정렬되어야함 
			// 먼저 시작해서 더 늦게 끝나는 회의 고려를 위해서 .... 
			// 아마 우선수위 큐를 사용 
			
			q.add(lectures[i].end); 
			// 끝나는 시간들을 큐에 넣어주고 해당 시간이 되면 (회의의 시작시간이 peek한 값과 같을때)
			// 빼주면서 클래스를 증가시키지 않음
			// 아닌 경우엔 클래스를 증가시켜 주어야함 
			
			if(q.peek() <= lectures[i+1].start) {
				q.poll(); 
//				System.out.println(i);
			}else {
				cnt ++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static class Lecture{
		int start, end; 
		Lecture(int start, int end){
			this.start = start;
			this.end = end; 
		}
//		@Override
//		public String toString() {
//			return "Lecture [start=" + start + ", end=" + end + "]";
//		}
		
		
	}

}
