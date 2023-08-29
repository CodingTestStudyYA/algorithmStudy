package greedy;
import java.io.*;
import java.util.*;
public class n2170 {

	public static void main(String[] args)throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		 * 선은 겹쳐 그을수 있는데 구분 못함
		 * 그려진 선들의 총 길이를 구해라
		 * 선이 여러번 그여진 곳은 한 번씩만 계산.
		 */
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stringT;
		
		/**
		 * 시작값이 작은것을 기준으로 정렬해서,
		 * 이전의 end값보다 지금 내 st값이 작으면 계속 진행
		 * 만약에 크면 값 계산 더해주고 새로 시작.
		 */
		PriorityQueue<Line> q = new PriorityQueue<>((e1,e2)->e1.st==e2.st?e1.ed-e2.ed:e1.st-e2.st);
		for(int i=0;i<N;i++) {
			stringT= new StringTokenizer(br.readLine());
			int st = Integer.parseInt(stringT.nextToken());
			int ed = Integer.parseInt(stringT.nextToken());
			q.offer(new Line(st,ed));
		}
		Line first = q.poll();
		int start = first.st;
		int end = first.ed;
		int sum=0;
		while(!q.isEmpty()) {
			Line next = q.poll();
			if(next. st<=end) {
				if(end<next.ed) {
					end=next.ed;
				}
			}else {
				sum+=(end-start);
				start = next.st;
				end=next.ed;
			}
		}
		sum+=(end-start);
		System.out.println(sum);
	}
	static class Line{
		int st,ed;
		Line(int st,int ed){
			this.st=st;
			this.ed=ed;
		}
	}

}
