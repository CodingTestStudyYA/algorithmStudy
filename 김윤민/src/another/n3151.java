package another;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n3151 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		Arrays.sort(arr); //정렬
		int ans=0;
		for(int i=0;i<N;i++) {
			if(arr[i]>0) break;//양수부터는 break->0이 될 수 없음
			int start = i+1;
			int end = N-1;
			while(start<end) {
				int cur = arr[i]+arr[start]+arr[end];
				int s = 1; //arr[start]와 중복되는 값이 몇개인가?
				int e = 1; //arr[end]와 중복되는 값이 몇개인가?
				if(cur==0) { //조건을 만족할때 
					/* 고려해야할 사항 두가지
					 * #1. start ~ end까지 같은 수가 나열 (-4 2 2 2 2) 
					 * #2. 각각 start와 end에서 같은 수가 나열 ( -5 -3 -3 0 8 8 )
					 */
					
					//#1
					if(arr[start]==arr[end]) {
						//몇개가 중복되었는지 구하고
						int n = end-start+1;
						//n개중에서 2개를 고르는 경우의 수가 필요
						//nC2 = n*(n-1)/2
						ans += n*(n-1)/2;
						break;
					}
					
					//#2.1 start값이 몇개 중복되었는지 계산
					//그러려먼 start뒤부터 세주면 됨.
					while(start+1<end&&arr[start]==arr[start+1]) {
						s++;
						start++;
					}
					
					//#2.2 end값이 몇개 중복됐는지 계산
					while(end-1>start&&arr[end]==arr[end+1]) {
						e++;
						end--;
					}
					ans+= e*s; //이동 다 했으니까 중복 경우의 수를 계산해서 넣어준다.
				}
				if(cur>0) end--;
				else start++;
			}
		}
		System.out.println(ans);
		
	}
}
