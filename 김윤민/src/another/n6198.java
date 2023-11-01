package another;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
//옥상 정원 꾸미기
public class n6198 {
	
	/* 오른쪽만 볼 수 있다.
	 * i번째 빌딩 관리인이 볼 수 있는 다른 빌딩의 옥상정원은 i+1, i+2...
	 * 나보다 작은것 만 볼 수 있다.
	 */
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new ArrayDeque<>();
		long ans=0;
		for(int i=0;i<N;i++) {
			int nowH = Integer.parseInt(br.readLine());
			//peek했을때 값이 있으면 비교해야함.
			while(!stack.isEmpty()&&stack.peek()<=nowH) {
				stack.pop();
			}
			stack.push(nowH);
			//스택에 값이 아직 남아있다면 나보다 큰 빌딩
			//=> 남은빌딩 -1 만큼 더해준다.
			//누적한다고 생각한다.
			
			ans += stack.size()-1;
//			System.out.println(i+","+ans);
		}
		//logic&input end
		
		System.out.println(ans);
		
	}
}
