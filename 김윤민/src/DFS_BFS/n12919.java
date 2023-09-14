package DFS_BFS;

import java.io.*;

public class n12919 {
	static boolean flag;
	static String targetS;
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		targetS = br.readLine();
		String s2 = br.readLine();
		// input end
		dfs(s2);
		if(flag) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}

	/*
	 * 뒤에서 부터 봄. -> 거꾸로 간다 생각
	 * 
	 * 문자열의 뒤에 A를 추가한다. 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
	 * -> 문자열 맨뒤의 A를 삭제한다.
	 * -> 문자열 첫번째가 B면 B를 빼고 문자열을 뒤집는다.
	 */
	static void dfs(String currentS) {
		// 만약 현재 길이가 서로 같다면 같은지 비교해서 결과값 return
		if (currentS.length() == targetS.length()) {
			if (currentS.equals(targetS)) {
				flag=true;
			}
			return;
		}
		StringBuilder sb;
		if (currentS.charAt(0) == 'B') { //-> 문자열 첫번째가 B면 B를 빼고 문자열을 뒤집는다.
			//sb를 이용해 reverse
			sb = new StringBuilder(currentS.substring(1)).reverse();
			dfs(sb.toString());
		}
		if (currentS.charAt(currentS.length() - 1) == 'A') { //-> 문자열 맨뒤의 A를 삭제한다.
			sb = new StringBuilder(currentS.substring(0, currentS.length()-1));
			dfs(sb.toString());
			
		}
	}

}
