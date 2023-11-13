package programmers;

import java.io.*;
import java.util.*;

//삼각달팽이
public class n68645 {
	/*
	 * ArrayList로 생각? -> 안됨. 마지막에 추가해야하는데 그 연산을 못함. --> 배열 ?
	 * 
	 * 총 3개의 방향 
	 * 1. 왼쪽아래 
	 * 2. 오른쪽 
	 * 3. 왼쪽 위
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5)));
	}

	/*
	 * 총 개수 : n * ( n + 1 ) / 2 arr[n][n]
	 * 
	 * 1. 아래 ( i+1 ) 
	 * 2. 오른쪽 ( j+1 ) 
	 * 3. 왼쪽 위 ( i-1, j-1 )
	 */
	public static int[] solution(int n) {
		int[][] arr = new int[n+1][n+1];
		int all = (n * (n + 1)) / 2;
		int[] ans = new int[all];

		// 시작 
		int y = 0, x = 0;
		int num = 1;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i % 3 == 0) { //1번방향이면
					++y;  //아래로
				} else if (i % 3 == 1) { //2번 방향이면
					++x; //오른쪽으로
				} else if (i % 3 == 2) {//3번 방향이면
					--y; //왼쪽 위로
					--x;
				}
				arr[y][x] = num++;//위치 정해지면 입력
			}
		}
		//1차원 배열에 옮기기
		int idx=0;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<i;j++){
                ans[idx] = arr[i][j];
                idx++;
            }
        }
		return ans;
	}
}
