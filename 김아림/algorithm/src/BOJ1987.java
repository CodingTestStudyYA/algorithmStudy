import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int R, C;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[] check = new boolean[26]; // 26개의 알파벳을 두고 방문했는지 체크
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        /* 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
        말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
        즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
        좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다. */

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        check[map[0][0] - 'A'] = true;
        DFS(0, 0, 1);
        System.out.println(ans);
    }

    static void DFS(int y, int x, int count) {

        ans = Math.max(ans, count);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
                if (!check[map[ny][nx] - 'A']) {
                    check[map[ny][nx] - 'A'] = true;
                    DFS(ny, nx, count + 1);
                    check[map[ny][nx] - 'A'] = false;

                }

            }
        }
    }


}
