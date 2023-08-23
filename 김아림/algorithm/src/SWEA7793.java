import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7793 {

    static int T, N, M;
    static char[][] matrix;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] dest; // 여신의 공간
    static int[] start; // 수연이 위치
    static int[] devil; // 악마의 공간

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    // 악마의 손아귀 이동을 위한 큐와 visit
    static Queue<int[]> q_forDevil = new ArrayDeque<>();
    static boolean[][] visit_devil;
    static boolean flag;

    // 수연이의 이동을 위한 큐와 visit
    static Queue<int[]> q_forS = new ArrayDeque<>();
    static boolean[][] visit_S;

    // 최단거리 (정답)
    static int count;

    // 1초에 동서남북 인접한 칸으로
    // 돌이 있는 위치 안됨
    // 악마의 손아귀는 돌이있는 곳에 확장 불가
    // 최소시간 이동

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            count = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            flag = false;

            matrix = new char[N][M];
            visit_devil = new boolean[N][M];
            visit_S = new boolean[N][M];

            q_forDevil = new ArrayDeque<>();
            q_forS = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = str.charAt(j);
                    if (matrix[i][j] == 'S') {
                        start = new int[]{i, j};
                    } else if (matrix[i][j] == 'D') {
                        dest = new int[]{i, j};
                    } else if (matrix[i][j] == '*') {
                        q_forDevil.add(new int[]{i, j});
                        visit_devil[i][j] = true;

                    }
                }
            }

            q_forS.add(start);
            visit_S[start[0]][start[1]] = true;
            doGame();

            sb.append("#").append(test_case).append(" ");
            if (flag)
                sb.append(count).append("\n");
            else
                sb.append("GAME OVER").append("\n");

        }

        System.out.println(sb);
    }

    static void doGame() {

        int ans = 0;

        while (!q_forS.isEmpty()) {

            devilSpread();

            int size = q_forS.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q_forS.poll();

                for (int j = 0; j < 4; j++) {

                    int ny = cur[0] + dy[j];
                    int nx = cur[1] + dx[j];

                    if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                        if (matrix[ny][nx] == 'D') {
                            // 여신님 찾았음 ~
                            count = ans+1;
                            flag = true;
                            return;
                        } else if (matrix[ny][nx] == '.' && !visit_S[ny][nx]) {
                            matrix[ny][nx] = 'S';
                            q_forS.offer(new int[]{ny, nx});
                            visit_S[ny][nx] = true;
                        }

                    }

                }
            }

            ans++;
        }

    }

    static void devilSpread() {

        int size = q_forDevil.size();

        for (int i = 0; i < size; i++) {
            int[] cur = q_forDevil.poll();
            int y = cur[0];
            int x = cur[1];

            for (int j = 0; j < 4; j++) {

                int ny = y + dy[j];
                int nx = x + dx[j];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (matrix[ny][nx] == '.' || matrix[ny][nx] == 'S') {
                        matrix[ny][nx] = '*'; // 바꿔주고
                        q_forDevil.offer(new int[]{ny, nx});
                        visit_devil[ny][nx] = true;
                    }

                }

            }
        }

    }

}