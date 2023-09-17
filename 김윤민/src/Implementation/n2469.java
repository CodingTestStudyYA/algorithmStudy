package Implementation;

import java.io.*;
import java.util.*;

public class n2469 {
	static int K, N;
	static String target;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		target = br.readLine();
		char[] cur = new char[K];
		char[] tgt = target.toCharArray();
		for (int i = 0; i < K; i++) {
			cur[i] = (char) ('A' + i);
		}
		// 로직 두번 실행
		// 1. 첫번째 부터 -> ??? 까지 순서대로
		// 2. 아래부터 -> ??? 까지 올라감
		boolean flag = false;
		String now;
		ArrayList<String> ladder = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			now = br.readLine();
			if (now.charAt(0) == '?') {
				flag = true;
				continue;
			} // ??? 입력 들어오면 flag 변경
			if (!flag) {
				for (int j = 0; j < K - 1; j++) {
					if (now.charAt(j) == '-') {
						cur = change(cur,j);
					}
				}
			} else {
				ladder.add(now);
			}
		}
		// 거꾸로
		for (int i = ladder.size() - 1; i >= 0; i--) {
			now = ladder.get(i);
			for (int j = 0; j < K - 1; j++) {
				if (now.charAt(j) == '-') {
					tgt = change(tgt,j);
				}
			}
		}
		// 두 개 다른거 구함.
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K - 1; i++) {
			if (tgt[i] == cur[i]) { //같으면 * 
				sb.append("*");
				
			} else {
				sb.append("-"); //다르면 다리 이어주고
				cur = change(cur,i);//위치 변경
			}
		}
		//같은지 비교
		for(int i=0;i<K;i++) {
			if(cur[i]!=tgt[i]) {//다르면 xxxx로 변경
				sb = new StringBuilder(createNone());
			}
		}
		System.out.println(sb.toString());
	}
	//x 생성
	static String createNone() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<K-1;i++) {
			sb.append("x");
		}
		return sb.toString();
	}
	static char[] change(char[] arr,int idx) {
		char tmp = arr[idx];
		arr[idx]=arr[idx+1];
		arr[idx+1]=tmp;
		return arr;
	}
}
