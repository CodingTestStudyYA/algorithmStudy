package greedy;

import java.io.*;
import java.util.*;
public class n2141 {
	/*
	 * N개의 마을.
	 * i번째 마을은 X[i]에 위치, A[i]명의 사람이 살고 있다.
	 * 우체국 위치를 각 사람들까지의 거리 합이 최소가 되는 위치에 우체국을 세우자.
	 */
	static int N;
	static long all;
	static PriorityQueue<Village> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br  =new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list = new PriorityQueue<>((e1,e2)->e1.x==e2.x?e1.a-e2.a:e1.x-e2.x);
		for(int i=1;i<=N;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			list.offer(new Village(x,a));
			all+=a;
		}
		int sum=0;
		long half = (all+1)/2;
		int x = 0;
		while(!list.isEmpty()) { //작은 값 부터 탐색해서 
			//처음 중간값보다 크거나 같아지는 경우 break;
			Village tmp = list.poll();
			sum += tmp.a;
			if(sum>=half) {
				x = tmp.x;
				break;
			}
		}
		System.out.println(x);

	}
	static class Village{
		int x,a;
		Village(int x,int a){
			this.x=x;
			this.a=a;
		}
	}
}
