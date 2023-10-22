package programmers;

import java.io.*;
import java.util.*;
//컬러링 북
public class n1829 {

	public static void main(String[] args) {
		int m = 6;
		int n=4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		System.out.println(Arrays.toString(solution(m,n,picture)));
	}
	/* 상하좌우 연결된 영역 -> 같은 색상의 공간
	 * 몇개의 영역 + 가장 큰 영역의 넒이는?
	 * 
	 */
	static int numOA;
	static int maxSizeOA;
	public static int[] solution(int m, int n, int[][] picture) {
		numOA = 0;
		maxSizeOA = 0;
		int[][] pClone = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				pClone[i][j] = picture[i][j];
			}
		}
		countArea(m,n,pClone);
        int[] answer = new int[2];
        answer[0] = numOA;
        answer[1] = maxSizeOA;
        return answer;
    }
	// 행 : m, 열 : n
	public static void countArea(int m,int n,int[][] picture) {
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(picture[i][j]!=0) {
					numOA++;
					deleteArea(i,j,m,n,picture);
				}
			}
		}
	}
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void deleteArea(int y,int x,int m,int n,int[][] picture) {
		int area =1;
		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(y,x));
		int color = picture[y][x];
		picture[y][x]=0;
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(can(ny,nx,m,n)&&picture[ny][nx]==color) {
					area++;
					q.offer(new Point(ny,nx));
					picture[ny][nx]=0;
				}
			}
		}
		maxSizeOA = Math.max(area, maxSizeOA);
	}
	public static boolean can(int y,int x,int m,int n) {
		if(y<0||x<0||y>=m||x>=n) return false;
		return true;
	}
	static class Point{
		int y,x;
		Point(int y,int x){
			this.x=x;
			this.y=y;
		}
	}
	
}
