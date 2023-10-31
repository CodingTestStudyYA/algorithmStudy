import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2580{

    static int[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[9][9];

        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        sudoku(0,0); // 검사시작
    }

    static void sudoku(int y, int x){

        if(y > 8) {
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);

            return;
        }

        if(x > 8) {
            sudoku(y+1, 0); // 다음칸 검사 ㄱㄱ
        }

        if(map[y][x] == 0) {
            for(int i = 1; i <= 9; i++){
                if(isAvailable(y, x, i)) {
                    map[y][x] = i;
                    sudoku(y, x+1);
                    map[y][x] = 0; // 다시 돌려주기 ㅇㄴ
                }
            }
        }else
            sudoku(y, x+1);
    }


    static boolean isAvailable(int y, int x, int num){ // y,x 칸에 num이 들어가는 지 검사하기

        // 행, 열 동시 검사하기
        for(int i = 0; i < 9; i++){
            if(map[y][i] == num) return false;
            if(map[i][x] == num) return false;
        }

        // 사각형 검사하기
        for (int i = (y / 3) * 3; i < (y / 3) * 3 + 3; i++) {
            for (int j = (x/ 3) * 3; j < (x / 3) * 3 + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }
        return  true;
    }
}
