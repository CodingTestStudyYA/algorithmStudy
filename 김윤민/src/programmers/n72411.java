package programmers;

import java.io.*;
import java.util.*;

//메뉴 리뉴얼
public class n72411 {
	/*
	 * 이전에 각 손님들이 주문할 때 가장 많이 함께 주문된 단품메뉴-> 코스요리 코스요리 : 최소 2개 이상의 단품 메뉴 최소 2명 이상
	 * 손님으로부터 주문된 단품메뉴 조합에 대해서만.
	 * 
	 * 배열 문자열->오름차순.
	 */
	public static void main(String[] args) {
		String[] orders = { "XYZ", "XWY", "WXA" };
		int[] course = { 2, 3, 4 };
		System.out.println(Arrays.toString(solution(orders, course)));
	}

	/*
	 * 기준 : course순서 만약 2라면 각 order에 대해서 완탐이 맞따 예시로 1번 오더에서 5개중에서 2개를 고름. "B","G" 다음
	 * 손님들의 단품메뉴 조합에서 String으로 비교. orders[4]를 "BCFG"로 만들어서 다 포함된다면 map에 저장.+ count센다.
	 * 
	 */
	static int COUNT;
	static boolean[] visit;
	static ArrayList<String> result;
	static Map<String, Integer> resultCount;

	public static String[] solution(String[] orders, int[] course) {
		int size = course.length;
		result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			COUNT = course[i]; //course마다 바뀜
			resultCount = new HashMap<>();  // COUNT개인 코스가 가능한 친구들의 모음. + 몇개 포함되는지 count 
			for (int j = 0; j < orders.length; j++) { //
				visit = new boolean[orders[j].length()];// 해당 주문에 대한 조합을 위한(?) visit 
				dfs(0, 0, orders[j].length(), orders, j);//완탐
			}
			int max = 0;
			for (String key : resultCount.keySet()) { //max값 구하깅
				if(resultCount.get(key)>max) {
					max = resultCount.get(key);
				}
			}
			if(max==1) continue;//1개면 혼자만 적용되니까 안됨.
			for (String key : resultCount.keySet()) { //max값과 같은친구들 결과에 추가
				if(resultCount.get(key)==max) {
					result.add(key);
				}
			}
		}
		String[] answer = new String[result.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = result.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}

	public static void dfs(int cnt, int idx, int length, String[] orders, int customerNum) {
		if (cnt == COUNT) { //COUNT개 골랐으면
			//고른친구들 배열에 넣고 string으로 변환
			char[] pick = new char[COUNT]; 
			int index = 0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				if (visit[i]) {
					pick[index++] = orders[customerNum].charAt(i);
				}
			}
			Arrays.sort(pick);
			for (char c : pick) {
				sb.append(c);
			}
			//변환 끝
			
			if (resultCount.containsKey(sb.toString())) {// 가지고 있으면 이미 위에서 한번 탐색을 한경우.
				return;
			} else {//안가지고 있으면 가능한 코스에 추가.
				resultCount.put(sb.toString(), 1);
			}
			logic(customerNum, pick, orders, sb.toString());
			return;
		}
		for (int i = idx; i < length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			dfs(cnt + 1, idx + 1, length, orders, customerNum);
			visit[i] = false;
		}
	}
	//현재 주문고객부터 아래로 탐색.
	//위쪽은 이미 탐색된 경우기 때문에 올라갈 필요가 없다.
	public static void logic(int customerNum, char[] pick, String[] orders, String coursePick) {
		for (int i = customerNum + 1; i < orders.length; i++) {//아래고객부터
			boolean flag = true;
			for (int j = 0; j < COUNT; j++) { //pick들이 다 포함되는지 체크
				if (!orders[i].contains(pick[j] + "")) {//안포함되면 바로 break
					flag = false;
					break;
				}
			}
			//포함된다면
			if (flag) {
				resultCount.put(coursePick, resultCount.get(coursePick) + 1);//count증가.
			}
		}
	}
}
