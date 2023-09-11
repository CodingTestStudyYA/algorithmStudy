import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14466 {

    static int N, K, R;
    // 목장의 크기, 소의 수

    // 인접하면 자유롭게 건너다닐 수 있지만, 일부는 길을 건너야함
    // 길이 있는 곳을 1로 잡고 표시 ?
    // 길을 리스트로 잡기
    // 소의 위치를 리스트로 잡으면서 bfs로 돌기. 이때 길이 있는 위치라면 건너지 않음
    // 그래서 만나는 소의 개수 체크
    // 만나지 못하는 소의 개수 -> 쌍

    static ArrayList<way> blocks = new ArrayList<>();
    static int[][] cows;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int size = 0;
        cows = new int[K][2]; // K번째 소의 위치
        // 목초지 길 표시 (행,열)(행,열)
        // 얘룰 그래프로 잡아서 union 시키면...? 부모 같을때 Nope
        // 근데 x,y좌표를 어떻게 그래프에서 한 노드로 잡고 하나

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            way w1 = new way(r1, c1);
            w1.block = (new int[]{r2, c2});
            blocks.add(w1);

            way w2 = new way(r2, c2);
            w2.block = new int[]{r1, c1};
            blocks.add(w2);
        }

        // 소의 위치 받기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }


        for (int i = 0; i < K - 1; i++) { // 마지막 소는 검사 안해도 됨
            BFS(i, cows[i][0], cows[i][1]);
        }

        System.out.println(ans);
    }

    static void BFS(int i, int y, int x) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N + 1][N + 1];
        boolean[][] cantWay = new boolean[N + 1][N + 1];

        q.offer(new int[]{y, x});
        visit[y][x] = true;
        cantWay[y][x] = true;
        // 총 K마리 소 중에서 자기 앞의 인덱스 소와, 본인을 제외해야함
        int count = K - 1 - i; // 만날 수 있는 소의 개수 -> 만날때 마다 --

        while (!q.isEmpty()) {
            // 꺼낸 애를 기준으로
            int[] cur = q.poll();

            // 못가는 곳 리스트 먼저 추출해야지
            // 리스트를 돌면서 확인한다
            ArrayList<int[]> block = new ArrayList<>();
            for (way w : blocks) {
                // 현재 블록을 기준으로 못가는 곳들
                if (w.y == cur[0] && w.x == cur[1]) {
                    cantWay[w.block[0]][w.block[1]] = true;
                    block.add(w.block);
                }
            }

            // 사방 탐색
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                // 갈 수 있음
                if (ny >= 1 && ny <= N && nx >= 1 && nx <= N) {

                    if (!visit[ny][nx] && !cantWay[ny][nx]) {
                        q.offer(new int[]{ny, nx}); // 큐에 넣어주고
                        visit[ny][nx] = true; // 방문 처리해주고
                        cantWay[ny][nx] = true; // 얘도 같이 해줘
                        // 소 검사 해주기 -> 쌍 찾는 거니까 자기 다음 인덱스부터 검사
                        for (int c = i + 1; c < K; c++) {
                            if (cows[c][0] == ny && cows[c][1] == nx) {
                                count--;
                                //count 0이 되면 바로 나가삼
                                if (count == 0) return;
                            }
                        }
                    }
                }

            }

            for (way w : blocks) {
                // 현재 블록을 기준으로 못가는 곳들
                if (w.y == cur[0] && w.x == cur[1]) {
                    cantWay[w.block[0]][w.block[1]] = false;
                }
            }
        }

        // 만나지 못하는 소의 개수 -> 쌍의 개수임
        ans += count;
    }


    static class way {
        int y, x;
        int[] block;

        way(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
