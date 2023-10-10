package programmers;

public class n49993 {
	public static void main(String[] args) {
		String[] ans = { "BACDE", "CBADF", "AECB", "BDA" };
		System.out.println(solution("CBD", ans));
	}

	/*
	 * 선행 스킬 순서 : 스파크->라볼 -> 썬더 다른건 순서에 상관없이 배울 수 있다.
	 * 
	 * 스킬순서와 유저들이 만든 스킬트리를 담은 배열이 주어질때 가능한 스킬트리 개수 return
	 */
	public static int solution(String skill, String[] skill_trees) {

		int answer = 0;
		// 스킬들을 소문자로 변형
		// 소문자인경우만 처리하면 됨
		char cnt = 'a';
		for (int i = 0; i < skill.length(); i++) {
			char c = skill.charAt(i);
			for (int j = 0; j < skill_trees.length; j++) {
				skill_trees[j] = skill_trees[j].replace(c, cnt);
			}
			cnt++;
		}
		
		
		for (String sk : skill_trees) {
			boolean flag = false;
			char prev = '`';
			for (int i = 0; i < sk.length(); i++) {
				//skill의 길이는 1이상 26이하.
				//소문자라면?
				//이전 스킬 순서를 확인한 후, 차이가 1일때 continue
				if(sk.charAt(i)>='a'&&sk.charAt(i)<='z') {
					char now = sk.charAt(i);
					if(now-prev!=1) {
						flag = true;
						break;
					}else {
						prev = now;
					}
				}
			}
			if(!flag) {
				 answer++;
			}
		}

		return answer;
	}

}
