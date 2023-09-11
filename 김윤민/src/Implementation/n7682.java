package Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//틱택토
public class n7682 {
	static char[][] map;
	static int ticX;
	static int ticY;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			boolean flag = true;
			map = new char[3][3];
			boolean blank = false; // 빈칸있는지 체크하는 boolean
			int xCnt = 0; // x개수
			int oCnt = 0; // o개수
			String s = br.readLine();
			if (s.equals("end"))
				break; // end면 break
			for (int i = 0; i < 9; i++) {
				map[i / 3][i % 3] = s.charAt(i); // 해당 위치에 put
				char now = map[i / 3][i % 3];
				if (now == '.') // 각 연산해줌.
					blank = true;
				else if (now == 'X')
					xCnt++;
				else if (now == 'O')
					oCnt++;
			}

			// 조건체크
			ticX = 0; // 틱택토가 존재하면 3 없으면 0
			ticY = 0;

			// # 1. 틱택토 존재? 체크 ------------------------------------

			// 오른 대각선
			if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
				if (map[0][0] == 'X')
					ticX = 3;
				else if (map[0][0] == 'O')
					ticY = 3;
			}
			// 왼쪽대각선
			if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
				if (map[0][2] == 'X')
					ticX = 3;
				else if (map[0][2] == 'O')
					ticY = 3;
			}
			// 가로 틱택토
			for (int i = 0; i < 3; i++) {
				if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
					if (map[i][0] == 'X')
						ticX = 3;
					else if (map[i][0] == 'O')
						ticY = 3;
				}
			}
			// 세로 틱택토
			for (int i = 0; i < 3; i++) {
				if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
					if (map[0][i] == 'X')
						ticX = 3;
					else if (map[0][i] == 'O')
						ticY = 3;
				}
			}

			// 1 end ------------------------------------------

			// 나머지 조건들 체크
			// # 2. 만약 빈칸이 있다면 틱택토가 존재해서 연산을 그만둔 경우므로 틱택토 없으면 invalid
			if (blank) {
				// 틱택토가 없으면 false
				if (ticY == 0 && ticX == 0) {
					flag = false;
				}
			}

			// # 3. X만 완성된 경우
			// 만약 X가 완성된 경우 oCnt와 차이가 1 나야함. 왜냐면 X가 먼저 완성된거니까.
			if (ticX == 3) {
				if (xCnt - oCnt != 1)
					flag = false;
			}
			// # 4. O만 완성된 경우
			// o만 완성된거라면 x와 o의 개수가 같아야함.
			if (ticY == 3 && ticX < 3) {
				if (oCnt != xCnt)
					flag = false;
			}
			// # 5. 둘다 완성된 경우는 있을 수 없다.
			// 둘 다 완성된 경우는 안됨. 하나가 완성되면 멈춰야 하므로...
			if (ticY == 3 && ticX == 3) {
				flag = false;
			}
			// # 6. X가먼저 시작하고,O가 이후에 시작하므로
			// X개수와 O의 개수의 차이는 항상 0이상 1 이하여야 한다.
			if (xCnt - oCnt > 1 || xCnt - oCnt < 0)
				flag = false;
			if (flag) {
				sb.append("valid" + '\n');
			} else {
				sb.append("invalid" + '\n');
			}
		}
		System.out.println(sb);

	}

}
