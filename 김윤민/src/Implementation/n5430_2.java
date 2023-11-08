package Implementation;

import java.io.*;
import java.util.*;
public class n5430_2 {
	/* R : 뒤집기, D : 버리기
	 * 뒤집기 : 배열 순서 뒤집음
	 * 버리기 : 첫번째 수 버림.
	 * 비어있는데 D쓰면 에러.
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0;t<T;t++) {
			String cmd = br.readLine();
			int N = Integer.parseInt(br.readLine());
			String arrS = br.readLine();
			String[] arr = arrS.substring(1,arrS.length()-1).split(",");
			
			//logic
			boolean reverse = false;
			boolean error = false;
			Deque<Integer> dq = new ArrayDeque<>();
			for(int i=0;i<N;i++){
				dq.offer(Integer.parseInt(arr[i]));
			}
			for(int i=0;i<cmd.length();i++) {
				char cmdC = cmd.charAt(i);
				if(cmdC=='R') {
					reverse = !reverse;
				}else{//D면
					if(dq.isEmpty()) {
						error = true;
						break;
					}
					if(reverse) { //뒤집은 상태면
						dq.pollLast();
					}else {
						dq.pollFirst();
					}
				}
			}
			if(error) {
				sb.append("error").append('\n');
			}else {
				if(dq.size()==0) {
					sb.append("[]").append('\n');
				}else {
					sb.append("[");
					if(reverse) {
						int size = dq.size();
						for(int i=0;i<size;i++) {
							sb.append(dq.pollLast()).append(",");
						}
					}else {
						for (Integer integer : dq) {
							sb.append(integer).append(",");
						}
					}
					
					sb.setLength(sb.length()-1);
					sb.append("]").append('\n');
				}
				
			}
		}
		System.out.println(sb);
	}
}
