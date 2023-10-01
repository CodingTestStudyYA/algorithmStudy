
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14466_2nd {

    static int N, K, R;
    static boolean[][] visit;
    static int [][] cows;
    static ArrayList<int[]>[][] line;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        cows = new int[K][2];
        line = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                line[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            line[x1][y1].add(new int[] {x2,y2});
            line[x2][y2].add(new int[] {x1,y1});
            // 양방향으로 넣어주라
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            cows[i] = new int[] {x,y};
        }

        System.out.println(find());
    }

    private static int find() {

        int answer = 0;
        for (int c = 0; c < K; c++) {
            visit = new boolean[N][N]; // 새로 만들어 줘야함
            dfs(cows[c][0], cows[c][1]); // 얘를 가지고 dfs 돌아

            for (int nc = c; nc < K; nc++) { // 얘를 중심으로 다른 소까지 체크해야됨 (쌍 찾기)
                int[] cow = cows[nc]; // 여기서 넣어주라고 ~
                if (!visit[cow[0]][cow[1]])
                    answer++; // 정답 추가
            }

        }

        return answer;
    }


    // dfs 로 돌아
    private static void dfs(int x, int y) {

        visit[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visit[nx][ny]) continue;

            boolean flag = true;
            for(int[] n : line[x][y]){
                if(n[0] == nx && n[1] == ny) {
                    flag = false;
                }
            }
            if(!flag) continue;

            dfs(nx, ny);
        }

    }

}