package programmers;

import java.util.*;

//단어 변환
public class n43163 {
	/*
	 * begin, target, words 1. 한번에 한개의 알파벳만 바꿀 수 있다. 2. words에 있는 단어로만 변환할 수 있다.
	 */
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log" };
		System.out.println(solution(begin, target, words));
	}

	/*
	 * dfs 1. words를 돌면서 차이가 1인것만 dfs돈다.->cnt개수 셈 2. 만약 target과 같으면 min계산
	 */
	static int min = Integer.MAX_VALUE;
	static int size;
	static boolean[] visit;

	public static int solution(String begin, String target, String[] words) {
		
		size = words.length;
		visit = new boolean[words.length];
		dfs(0,begin,target,words);
		if(min==Integer.MAX_VALUE) {
			min =0;
		}
		return min;
	}

	public static void dfs(int cnt, String now, String target,String[] words) {
		if(now.equals(target)) {
			min = Math.min(cnt, min);
			return;
		}
		for (int i = 0; i < size; i++) {
			if(visit[i]) continue;
			int dif = getDifferent(now,words[i]);
			if(dif==1) {
				visit[i]=true;
				dfs(cnt+1,words[i],target,words);
				visit[i]=false;
			}
		}
	}
	public static int getDifferent(String a,String b) {
		int result=0;
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)!=b.charAt(i)) result++;
		}
		return result;
	}
}
