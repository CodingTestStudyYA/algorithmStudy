package another;



import java.io.*;

public class n9935 {
	/*
	 * 폭발 문자열 포함시, 모든 폭발 문자열 폭발, 남은 문자열 순서대로 이어붙여 새로운 문자열 새로 생긴 문자열에 폭탄 문자열이 있을 수도
	 * 있다 없을때 까지 계속
	 */
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String boom = br.readLine();
		StringBuilder sb = new StringBuilder();
		// input end
		// 남아있는 문자가 없는 경우 FRULA
		for (int i = 0; i < s.length(); i++) {
			char now = s.charAt(i);
			sb.append(now);
			// sb길이가 boom길이보다 크거나 같을때,
			// sb의 '뒤'부터 'boom'만큼 길이 탐색 -> 일치하는게 있으면 문자열 폭파
			if (sb.length() >= boom.length()) {
				boolean flag = true; // 문자열 폭파할 게 있나?
				for (int j = 0; j < boom.length(); j++) {
					char one = sb.charAt(sb.length()-boom.length()+j);
					char two = boom.charAt(j);
					if(one!=two) { //두개 다르면 문자열 안됨
						flag =false;
						break;
					}
				}
				if(flag) { //해당하면 '뒤에서부터' boom.length만큼 아웃
					sb.setLength(sb.length()-boom.length());
				}

			}
		}
		if(sb.length()==0) {
			System.out.println("FRULA");
		}else {
			System.out.println(sb);
		}
	}

}
