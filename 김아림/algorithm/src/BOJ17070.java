import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

    //아래, 우, 우하
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int N;
    static int[][] map;
    static int ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 파이프의 끝지점을 기준으로 생각하기
        // 대각선으로 내려가는 경우엔 해당 자리 + 왼쪽과 위쪽도 모두 벽이면 안됨 !
        // 방법의 개수구하는 것이므로 ++ 시키는 것 필요함

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(1, 2, 1); // 시작점 넣어주기
        System.out.println(ans);
    }


    static void DFS(int y, int x, int dir ) {

        if (y == N && x == N) {
            ans++;
            return;
        }

        // 세로, 우, 우하
        for (int i = 0; i < 3; i++) {
            if (dir == 1) { // 현재 가로를 봄
                if (i == 0) continue; // 현재 가로를 보는 경우엔 아래로 갈 수 없음
            } else if (dir == 0) { // 현재 세로를 봄
                if (i == 1) continue; // 현재 세로를 보는 경우 가로로 갈 수 없음
            } // 우하는 어디로든 가는 거 가능 ㅇㅇ

            // 다음 좌표
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 만약에 우하로 내려가는 경우
            if (i == 2) {
                // 가는 좌표, 가는 좌표의 위, 가는 좌표의 왼쪽이 모두 벽이 아니어야 한다.
                if (isAvailable(ny, nx) && isAvailable(ny - 1, nx) && isAvailable(ny, nx - 1)) {
                    DFS(ny, nx, 2);
                }
            }else{
                if (isAvailable(ny, nx)) { // 우하로 내려가지 않는 경우 체크
                    DFS(ny, nx, i);
                }
            }
        }
    }


    static boolean isAvailable(int ny, int nx) {
        if (ny <= N && ny >= 1 && nx <= N && nx >= 1) {
            if (map[ny][nx] == 0) // 빈칸인 경우
                return true;
        }
        return false;
    }
}
