import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {

    static char[][] map;
    static int H, W;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        // 육지로만 이동 가능
        // 한칸 이동에 한 시간이 걸림
        // 보물은 최단거리 이동에 있어서 가장 긴 시간이 걸리는 육지 두 곳에
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L')
                    BFS(i, j);
            }
        }

        System.out.println(answer);
    }


    static void BFS(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visit = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                visit[i][j] = -1; // 방문 안함
            }
        }
        q.offer(new int[]{y, x});
        visit[y][x] = 0; // 방문함을 의미함 -> +1 해주면서 최단거리 기록하기

        while (!q.isEmpty()) {
            int curY = q.peek()[0];
            int curX = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int ny = curY + dy[d];
                int nx = curX + dx[d];
                if (isAvailable(ny, nx, visit)) {
                    q.offer(new int[]{ny, nx});
                    visit[ny][nx] = visit[curY][curX] + 1;
                    answer = Math.max(answer, visit[ny][nx]);
                }
            }
        }
    }

    static boolean isAvailable(int y, int x, int[][] visit) {

        if (y >= 0 && y < H && x >= 0 && x < W) {
            if ((visit[y][x] == -1) && (map[y][x] == 'L')) return true;
        }
        return false;
    }

}
