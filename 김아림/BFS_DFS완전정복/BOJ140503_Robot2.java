package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ140503_Robot2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M; // N*M사이즈
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int[][] map ;
    static int dir;
    static int cnt;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(y,x);
        // 입력받기
        System.out.println(cnt);
    }

    static void DFS(int y, int x){

        if(map[y][x] == 1) { // 벽이라 못감요
            return;
        }

        if(map[y][x] == 0){ // 청소 되지 않은곳?
            map[y][x] = -1; // 청소했다는 표시
            cnt++; // 청소횟수 증가시키기
        }

        for(int i = 0; i<4; i++) {
            if (dir == 0) dir = 3;
            else dir = (dir - 1) % 4; // 방향 돌려가면서 탐색해야함 ***
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            // 모두 청소할 수 없는 경우를 구해줘야함 -> 후진하러 가기
            // 청소되지 않은곳 돌면서 발견하면 바로 가야함여
            if (isAvailable(y, x) && (map[ny][nx] == 0)) {
                DFS(ny, nx);
                break; // 나가줘야함 (그방향으로 전진하고 나머지는 필요없으므로)
            }

            // 마지막까지 break되지 않고 검사 되었다는 의미이므로
            if (i == 3) {
                rearCheck(y, x);
            }
        }
        // 후진하기
    }

    // 범위 체크
    static boolean isAvailable(int y, int x){
        if(x >= 0 && x <M && y >=0 && y <N) return true;
        return false;
    }

    static void rearCheck(int y, int x){
        int ny = y + dy[(dir+2)%4];
        int nx = x + dx[(dir+2)%4]; // 뒤로가기이므로 (이때 바라보는 방향 바뀌면 안됨 유의)
        if(isAvailable(ny,nx)){
            DFS(ny, nx);
        }
    }

    static class Point{
        int x,y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


}
