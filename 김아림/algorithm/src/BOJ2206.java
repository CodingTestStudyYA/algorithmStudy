import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    static int N, M;
    static int map[][];

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> q = new ArrayDeque<>();
    static boolean[][][] visit; // 벽 부수지 않은거면 0. 부수면 1
    static int[][] cost;
    static boolean success;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cost = new int[N][M];    // 가면서 거리 더해주기
        visit = new boolean[2][N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        q.offer(new int[]{0, 0, 0});


        if(N == 1 && M == 1){
            System.out.println(-1);
            System.exit(0);
        }

        BFS();

        if (!success)
            System.out.print(-1);
    }

    static void BFS() {
        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    // 다음 칸에 벽이 있을 때 -> (1) 벽을 부순적이 있는지 체크
                    //                        (2) 그 벽을 방문한적이 있는지 체크
                    if (map[ny][nx] == '1') { // 벽임
                        if (cur[2] == 0 && !visit[1][ny][nx]) { // 부신적 없는 경우 -> 다음 벽 부시기 가능
                            visit[cur[2]][ny][nx]  = true; // 벽 부수고 true
                            cost[ny][nx] = cost[cur[0]][cur[1]] + 1;
                            q.offer(new int[]{ny, nx, 1});
                        }
                    } else { // 벽 아님
                        if (!visit[cur[2]][ny][nx]) {
                            visit[cur[2]][ny][nx] = true;
                            cost[ny][nx] = cost[cur[0]][cur[1]] + 1;
                            q.offer(new int[]{ny, nx, cur[2]});
                        }
                    }

                    if (nx == M - 1 && ny == N - 1) {
                        success = true;
                        System.out.print(cost[ny][nx] + 1);
                    }
                }
            }


        }
    }

}

