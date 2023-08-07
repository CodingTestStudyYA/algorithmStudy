package DFS_BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7562 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, I, count ;
    static Point now, dest;
    static Deque<Point> q;
    static boolean[][] isVisited;
    static StringBuilder sb = new StringBuilder();
    static int[][] cnt;
    static int[] dx = { 1, 2, -1, -2, 2, 1, -1, -2 };
    static int[] dy = { 2, 1, 2, 1, -1, -2, -2, -1 };

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            q = new ArrayDeque<Point>();
            count = 0;
            I = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            now = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            dest = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            isVisited = new boolean[I][I];
            cnt = new int[I][I];
            q.add(now);
            isVisited[now.x][now.y] = true;
            goStep();

            sb.append(cnt[dest.x][dest.y]+ "\n");
        }
        System.out.println(sb);

    }



    static void goStep() {


        while(!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x;
            int y= cur.y;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isAvailalbe(nx, ny)) {
                    q.add(new Point(nx, ny));
                    isVisited[nx][ny] = true;
                    cnt[nx][ny] = cnt[x][y] + 1;

                }

            }
        }

    }

    static boolean isAvailalbe(int x, int y) {
        if (x >= 0 && x < I && y >= 0 && y < I){
            if(!isVisited[x][y]) return true;

        }
        return false;
    }

}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}