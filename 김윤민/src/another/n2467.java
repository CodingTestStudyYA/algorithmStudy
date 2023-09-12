package another;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n2467 {
	/*
	 * 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer sto = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(sto.nextToken());
		}
		
		int st =0;
		int end =n-1; //끝위치
		int lIdx =0, rIdx = 0; //왼,오른 인덱스
		long min = Long.MAX_VALUE; // 가장 큰값으로 초기화
		//# 이분탐색
		while(st<end) {
			long sum = arr[st]+arr[end]; //비교대상 값
			if(min > Math.abs(sum)) { //가장 작다면
				min = Math.abs(sum); //바꿔주고
				lIdx = st; rIdx = end; //인덱스기록
			}
			if(sum>=0) { //0보다 크면 st값과 절대값이 같거나, ed가 더큼 
				end--;	
			}else { //작으면 절대값이  st가 더 큼
				st++;
			}
		}
		System.out.println(arr[lIdx] +" "+arr[rIdx]);
	}
}
