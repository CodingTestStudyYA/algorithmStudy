
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144 {

    static int R, C, T; // R * C 크기, T초

    static int[][] status;
    static int[][] copied;

    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    // 미세 먼지 담아두기
    static Queue<int[]> q = new ArrayDeque<>();

    // 공기 청정기의 위치
    static int[][] cleaner;

    // 결과 
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        status = new int[R][C];
        copied = new int[R][C];
        cleaner = new int[2][2];
        int idx = 0; // cleaner 가 차지하는 칸이 2칸이니까..

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());
                if (status[i][j] == -1) {
                    cleaner[idx][0] = i;
                    cleaner[idx][1] = j;
                    idx++;
                } else if (status[i][j] != 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        for (int t = 1; t <= T; t++) {
//            System.out.println(" -- 미세먼지 확산 -- ");
            spread();
//            for(int i = 0; i < R; i++) {
//                for(int j = 0; j < C; j++) {
//                    System.out.print(status[i][j] + " ");
//
//                }
//                System.out.println();
//            }
//            System.out.println(" -- 청소하기  -- ");
            clean();
//            for(int i = 0; i < R; i++) {
//                for(int j = 0; j < C; j++) {
//                    System.out.print(status[i][j] + " ");
//
//                }
//                System.out.println();
//            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (status[i][j] > 0) {
                    answer += status[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    // 미세먼지 확산 -> 모든 칸에서 동시에 일어남 (1회)
    // amount -> 확산되는 양 (소수점은 버림)
    // 방향 칸의 개수 세서 그만큼 빼야함 count
    static void spread() {

        // 초기화 한거
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copied[i][j] = 0;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copied[i][j] += status[i][j];
                BFS(i, j);

            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                status[i][j] = copied[i][j];
            }
        }
    }

    static void BFS(int y, int x) {

        int amount = status[y][x] / 5; // 확산되는 양

        if (amount <= 0) return;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C)
                continue;

            if (status[ny][nx] != -1) {
                copied[ny][nx] += amount;
                copied[y][x] -= amount;
            }
        }

    }

    // 공기 청정기 작동 코드
    // 위 공기 청정기는 반시계, 아래 공기 청전기는 시계 방향
    // 바람의 방향대로 한칸씩 이동 (공기 청정기 위치로 오게되면 없어짐)
    static void clean() {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copied[i][j] = 0;
            }
        }

        for (int i = 0; i < 2; i++) {
            int ny = cleaner[i][0];
            int nx = cleaner[i][1] + 1; // 공기청정기 x 다음 위치

            // 우
            while (nx < C - 1) {
                copied[ny][nx + 1] = status[ny][nx];
                nx++;
            }

            // 위, 아래
            if (i == 0) { // 위쪽 청정기
                while (ny > 0) {
                    copied[ny - 1][nx] = status[ny][nx];
                    ny--;
                }
            } else { // 아래쪽 청정기
                while (ny < R - 1) {
                    copied[ny + 1][nx] = status[ny][nx];
                    ny++;
                }
            }

            //좌
            while (nx > 0) {
                copied[ny][nx - 1] = status[ny][nx];
                nx--;
            }

            // 위, 아래
            if (i == 0) { // 위쪽 청정기
                while (ny < cleaner[i][0] - 1) { // 내려올 때 위쪽 공기청정기의 y 좌표 하나 이전
                    copied[ny + 1][nx] = status[ny][nx];
                    ny++;
                }
            } else { // 아래쪽 청정기
                while (ny > cleaner[i][0] + 1) {
                    copied[ny - 1][nx] = status[ny][nx];
                    ny--;
                }
            }
        }

        // 변경한 것들만 반영
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 || i == R - 1 || j == 0 || j == C - 1 || i == cleaner[0][0] || i == cleaner[1][0])
                    status[i][j] = copied[i][j];
            }
        }

    }
}