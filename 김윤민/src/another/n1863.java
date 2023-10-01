package another;

import java.io.*;
import java.util.*;
/*
 * 스카이라인 쉬운거
 */
public class n1863 {
	static int N;
	static int ans;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextElement();
		stack.push(Integer.parseInt(st.nextToken()));// 초기값 그냥 push
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();// 날린다

			int now = Integer.parseInt(st.nextToken());
			if (now > stack.peek())
				stack.push(now);
			else if (now < stack.peek()) {
				while (now < stack.peek()) {
					ans++;
					stack.pop();
					if(stack.isEmpty()) break;
				}
				if(stack.isEmpty()) {
					stack.push(now);
				}else if(!stack.isEmpty()&&now>stack.peek()) {
					stack.push(now);
				}
			}
		}
		while(!stack.isEmpty()) {
			if(stack.pop()!=0) {
				ans++;
			}
		}
		System.out.println(ans);

	}

}
