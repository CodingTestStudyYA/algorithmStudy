package greedy;

import java.io.*;
import java.util.*;
//배
public class n1092 {
	/*
	 * 크레인 N대, 1분에 박스 하나씩 / 모든 크레인은 동시에 움직인다.
	 * 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다
	 * 
	 * 모든 박스를 배로 옮기는데 드는 시간의 최솟값
	 * 못옮기면 -1
	 */
	static int N,M,time;
	static ArrayList<Integer> crane,box;
	static boolean flag;
	public static void main(String[] args) throws Exception{
		//input
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		crane = new ArrayList<>();
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}
		M = Integer.parseInt(br.readLine());
		box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		//input end
		
		//logic
		
		//크레인도 큰것부터, 박스도 큰것부터 처리
		//큰거끼리 매칭
		Collections.sort(crane,Collections.reverseOrder());
		Collections.sort(box,Collections.reverseOrder());
		// 크레인이 옮길수 있는 가장 큰 무게보다 박스가 크면 그 박스는 못옮김 -> -1
		if(crane.get(0)<box.get(0)) {
			flag=true;
		}
		if(!flag) {//옮길수 있는 정도라면
			while(!box.isEmpty()) { //박스가 빌때까지 크레인으로 이동
				int bIndex = 0;//박스 인덱스
				int cIndex = 0;//크레인 인덱스
				//크레인 한바퀴 돌기
				while(cIndex<N) { 
					if(bIndex==box.size()) break; //박스 인덱스가 범위를 넘어가면 break
					// 크레인이 박스를 담을 수 있으면
					if(crane.get(cIndex)>=box.get(bIndex)) {
						box.remove(bIndex); //박스를 빼고
						cIndex++; //크레인 다음꺼
					}else {
						bIndex++; //못담으면 다음 박스보쟈
					}
				}
				time++;//시간은 증가
			}
			
		}
		
		if(flag) {
			System.out.println(-1);
		}else {
			System.out.println(time);
		}
	}
}
