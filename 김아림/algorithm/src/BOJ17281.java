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
    static boolean[] base;
    static int ans = Integer.MIN_VALUE;
    static int score;

    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        player = new int[9][N];
        select = new boolean[9];
        playerQ = new int[9];

        base = new boolean[4]; // 타자, 1루 진출, 2루 진출, 3루 진출

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        // 4번 타자는 정해져 있으므로 4번 빼고 정해야함요

        makeQ(0);
        System.out.println(ans);

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
            base = new boolean[4];
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
            Arrays.fill(base, false); // 출루 X
            while (outCount < 3) {
                base[0] = true; // 타자 준비
                int now_score = player[playerQ[now_player]][i]; // 한 선수의 i번째 이닝때 점수

                switch (now_score) {
                    case 0:
                        outCount++; // 아웃카운트 증가
                        break;
                    case 1: // 안타
                        makeScore(1);
                        break;
                    case 2: // 2루타
                        makeScore(2);
                        break;
                    case 3: // 3루
                        makeScore(3);
                        break;
                    case 4:
                        for (int j = 0; j < 4; j++) {
                            if (base[j]) score++; // 각 베이스에 있던 선수들 모두 점수 얻음 + 타자
                        }
                        Arrays.fill(base, false); // 출루 X
                        base[0] = true; //
                        break;
                }

                now_player++; // 어떤 경우에도 한번 치면 선수 교체
                if (now_player == 9) {
                    now_player = 0;
                }

            }

        }

    }


    static void makeScore(int s) {

        for (int j = 0; j < s; j++) {
            // 0 은 타자 자리
            if (base[3]) {
                score++; // 점수 생성 및
            }

            // 한칸씩 땡기기

            base[3] = base[2]; // 2루의 선수가 3루로
            base[2] = base[1]; // 1루의 선수가 2루로
            base[1] = base[0];
            base[0] = false;
            // 만약에 3루에 사람이 있으면 -> 점수 내고 한칸씩 땡김
            // 뒤에서 부터 땡겨야함. 3루 먼저 검사하는게 필수일 듯
            // 점수 내고 0 만들고 그다음에 뒤에서 부터 돌면서 댕기기
            // 사람이 들어오는 경우엔 사람을 넣어주고
            // 홈런의 경우 사람이 들어오지 않음! 그냥 전체 배열을 돌면서 확인

            // 만약에 사람이 없으면 (그냥 땡김)
            // 그걸 점수 만큼 반복
        }

    }

}
