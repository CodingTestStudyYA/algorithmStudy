import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1873 {

    static int T, H, W, N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static Queue<Character> q = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    // 동 서 남 북
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int dir = 0;
    static int y, x; // 초기 위치

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                map[i] = str.toCharArray();
            }


            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '^') {
                        dir = 3;
                        y = i;
                        x = j;
                        break;
                    } else if (map[i][j] == 'v') {
                        dir = 2;
                        y = i;
                        x = j;
                        break;
                    } else if (map[i][j] == '<') {
                        dir = 1;
                        y = i;
                        x = j;
                        break;
                    } else if (map[i][j] == '>') {
                        dir = 0;
                        y = i;
                        x = j;
                        break;
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            String ops = br.readLine();

            // 명령어 넣기
            for (int i = 0; i < ops.length(); i++) {
                q.offer(ops.charAt(i));
            }

            game();

            System.out.print("#" + test_case + " ");

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

        }

    }

    static void game() { // 전차의 현재 위치
        int ny = 0;
        int nx = 0;

        while (!q.isEmpty()) {
            switch (q.poll()) {
                case 'U':
                    // 전차가 바라보는 방향을 위로 바꾸고, 위가 평지라면 그칸으로 이동
                    dir = 3;
                    map[y][x] = '^'; // 제자리에서 방향 돌리기
                    ny = y + dy[dir]; // 이동 체크
                    nx = x + dx[dir]; // 이동 체크
                    if (isAvailable(ny, nx) && map[ny][nx] == '.') { // 이동 체크
                        map[y][x] = '.';  // 지나간 곳은 다시평지임
                        y = ny; // 이동
                        x = nx; // 이동
                        map[y][x] = '^'; // 위치 이동
                    }
                    break;
                case 'D':
                    dir = 2;
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                    map[y][x] = 'v';
                    if (isAvailable(ny, nx) && map[ny][nx] == '.') {
                        map[y][x] = '.';  // 지나간 곳은 다시평지임
                        y = ny;
                        x = nx;
                        map[y][x] = 'v'; // 위치 이동
                    }
                    break;
                case 'L':
                    dir = 1;
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                    map[y][x] = '<';
                    if (isAvailable(ny, nx) && map[ny][nx] == '.') {
                        map[y][x] = '.';  // 지나간 곳은 다시평지임
                        y = ny;
                        x = nx;
                        map[y][x] = '<'; // 위치 이동
                    }
                    break;
                case 'R':
                    dir = 0;
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                    map[y][x] = '>';
                    if (isAvailable(ny, nx) && map[ny][nx] == '.') { // 갈 수 있음
                        map[y][x] = '.';  // 지나간 곳은 다시평지임
                        y = ny; // 지나감
                        x = nx; // 지나감
                        map[y][x] = '>'; // 위치 이동
                    }
                    break;
                case 'S':
                    ny = y + dy[dir]; // dir은 바뀌지 않음(이전 바라보고 있던 방향 그대로 사용함)
                    nx = x + dx[dir];
                    while (isAvailable(ny, nx)) { // 범위 안이라면
                        if (map[ny][nx] == '*') {
                            map[ny][nx] = '.';
                            break; // 벽이면 평지로 바꾸고 멈춰
                        } else if (map[ny][nx] == '#') {
                            break; // 벽돌벽이면 그냥 멈춰
                        } else {
                            // 해당 경우가 아닐 경우 계속 전진함
                            ny = ny + dy[dir];
                            nx = nx + dx[dir];
                        }
                    }
                    break;
            }

        }
    }


    static boolean isAvailable(int y, int x) { // 범위 안에 들어가는지 아닌지 체크
        if (y >= 0 && y < H && x >= 0 && x < W) {
            return true;
        }
        return false;
    }
}