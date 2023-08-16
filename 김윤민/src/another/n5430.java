package another;

import java.io.*;
import java.util.*;
//AC
public class n5430 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int TC = 0; TC < T; TC++) {
			String s = br.readLine(); //명령어
			br.readLine();//패스
			String arrS = br.readLine();//배열 받아옴
			Deque<Integer> q = new ArrayDeque<>();
			arrS = arrS.substring(1,arrS.length()-1); //괄호 자름
			if (!arrS.equals("")) { //내용이 있을때만 큐에 offer
				String[] arr = arrS.split(","); //,로 구분
				for (int i = 0; i < arr.length; i++) { //해당 글자들 offer
					q.offer(Integer.parseInt(arr[i]));
				}
			}
			boolean reverse = false; //R 명령어가 들어오면 엎지 않고 Rflag로 앞 뒤 구분.
			boolean flag = false; //error Flag
			for (int i = 0; i < s.length(); i++) {
				char now = s.charAt(i); //해당 명령어가
				if (now == 'R') { //R이면 rflag 변경
					reverse = !reverse;
				} else {//D면
					if (q.isEmpty()) { //q가 비어있을 때 error 
						flag = true; //error flag = true
						sb.append("error").append('\n'); // 에러 입력 
						break; 
					}
					if(reverse) { //q가 안비어있는데 reverse면 마지막 요소 poll
						q.pollLast();
					}else {
						q.poll(); //정순이면 앞에서 poll
					}
					
				}
			}
			if(flag) continue; //만약 flag=true면 에러이므로 다음 테스트케이스 수행
			sb.append('['); //아니면 남은 요소들 출력
			if(reverse) {
				while(!q.isEmpty()) {
					sb.append(q.pollLast()).append(",");
				}
			}else {
				while(!q.isEmpty()) {
					sb.append(q.poll()).append(",");
				}
			}
			if(sb.charAt(sb.length()-1)==',') {
				sb.setLength(sb.length()-1);
			}
			
			sb.append(']').append('\n');
		}
		System.out.println(sb);
	}
}
