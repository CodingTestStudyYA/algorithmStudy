import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA5653 {

    static int T, N, M, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] done;
    static PriorityQueue<Cell> cells;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static StringBuilder sb = new StringBuilder();
    static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            ans = 0;
            sb.append("#").append(test_case).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int MAX_N = N + K * 2 + 4;
            int MAX_M = M + K * 2 + 4;

            map = new int[MAX_N][MAX_M];
            visit = new boolean[MAX_N][MAX_M];
            done = new boolean[MAX_N][MAX_M];
            cells = new PriorityQueue<>(((o1, o2) -> o1.lifeTime - o2.lifeTime)); // 넣으면서 알아서 정렬되게 해야함 **

            for (int i = N / 2 - 1; i < N / 2 - 1 + N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = M / 2 - 1; j < M / 2 - 1 + M; j++) {
                    int X = Integer.parseInt(st.nextToken()); // 생명치를 의미함
                    if (X != 0) {
                        map[i][j] = X; // 지도에 생명치 넣어줘야함
                        cells.offer(new Cell(j, i, X, 0, 0, false)); // 세포는 리스트에 넣어줘야함
                    }
                }
            }

            check();
            ans = cells.size();
            sb.append(ans).append("\n");
        }

        System.out.println(sb);

    }


    static void check() {

        // 0. K시간 만큼 실행함
        for (int i = 0; i < K; i++) {

            int size = cells.size();

            // 1. 한시간 기준으로 진행 - size 만큼 진행한다.
            for (int t = 0; t < size; t++) {
                Cell cur = cells.poll(); // 2. 하나 꺼내서

                // 3. active 한지 체크한다
                // 3 - 1. 첫 한시간 동안만 번식하므로 그걸 체크해줘야함
                if (cur.isAcitve && !done[cur.y][cur.x]) {
                    // 4. 활성화 된 상태라면 주변에 번식해줘야함
                    for (int j = 0; j < 4; j++) {
                        int ny = cur.y + dy[j];
                        int nx = cur.x + dx[j];
                        if (visit[ny][nx]) continue; // 방문했다면 ㄴㄴ
                        cells.add(new Cell(ny, nx, cur.lifeTime, 0, 0, false));
                        map[ny][nx] = cur.lifeTime; // 생명치 값은 복사됨
                        visit[ny][nx] = true; // 방문 했다 ~
                        done[cur.y][cur.x] = true;
                    }
                }
                // 5. 한시간 지내기
                if (!oneYearCheck(cur)) cells.add(cur); // 안죽었으면 일단 다시 넣어줘야함 - 죽었으면 큐에서 제외
            }

        }

    }

    static boolean oneYearCheck(Cell cur) {

        if (!cur.isAcitve) { // 비활성화 상태라면
            cur.disAbleTime++; // 증가시키고
            if (cur.lifeTime == cur.disAbleTime) { // 같아지면
                cur.isAcitve = true; //활성화 상태로 변경해주기
                System.out.println(cur.x + "," +cur.y + " : 활성");
            }
        } else { // 활성화 상태인데 아직 죽지 않음
            cur.activeTime++;
            // 처리를 다 끝내고 나서
            if (cur.lifeTime == cur.activeTime) return false; // 죽음
        }

        return true; // 안죽음
    }


    static class Cell implements Comparator<Cell> {
        int y, x;  // 좌표
        int lifeTime; // X 입력값
        int disAbleTime; // 비활성화된지 얼마나 됨
        int activeTime; // 활성화 된지 얼마나 됨
        boolean isAcitve; // true 이면 활성화, false이면 비활성화

        public Cell(int y, int x, int lifeTime, int disAbleTime, int activeTime, boolean isAcitve) {
            this.y = y;
            this.x = x;
            this.lifeTime = lifeTime;
            this.disAbleTime = disAbleTime;
            this.activeTime = activeTime;
            this.isAcitve = isAcitve;
        }

        @Override
        public int compare(Cell o1, Cell o2) {
            return o1.lifeTime - o2.activeTime;
        }
    }
}
