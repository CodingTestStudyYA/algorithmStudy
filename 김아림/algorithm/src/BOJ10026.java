import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ10026 {

    static int N;
    static char[][] map;

    static boolean[][] visit_non;
    static Queue<int[]> q_non = new ArrayDeque<>();

    static boolean[][] visit_S;
    static Queue<int[]> q_s = new ArrayDeque<>();

    static int[] dy = { 0, 0, 1, -1 };
    static int[] dx = { 1, -1, 0, 0 };

    static int nonCount = 0;
    static int SCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visit_non = new boolean[N][N];
        visit_S = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit_non[i][j]) {
                    nonCount++;
                    visit_non[i][j] = true;
                    q_non.offer(new int[] { i, j });
                    BFS(map[i][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit_S[i][j]) {
                    SCount++;
                    visit_S[i][j] = true;
                    q_s.offer(new int[] { i, j });
                    BFS_s(map[i][j]);
                }
            }
        }

        System.out.println(nonCount + " " + SCount);

    }

    static void BFS(char color) {

        while (!q_non.isEmpty()) {

            int[] cur = q_non.poll();
            int curY = cur[0];
            int curX = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N)
                    continue;

                if (map[ny][nx] == color && !visit_non[ny][nx]) {
                    q_non.offer(new int[] { ny, nx });
                    visit_non[ny][nx] = true;
                }
            }

        }

    }

    static void BFS_s(char color) {

        while (!q_s.isEmpty()) {

            int[] cur = q_s.poll();
            int curY = cur[0];
            int curX = cur[1];

            for (int i = 0; i < 4; i++) {

                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N)
                    continue;

                switch (color) {
                    case 'R':
                        if (map[ny][nx] == 'B')
                            continue;
                        break;
                    case 'G':
                        if (map[ny][nx] == 'B')
                            continue;
                        break;
                    case 'B':
                        if (map[ny][nx] != 'B')
                            continue;
                        break;

                }

                if (!visit_S[ny][nx]) {
                    q_s.offer(new int[] { ny, nx });
                    visit_S[ny][nx] = true;
                }

            }

        }

    }

}