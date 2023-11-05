import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2589_ver2 {
    // 보물섬
    static int W, H;
    static char[][] map;
    static int[][] distance;
    static int max = Integer.MIN_VALUE;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 모든 육지에서 다른 육지로 가는 시간
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'L') {
                    list.add(new int[]{i, j});
                }
            }
        }

        for(int[] L : list){
            bfs(L[0] , L[1]); // 모든 육지 검사 하기
//            System.out.println();
        }

        System.out.println(max);
    }

    static void bfs(int y, int x) {

        boolean[][] visit = new boolean[H][W];
        Queue<int[]> q = new ArrayDeque<>();
        distance = new int[H][W];
        q.offer(new int[]{y, x});
        visit[y][x] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[1];
            int cy = cur[0];
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                // 범위 체크
                if(ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                // 방문 체크 / 육지 체크
                if(visit[ny][nx] || map[ny][nx] == 'W') continue;

                // 큐에 넣기
                q.offer(new int[]{ny, nx});
                visit[ny][nx] = true;

                // 자리 체크
                distance[ny][nx] = distance[cy][cx] + 1;

                // 제일 긴 거리 검사하기
                max = Math.max(distance[ny][nx], max);
            }
        }

//        for(int i = 0; i < H; i++){
//            for(int j = 0; j < W; j++){
//                System.out.print(distance[i][j]);
//            }
//            System.out.println();
//        }
    }
}
