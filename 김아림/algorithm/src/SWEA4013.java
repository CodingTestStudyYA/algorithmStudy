import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA4013 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, K;
    static int[][] magnets; // N극이면 0, S극이면 1
    static Queue<int[]> operators;
    static StringBuilder sb = new StringBuilder();
    static int[] point;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            K = Integer.parseInt(br.readLine());
            magnets = new int[4][8]; // 4개의 자석, 8개의 날
            point = new int[4]; // 각 자석에서 화살표의 index를 담아둠
            operators = new ArrayDeque<>(); // 명령어 집어넣기
            StringTokenizer st;
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                point[i] = 0; // 맨 처음 주어질땐 화살표 Index가 맨 처음 인덱스
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                operators.offer(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
            }

            rotate();
            sb.append(calcScore()).append("\n"); // 여기 정답 넣기
//            System.out.println("다음 자석");
        }

        System.out.println(sb);
    }

    static void rotate() {

        while (!operators.isEmpty()) {
            int[] operator = operators.poll();
            int magnetId = operator[0]; // 자석 번호
            int dir = operator[1] * (-1); // 방향 1시계, -1반시계

            // 시계일 경우 1 아니면 -1 -> 이때 되돌아 와야하므로 나머지 연산...
            // 가운데 정점을 기준으로 +2, -2가 다른 자석과 닿아 있음
            // 돌리기 전에 쭉 검사해서 돌릴 수 있는 자석을 체크한다.


            if (magnetId - 1 >= 0) {
                spread(magnetId - 1, magnets[magnetId][(point[magnetId] + 6) % 8], dir * (-1), true); // 왼쪽에 자석이 있으면 퍼트리기
            }
            if (magnetId + 1 <= 3) {
                spread(magnetId + 1, magnets[magnetId][(point[magnetId] + 2) % 8], dir * (-1), false); // 오른쪽에 자석이 있으면 퍼트리기 - 현재 맞닿는 오른쪽 부분이 close
            }

            // 다 검사하고 난 후에 돌려야함 **
            point[magnetId] = (point[magnetId] + dir) % 8; // 해당 정점이 가운데임!
            if (point[magnetId] == -1) {
                point[magnetId] = 7;
            }
//            System.out.println((magnetId+1) + "번 자석 돌림 : 중간 index " + point[magnetId]);
//
//            System.out.println("------");
        }
    }

    // 검사하고 돌리는 함수
    static void spread(int magnetId, int close, int dir, boolean isLeft) { // close는 magentId 자석과 붙은 자석의 튀어나오는 축 의미

//        System.out.println(magnetId + "마그넷의 오른쪽 인덱스 : "+(point[magnetId] + 2) % 8);
//        System.out.println(magnetId + "마그넷의 왼쪽 인덱스 : "+(point[magnetId] + 6) % 8);

        if (isLeft) {
            if (magnets[magnetId][(point[magnetId] + 2) % 8] != close) { // close는 퍼트'린' 자석의 왼쪽부분이므로 자신의 오른쪽 축과 달라야함
//                System.out.println("왼쪽 전진");

                // 다음으로 퍼트리기
                if (magnetId - 1 >= 0) { // 현재 마그넷에서 왼쪽 마그넷이 있으면
                    spread(magnetId - 1, magnets[magnetId][(point[magnetId] + 6) % 8], dir * (-1), true); // 왼쪽에 자석이 있으면 퍼트리기
                }

                // 돌리기
                point[magnetId] = (point[magnetId] + dir) % 8;
                if (point[magnetId] == -1) {
                    point[magnetId] = 7;
                }
//                System.out.println((magnetId+1) + "번 자석 돌림 : 중간 index " + point[magnetId]);
            }
        }else {
            if (magnets[magnetId][(point[magnetId] + 6) % 8] != close) { // close는 돌아간 왼쪽 자석의 오른쪽 맞닿은 부분이므로 현재 자석의 왼쪽 맞닿은 축과 달라야함
//                System.out.println("오른쪽 진전");

                // 다음으로 퍼트리기
                if (magnetId + 1 <= 3) {
                    spread(magnetId + 1, magnets[magnetId][(point[magnetId] + 2) % 8], dir * (-1), false); // 왼쪽에 자석이 있으면 퍼트리기
                }

                // 돌리기
                point[magnetId] = (point[magnetId] + dir) % 8;
                if (point[magnetId] == -1) {
                    point[magnetId] = 7;
                }
//                System.out.println((magnetId+1) + "번 자석 돌림 : 중간 index " + point[magnetId]);

            }else {
//                System.out.println("같아서 돌아가지 않는 경우 -> 새 마그넷의 왼쪽 : " + magnets[magnetId][(point[magnetId] + 6) % 8] + ", 호출한 마그넷의 오른쪽 : " + close);
            }
        }


    }

    static int calcScore() {

        int score = 0;

        for (int i = 0; i < 4; i++) {
            if (magnets[i][point[i]] == 1) { // S극일 경우
                score += (int) Math.pow(2, i);
            }
        }
        return score;
    }
}
