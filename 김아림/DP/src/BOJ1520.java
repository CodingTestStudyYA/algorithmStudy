import java.util.*;
import java.io.*;


public class BOJ1520 {

    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] way;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];
        way = new int[M+1][N+1];

        // 입력받기
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                way[i][j] = -1; // 방문 X -> -1
            }
        }
        System.out.println(search(1, 1));
    }


    // BFS로 푸니까 답은 맞게 나오는데 메모리 초과
    // DFS로 푸니까 이번에 시간초과 나옴
    static int search(int y, int x) {

        int cur_height = map[y][x];


        // 여기가 최종보스니까 여기에 1을 줌
        if (y == M && x == N)
            return 1;

        // 만약에 이미 차있으면 그값을 추가로 더해줘야 하니까 해당 값 리턴
        if (way[y][x] != -1) {
            return way[y][x];
        }

        way[y][x] = 0; // search 돌면서 방문했으니까 방문했음 -> 0

        // 작다면 그 값의 그값의 전 ~ 이렇게 해서 오른쪽 끝까지 가는거
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (isAvailable(nextX, nextY, cur_height)) { // 범위 안에 있는지 체크 + 이전 것보다 다음것이 작다면? 지금까지 온 경우의 수들 더해줌
                way[y][x] += search(nextY, nextX); // 경로의 개수를 역으로 더해줌.. 맞잖아
            }
        }

        return way[y][x]; // 최종 리턴
    }

    static boolean isAvailable(int x, int y, int cur) {
        if (x >= 1 && x <= N && y >= 0 && y <= M) {
            if (map[y][x] < cur) return true; // 내리막길
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
