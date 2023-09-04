import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2636 {

    static int H, W;
    static int[][] cheese;
    static Queue<int[]> q;
    static Queue<int[]> c_Cheese;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int time = 0;
    static int cheeseCnt = 0;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        cheese = new int[H][W];
        q = new ArrayDeque<>();
        c_Cheese = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                if(cheese[i][j] == 1) {
                    q.add(new int[]{i, j});
                    cheeseCnt++;
                }
            }
        }

        // 치즈 리스트에서 하나씩 뽑으면서 해당 좌표로 시작하는 BFS 돌기
        // BFS 돌면서 벽을 마주하면 해당 좌표는 0으로 만들기
        list.add(cheeseCnt);
        BFS();
        System.out.print(time + " ");
        if(list.size() == 1 || list.size() == 0) System.out.println(list.get(0));
        else System.out.println(list.get(list.size() -2));

    }

    // 사방탐색하면서 0이 하나라도 있으면 죽이기 -> -1로 만들기 -> 이후 -1의 값을 다시 0으로 만들어줌
    static void BFS() {

        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            cheeseCnt = 0;
            for (int i = 0; i < size; i++) {

                // 하나의 치즈를 빼서
                int[] cur = q.poll();
                Queue<int[]> way = new ArrayDeque<>();
                boolean[][] visit = new boolean[H][W];
                way.add(new int[]{cur[0], cur[1]});
                visit[cur[0]][cur[1]] = true;

                // BFS 돌기
                while (!way.isEmpty()) {

                    int[] cur_way = way.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur_way[0] + dy[d];
                        int nx = cur_way[1] + dx[d];

                        if (ny == 0 || ny == H - 1 || nx == 0 || nx == W - 1) {
                            c_Cheese.offer(new int[]{cur[0], cur[1]});
                            break;
                        }
                        if (ny >= 0 && ny < H && nx >= 0 && nx < W && cheese[ny][nx] == 0 && !visit[ny][nx]) {
                            visit[ny][nx] = true;
                            way.offer(new int[]{ny, nx});
                        }
                    }
                }
            }

            // 공기에 노출되었던 치즈들 자리 전부 0으로 만들기 (치즈녹음)
            while (!c_Cheese.isEmpty()) {
                int[] cur_cheese = c_Cheese.poll();
                cheese[cur_cheese[0]][cur_cheese[1]] = 0;
            }

            // 남은 치즈 큐에 넣어주기
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (cheese[i][j] == 1) {
                        q.add(new int[]{i, j});
                        cheeseCnt++;
                    }
                }
            }

            list.add(cheeseCnt);
        }

    }
}
