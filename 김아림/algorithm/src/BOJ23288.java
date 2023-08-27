// BOJ - 주사위굴리기2(23288번)
// 구현 + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 보고 있는 방향 저장을 위해서 dir 필요
// 주사위 전재도 저장을 위해서 배열 만들어야함 -> 순서대로 0 ~ 6 해서 위치에 따라 지금 갖고 있는 수
// 돌리는거 어케하니
//
public class BOJ23288 {

    static int N, M, K;
    static int[] dice = {1, 2, 3, 4, 5, 6};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static int dir;
    static int score = 0;
    static int canGoCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1]; // (1,1) 부터 시작함

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // map을 기준으로 동쪽은 아래로, 남쪽은 오른쪽으로 가는 것임
        dir = 0; // 지금 동쪽으로 시작

        // 시작할때 내가 있는 위치가 (1,1)임
        int ny = 1;
        int nx = 1;

        for(int i = 0; i < K; i++){
            nx += dx[dir];
            ny += dy[dir];

            // 가로 막혀 있으면 뒤로 후진한뒤에 원래 이동한 방향에서 반대로 전환해서 다시 이동해야함
            // 이때는 다시 검사해줄 필요 없음 (무조건 갈 수 있음)
            if (nx < 1 || nx > N || ny < 1 || ny > M) {
                nx -= dx[dir];
                ny -= dy[dir];
                dir = (dir + 2) % 4; // 반대방향 가기
                nx += dx[dir];
                ny += dy[dir];
            }

            // 돌리기
            diceRolling_map();
            int canGoCnt = 0;
            check(nx, ny);

            // 이동할 수 있는 칸의 개수  * 그 칸의 점수를 얻음

            score += (canGoCnt * map[nx][ny]);

            // 아랫면이랑 지금 칸의 점수 비교해서 방향 정해야함
            if (dice[5] > map[nx][ny]) {
                dir = (dir + 1) % 4; // 만약 아랫면정수A가 더 크면 시계방향
            } else if (dice[5] < map[nx][ny]) {
                if (dir == 0) dir = 3;
                else dir = dir - 1; // 작으면 반시계 방향임
            }
        }

        System.out.println(score);
    }

    static void diceRolling_map() {
        if (dir == 0) {
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;

        } else if (dir == 1) {
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        } else if (dir == 2) {
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        } else if (dir == 3) {
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }

    // 아랫면을 줌
    static void check(int x, int y) {

        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        // 동 서 남 북 방향 연속해서 이동할 수 있는 칸의 수를 구해야함

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                // 주위 탐색해야함
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (1 <= nx && nx <= N && 1 <= ny && ny <= M) {
                    if (!visited[nx][ny] && map[nx][ny] == map[x][y]) {
                        visited[nx][ny] = true;
                        canGoCnt++;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}