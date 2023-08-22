import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17281 {

    static int[][] player; // i번째 선수가 j번째 이닝에서 얻는 결과

    static int N;
    static boolean[] select;
    static int[] playerQ;
    static int[] base;
    static int ans = Integer.MIN_VALUE;
    static int score;

    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        player = new int[9][N];
        select = new boolean[9];
        playerQ = new int[9];

        base = new int[4]; // 첫번째 쳐서 출루한사람, 두번째 쳐서 출루한사람, 세번째 쳐서 출루한 사람

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[i][i] = Integer.parseInt(st.nextToken());
            }
        }

        // 4번 타자는 정해져 있으므로 4번 빼고 정해야함요

        makeQ(0);

    }

    // 상태 0, 1, 2, 3
    // 3을 넘어서면 점수 +1
    // 큐에 선수의 상태를 넣고 돌릴까... 흠....
    static void makeQ(int src) {

        if (src == 3) {
            src++;
        }

        if (src == 9) {
            score = 0;
            playerQ[3] = 0;
            System.out.println(Arrays.toString(playerQ));
            // 여기서 계산
            calc();
            ans = Math.max(ans, score);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (select[i])
                continue;

            select[i] = true;
            playerQ[src] = i;
            makeQ(src + 1);
            select[i] = false;

        }
    }

    static void calc() {

        // 이때 모든 출루는 다 취소되므로 점수는 이닝마다 0으로 초기화 해서 계산함
        int now_player = 0; // 0번 플레이어 부터 시작함 -> playerQ의 인덱스가됨
        for (int i = 0; i < N; i++) { // 이닝 만큼 반복함
            int outCount = 0; // outCount가 9가 되면 다음 이닝을 시작함
            int base = -3; // base에서 부터 3명이 출루해야지 올라감

            while (outCount < 3) {
                int now_score = player[now_player][i];

                switch (now_score) {
                    case 0:
                        outCount++; // 아웃카운트 증가
                        break;
                    case 1: // 안타
                        break;
                    case 2: // 2루타
                        break;
                    case 3: // 3루
                        break;
                    case 4:
                        break;
                }

                now_player++; // 어떤 경우에도 한번 치면 선수 교체
                if(now_player == 9){
                    now_player = 0;
                }

            }

        }

    }


    static void makeScore(int i) {

        // 만약에 3루에 사람이 있으면 -> 점수 내고 한칸씩 땡김
        // 뒤에서 부터 땡겨야함. 3루 먼저 검사하는게 필수일 듯
        // 점수 내고 0 만들고 그다음에 뒤에서 부터 돌면서 댕기기
        // 사람이 들어오는 경우엔 사람을 넣어주고
        // 홈런의 경우 사람이 들어오지 않음! 그냥 전체 배열을 돌면서 확인

        // 만약에 사람이 없으면 (그냥 땡김)
        // 그걸 점수 만큼 반복
    }

}