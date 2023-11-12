import java.util.*;

class Programmers_행렬테두리회전 {

    static int[][] map ;
    public static void main(String[] args){


    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        // 행 렬 초기 세팅
        map = new int[rows][columns];
        int idx = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = idx++;
            }
        }

        // 범위를 여러번 선택해서 시계방향 회전시킴
        // (x1, y1, x2, y2) -> 범위
        // 중앙은 회전하지 않음.

        int i = 0;
        for (int[] q : queries) {
            int y1 = q[0]; // 2
            int x1 = q[1]; // 2
            int y2 = q[2]; // 5
            int x2 = q[3]; // 4

            // 범위 잡기
            answer[i++] = rotate(x1 - 1, x2 - 1, y1 - 1, y2 - 1, rows, columns);
            // 회전의 개수가 10000이하...... 하나씩 돌린다고 해결될게 아니긴 함..

        }

        return answer;
    }

    static int rotate(int x1, int x2, int y1, int y2, int rows, int columns){
        // 2,2 -> 5,4
        // 범위 내에서 가장 작은 숫자를 구하기
        int min = Integer.MAX_VALUE;

        int temp1 = map[y1][x2]; // 오른쪽 가장 위
        min = Math.min(min, temp1);

        int startx = x2;
        int starty = y1;

        // 윗줄 오른쪽으로 이동
        while (startx > x1) {
            map[y1][startx] = map[y1][startx - 1];
            min = Math.min(min, map[y1][startx]);
            startx--;
        }
//		// 회전 시키기
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < columns; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=========================");
        // 오른쪽줄 아래로 이동
        // 오른쪽 가장 위였던 숫자가 아래로 내려가야함
        // 오른쪽 가장 아래 숫자가 temp에 저장되어야함
        int temp2 = map[y2][x2];
        min = Math.min(min, temp2);

        startx = x2;
        starty = y2;
        while (starty > y1) {
            map[starty][startx] = map[starty - 1][startx];
            min = Math.min(min, map[starty][startx]);
            starty--;
        }
        map[y1 + 1][x2] = temp1; //
        // 회전 시키기
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < columns; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=========================");

        // 아래줄 왼쪽으로 이동시키기
        int temp3 = map[y2][x1]; // 왼쪽 가장 아래
        min = Math.min(min, temp3);

        startx = x1;
        starty = y2;
        while (startx < x2) {
            map[starty][startx] = map[starty][startx + 1];
            min = Math.min(min, map[starty][startx]);
            startx++;
        }
        map[y2][x2 - 1] = temp2;

//		// 회전 시키기
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < columns; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=========================");

        // 왼쪽줄 위로 이동시키기
        startx = x1;
        starty = y1;
        while (starty < y2) {
            map[starty][startx] = map[starty + 1][startx];
            min = Math.min(min, map[starty][startx]);
            starty++;
        }

        map[y2 - 1][x1] = temp3;

//		// 회전 시키기
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < columns; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

        return min;
    }
}
