package another;
import java.io.*;
import java.util.*;
//탑
public class n2493 {
	//반대 방향(왼쪽 방향)으로 동시에 레이저 신호
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		Deque<Top> stack = new ArrayDeque<>();
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); //차례대로 넣고
			//가장큰 높이보다 현재 높이가 더 크면 pop ( 어짜피 못쓰니까)
			while (!stack.isEmpty() && stack.peek().height < arr[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) { //비었으면 왼쪽에 탑이 없으니까 0
				sb.append("0");
			} else {
				sb.append(stack.peek().idx); //아니면 현재 가장 큰 탑 idx를 가져온다.
			}
			sb.append(" ");
			stack.push(new Top(arr[i], i + 1)); 
		}
		System.out.println(sb.toString());
	}

	static class Top {
		int height;
		int idx;

		Top(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
	}
}