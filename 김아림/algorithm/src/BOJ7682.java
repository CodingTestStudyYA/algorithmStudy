
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ7682 {

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int idx = 1;
        while(!input.equals("end")) {

//            System.out.println(idx++);
            int[] player1_win = new int[3];
            int[] player2_win = new int[3];

            int player1_count = 0;
            int player2_count = 0;
            int empty_count = 0;
            map = new char[3][3];

            int input_idx = 0;
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    map[r][c] = input.charAt(input_idx);
                    if(map[r][c] == '.') empty_count++;
                    if(map[r][c] == 'X') player1_count++;
                    if(map[r][c] == 'O') player2_count++;
                    input_idx++;
                }
            }

            // 가로 줄 검사
            for(int r = 0; r < 3; r++) {
                if(map[r][0] == map[r][1] &&  map[r][1] == map[r][2]) {
                    if(map[r][0] == 'X') {
                        player1_win[0]++;
                    }
                    else if(map[r][0] == 'O') {
                        player2_win[0]++;
                    }
                }
            }

            // 세로줄 검사
            for(int r = 0; r < 3; r++) {
                if(map[0][r] == map[1][r] &&  map[1][r] == map[2][r]) {
                    if(map[0][r] == 'X') {
                        player1_win[1]++;
                    }
                    else if(map[0][r] == 'O') {
                        player2_win[1]++;
                    }
                }
            }

            // 대각선 검사
            if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                if(map[0][0] == 'X') {
                    player1_win[2]++;
                }
                else if(map[0][0] == 'O') {
                    player2_win[2]++;
                }
            }

            if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                if(map[0][2] == 'X') {
                    player1_win[2]++;
                }
                else if(map[0][2] == 'O') {
                    player2_win[2]++;
                }
            }
            // 만약 한 사용자가 2번이상 이기거나
            // 두 사용자가 모두 이긴 상태라면 게임이 말이 안됨
            // 혹은 두 사용자가 모두 이기지 않았는데 빈칸이 남은 경우


            // 가로 두개 혹은 세로 두개는 안됨
            // 가로 세로 하나 이렇게는 됨
            int sum_player1 = player1_win[0] + player1_win[1]  + player1_win[2] ;
            int sum_player2 = player2_win[0] + player2_win[1]  + player2_win[2] ;


            boolean flag = true;
            // 후공은 선공보다 많을 수 없음
            if(player2_count > player1_count){
                flag = false;
            }

            // 둘 다 이긴 경우
            if(sum_player1 > 0 && sum_player2 >0){
                flag = false;
            }

            // 가로 세로가 두개 이상씩인 경우
            if(player1_win[0] >= 2 || player1_win[1]  >= 2 || player2_win[0] >= 2 || player2_win[1]  >= 2 ) {
                flag = false;
//                System.out.println("1");
            }

            // 두 사용자 모두 이기지 않은데다가 빈칸이 남은 경우
            if(sum_player1 == 0 && sum_player2==0 && empty_count > 0 ) {
                flag = false;
//                System.out.println("2");

            }

            // empty_count == 0 <- 꽉 채움을 의미
            if(empty_count == 0 && (player1_count < player2_count)) {
                flag = false;
//                System.out.println("3");

            }

            // 가, 세, 대각선 한줄 채우고 다른 사용자는 아닌 상태에서
            // 다른 사용자의 개수가 더 많다면?

            // 1번 사용자가 한줄로 이길 경우 -> 2번 사용자의 말의 수는 2개
            // 2번 사용자가 한줄로 이길 경우 -> 1번 사용자의 말의 수는 3개

            if(sum_player1 >= 1 && sum_player2 ==0 ){
                if(player1_count != player2_count + 1){
//                    System.out.println("======");
                    flag = false;
//                    System.out.println(player1_count);
//                    System.out.println(player2_count);
//                    System.out.println("4");
//                    System.out.println("=====");
                }
            }

            if(sum_player2 >= 1 && sum_player1 ==0 ){
                if(player2_count != player1_count){
                    flag = false;
//                    System.out.println("5");

                }
            }

            if(flag) System.out.println("valid");
            else System.out.println("invalid");

            input = br.readLine();

        }

    }

}