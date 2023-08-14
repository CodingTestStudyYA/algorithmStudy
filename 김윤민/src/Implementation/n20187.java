package Implementation;

import java.io.*;
import java.util.*;
//난 이거 3시간 풀었는데도 29점이 최대다
//오늘은 GG 더이상 못해

//부분점수 29점 case 1 정답
//종이 접기
public class n20187 {
	static int n, k;
	static String[] list;
	static int[][] map;
	
	static HashMap<Integer,Integer> match1 = new HashMap<>(); //위아래 세로
	static HashMap<Integer,Integer> match2 = new HashMap<>(); //오른왼 가로 
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = n * 2;
		list = new String[m];
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(br.readLine());
		int num = (int) Math.pow(2, n);
		map = new int[num][num];
		int x = 0, y = 0, r_size = num, c_size = num;// 시작 위치 찾아줌.
		for (int i = 0; i < m; i++) {
			num = num / 2;
			list[i] = st.nextToken();
			switch (list[i]) {// 시작 위치 알아냄.
			case "U": // 위로 접음 조정 x
				r_size /= 2;
				break;
			case "D": // 아래로 접음 y값 조정 o
				r_size /= 2;
				y += r_size;
				break;
			case "L": // 왼쪽으로 접음 x조정 x
				c_size /= 2;
				break;
			case "R": // 오른쪽으로 접음 x조정 O
				c_size /= 2;
				x += c_size;
				break;
			}
		}
		
		// 구멍 뚧어
		int ny = y%2;
		int nx = x%2;
		map[ny][nx] = k;
		//어짜피 4개 패턴이 반복됨.
		defaultForm(ny,nx);
		
		//계속 4개 반복.
		for(int i=0;i<n*2;i++) {
			for(int j=0;j<n*2;j++) {
				int ni = i%2;
				int nj= j%2;
				if(ni==0&&nj==0) {
					sb.append(map[0][0]);
				}else if(ni==0&&nj==1) {
					sb.append(map[0][1]);
				}else if(ni==1&&nj==0) {
					sb.append(map[1][0]);
				}else if(ni==1&&nj==1) {
					sb.append(map[1][1]);
				}
				sb.append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	static void dMethod() { //대칭 미리 저장.
		match1.put(0,2); //세로
		match1.put(1,3);
		match1.put(2,0);
		match1.put(3,1);
		
		match2.put(0,1); //가로
		match2.put(1,0);
		match2.put(2,3);
		match2.put(3,2);
	}
	static void defaultForm(int ny,int nx) {
		dMethod();
		//match2 가로 match1 세로 
		if(ny==0&&nx==0) {
			map[0][1]=match2.get(k);
			map[1][0]=match1.get(k);
			map[1][1]=match2.get(map[1][0]);
		}else if(ny==0&&nx==1) {
			map[0][0]=match2.get(k);
			map[1][1]=match1.get(k);
			map[1][0]=match1.get(map[0][0]);
		}else if(ny==1&&nx==0) {
			map[0][0]=match1.get(k);
			map[1][1]=match2.get(k);
			map[0][1]=match2.get(map[0][0]);
		}else if(ny==1&&nx==1) {
			map[0][1]=match1.get(k);
			map[1][0]=match2.get(k);
			map[0][0]=match2.get(map[0][1]);
		}
	}

}
