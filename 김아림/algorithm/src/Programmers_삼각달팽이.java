import java.util.Arrays;

public class Programmers_삼각달팽이 {
    static int n = 5;
    public static void main(String[] args) {
        int[] answer = new int[(n*(n+1))/2];

        // 피라미드 모양 -> 삼각형으로 왼쪽이나 오른쪽으로 밀어서 생각하자
        // 제일 마지막 숫자가 무엇일까

        int[][] sq = new int[n+1][n+1];
        // 방향은 아래 오른쪽 위
        int[] dx = {0, 1, -1};
        int[] dy = {1, 0, -1};

        // 1) 채우기
        // n -> 4 를 기준으로
        // 아래로 4, 오른쪽으로 3, 대각선 왼쪽 위로2, 아래로 1
        // n -> 5 를 기준으로
        // 아래로 5, 오른쪽으로 4, 대각선 왼쪽 위로 3, 아래로 2, 오른쪽으로 1
        int idx = 1; // 1부터 시작함
        int x = 0;
        int y = -1;
        int dir = 0;
        for(int i = n; i > 0; i--){
            // 가야하는 칸의 개수 i개
            // 방향 전환 반복해줘야함
            int cnt = i;
            while(cnt > 0){
                if(dir == 3){
                    dir = 0;
                }
                y = y + dy[dir];
                x = x + dx[dir];

                sq[y][x] = idx++;
                cnt--;
            }
            dir += 1;
        }

//        for(int i = 0; i < n; i++){
//            System.out.println(Arrays.toString(sq[i]));
//        }

        int input_idx = 0;
        // 2) 순서대로 answer에 넣어주기
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                answer[input_idx++] = sq[i][j];
//                System.out.print(sq[i][j]);
            }
//            System.out.println();
        }

        System.out.println(Arrays.toString(answer));
    }
}
