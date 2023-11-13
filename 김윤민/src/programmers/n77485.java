package programmers;

import java.io.*;
import java.util.*;

//행렬 테두리 회전하기
public class n77485 {
	/*
	 * 어디서 많이 본 문젠데 테두리만 시계방향으로 돌리고 거기서 가장작은수. => 시계방향으로 도는 그 안에 가장 작은 수.
	 * 
	 */
	public static void main(String[] args) {
		int[][] q = {{2,2,5,4,},{3,3,6,6},{5,1,6,3}};
		System.out.println(Arrays.toString(solution(6,6,q)));
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] map = new int[rows][columns];
		int num=1;
		//초기화
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				map[i][j]=num++;
			}
		}
		//logic
		for (int tc = 0; tc < queries.length; tc++) { // 각 케이스마다.
			// 거꾸로 받아 : 0부터 시작하니까 -1
			int sty = queries[tc][0]-1;
			int stx = queries[tc][1]-1;
			int edy = queries[tc][2]-1;
			int edx = queries[tc][3]-1;
			
			answer[tc]=rotate(sty,stx,edy,edx,map);
		}

		return answer;
	}
	//원래 돌앙야하는 방향과 반대로 rotate를 돈다.
	public static int rotate(int sty,int stx,int edy,int edx,int[][] map) {
		int min = Integer.MAX_VALUE;
		int now = map[sty][stx]; //현재 위치
		min = Math.min(min, now);
		//아래
		for(int i=sty; i<edy;i++) {
			map[i][stx] = map[i+1][stx];
			min = Math.min(min, map[i][stx]);
		}
		//오른
		for(int j=stx;j<edx;j++) {
			map[edy][j]=map[edy][j+1];
			min = Math.min(min, map[edy][j]);
		}
		//위
		for(int i=edy; i>sty;i--) {
			map[i][edx] = map[i-1][edx];
			min = Math.min(min, map[i][edx]);
		}
		//왼
		for(int j=edx;j>stx;j--) {
			map[sty][j]=map[sty][j-1];
			min = Math.min(min, map[sty][j]);
		}
		map[sty][stx+1]=now;
		
		return min;
	}

}
