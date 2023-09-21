package another;


import java.io.*;

//문자열게임2
public class n20437 {
	/*
	 * 소문자 문자열 W 양의 정수 K 어떤 문자를 정확히 K개 포함하는 가장 짧은연속 문자열의 길이는? 어떤 문자를 정확히 K개 포함하고,
	 * 문자열 첫글자와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열
	 * 
	 * 그니까 a라고 하면 a가 k개고, 첫글자와 마지막글자가 해당 문자여야 함.
	 */

	static int T, K, size;
	static String W;
	static int[] count;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			// input end
			if(K==1) {
				sb.append("1 1").append('\n');
				continue;
			}
			count = new int[26];
			size = W.length(); // w길이
			// 개수저장
			for (int i = 0; i < size; i++) {
				int idx = W.charAt(i) - 'a';
				count[idx]++;
			}
			
			// min
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				char now = W.charAt(i);
				if (count[now-'a']>=K) { //만약 해당하면
					int cnt=1;
					for(int j=i+1;j<size;j++) {
						char next = W.charAt(j);
						if(next==now) {
							cnt++;
						}
						if(cnt==K) {
							int tmpLength = j-i+1;
							min = Math.min(tmpLength, min);
							max = Math.max(tmpLength, max);
							break;
						}
					}
				}
			}
			if(min==Integer.MAX_VALUE||max==Integer.MIN_VALUE) {
				sb.append("-1").append('\n');
			}else {
				sb.append(min+" "+max).append('\n');
			}
		}  
		System.out.println(sb.toString());
		

	}
}