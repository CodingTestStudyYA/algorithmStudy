package programmers;

/*
 *	프로그래머스 
 *  level2
 *  방금그곡
 */
import java.util.*;
public class n17683 {
	/*
	 * 기억하는 멜로디 : 음악 끝부분 + 처음부분 만약 음악을 중간에 끊은 경우, 원본음악에 기억한 멜로디가 있다해도 그 곡이 아닐수도 있음
	 * -> 재생시간 + 제공된 악보를 보면서 비교 = 네오가 찾으려는 음악의 제목은?
	 * 
	 * 음악 제목, 재생 시작&끝난 시각, 악보 음 : 1분에 1개 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개
	 * 음악길이 < 재생시간 => 끊김없이 처음부터 반복재생 재생시간 긴거 < 먼저 입력
	 */
	public static void main(String[] args) {
		String m = "CC#BCC#BCC#BCC#B";
		String[] musicinfos = { "03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
		String result = solution(m, musicinfos);
		
		System.out.println(result);
	}

	/*
	 * m : 음 info : 시작시간, 끝난 시간, 음악 제목, 악보 정보 (,로 구분)
	 */
	public static String solution(String m, String[] musicinfos) {
		ArrayList<Play> ans =new ArrayList<>();
		// ,로 split -> 만약 조건을 만족한다면 재생된 시간과 제목을 저장.
		for (int i = 0; i < musicinfos.length; i++) {
			String[] info = musicinfos[i].split(",");
			// 1. 시간 차이 구하기
			int stHour = Integer.parseInt(info[0].split(":")[0]);
			int stMin = Integer.parseInt(info[0].split(":")[1]);
			int edHour = Integer.parseInt(info[1].split(":")[0]);
			int edMin = Integer.parseInt(info[1].split(":")[1]);
			
			String title = info[2];
			String note = getNote(info[3]);
			String listen = getNote(m);
			// 분 차이 계산
			int time = (edHour - stHour) * 60 + (edMin - stMin);
			
			//만약 플레이시간이 노트보다 짧다면 노트 자르기
			String play = "";
			if(time<=note.length()) {
				play = note.substring(0,time);
			}else {
				StringBuilder sb= new StringBuilder(note);
				
				//몫 + 나머지로 붙여야 함.
				for(int q = 1; q<time/note.length();q++) {
					sb.append(note);
				}
				
				sb.append(note.substring(0,time%note.length()));
				play = sb.toString();
			}
			if(play.contains(listen)) {
				ans.add(new Play(time,title));
			}
		}
		String resultT = "";
		int max =0;
		for (Play play : ans) {
			if(play.time>max) {
				max = play.time;
				resultT = play.title;
			}
		}
		if(resultT.equals("")) {
			resultT="(None)";
		}
		//순회하면서 찾아서 return
		return resultT;
	}
	//C#, D#, F#, G#, A#
	static String getNote(String note) {
		return note.replaceAll("C#","T").replaceAll("D#","M").replaceAll("F#","U").replaceAll("G#","N").replaceAll("A#","V");
		
		
	}
	static class Play{
		int time;
		String title;
		Play(int time,String title){
			this.time = time;
			this.title = title;
		}
		
	}
}
