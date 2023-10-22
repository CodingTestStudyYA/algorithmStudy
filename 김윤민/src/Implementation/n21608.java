package Implementation;


import java.io.*;
import java.util.*;

//상어 초등학교
public class n21608 {
	/* 1. 빈칸중 좋아하는 학생이 인접한 칸이 가장 많은 칸
	 * 2. 만족하는 칸이 여러개면, 
	 * 3.  빈칸이 더 많은 칸 + 행의 번호가 가장 작은칸 + 열 번호가 작은 칸
	 */
	static int N;
	static int[] like;
	static ArrayList<ArrayList<Integer>> likeList;
	static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static Deque<Point> blank;
	public static void main(String[] args) throws Exception{
		//input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		likeList = new ArrayList<>();
		for(int i=0;i<=N*N;i++) {
			likeList.add(new ArrayList<>());
		}
		like = new int[N*N];
		map = new int[N][N];
		for(int i=0;i<N*N;i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			like[i]=Integer.parseInt(st.nextToken());
			for(int j=0;j<4;j++) {
				likeList.get(like[i]).add(Integer.parseInt(st.nextToken()));
			}
		}
		//input end
		
		//logic
		map[1][1]=like[0];
		set();
		System.out.println(getScore());
		
	}
	public static void set() {
		for(int i=1;i<N*N;i++) { //순서대로 넣는다.
			int student = like[i];
			Point seat = searchAround(student);
			map[seat.y][seat.x]=student;
			
		}
	}
	public static Point searchAround(int student) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		getBlank();
		while(!blank.isEmpty()) {
			Point nowB = blank.poll();
			int blank=0;
			int love = 0;
			for(int i=0;i<4;i++) {
				int ny = nowB.y+dy[i];
				int nx = nowB.x+dx[i];
				//map에 해당하고, map[ny][nx]값이 좋아하는 사람에 해당하면
				if(can(ny,nx)) {
					if(likeList.get(student).contains((Integer)map[ny][nx])) {
						love++;//love♥
					}
					if(map[ny][nx]==0) {
						blank++;
					}
				}
			}
			pq.offer(new Point(nowB.y,nowB.x,love,blank));
		}
		Point p = pq.poll();
		return p;
	}
	//점수계산
	public static int getScore() {
		int score=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int student = map[i][j];
				int count =0;
				for(int z=0;z<4;z++) {
					int ny = i+dy[z];
					int nx= j+dx[z];
					if(can(ny,nx)&&likeList.get(student).contains(map[ny][nx])) {
						count++;
					}
				}
				if(count!=0) {
					score += Math.pow(10, count-1);
				}
			}
		}
		return score;
	}
	//빈칸 get
	public static void getBlank() {
		blank = new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0) {
					blank.add(new Point(i,j));
				}
			}
		}
	}
	static boolean can(int y,int x) {
		if(y<0||x<0||y>=N||x>=N) return false;
		return true;
	}
	static class Point implements Comparable<Point>{
		int y,x,s,b;
		Point(int y,int x){
			this.x=x;
			this.y=y;
		}
		Point(int y,int x,int s,int b){
			this.x=x;
			this.y=y;
			this.s=s;
			this.b=b;
		}
		//빈칸이 더 많은 칸 + 행의 번호가 가장 작은칸 + 열 번호가 작은 칸
		@Override
		public int compareTo(Point o) {
			if(this.s==o.s) {
				if(this.b==o.b) {
					if(this.y==o.y) {
						return this.x-o.x;
					}
					return this.y-o.y;
				}
				return o.b-this.b;
			}
			return o.s-this.s;
		}
	}
}
