import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941 {


    // 처음엔 조합없이 DFS로 접근했는데 너무 어렵... 카운팅에서 헤맴
    // 조합 + BFS
    // 이 문제에서 단순화 핵심은 2차원 배열의 visit -> 1차원으로 표현하는 것

    static int ans = 0;
    static int[] tgt;
    static char map[][];
    static boolean visit[];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        for (int i = 0; i < 5; i++)
            map[i] = br.readLine().toCharArray();


        visit = new boolean[25];
        tgt = new int[7];

        comb(0, 0, 0);

        System.out.println(ans);
    }

    public static void comb(int idx, int cnt, int cntS) {
        if (cnt == 7) {
            if (cntS >= 4) { // 조건 확인하기
                if (BFS()) // 조합의 친구들이 모두 인접했다는 뜻임
                    ans++;
                return;
            }
            return;
        }

        for (int i = idx; i < 25; i++) {
            visit[i] = true;
            tgt[cnt] = i;
            // '솜'파일 경우,
            // 1차원 배열로 표현할때 행, 열표현은 / and %
            if (map[i / 5][i % 5] == 'S') comb(i + 1, cnt + 1, cntS + 1);
            else comb(i + 1, cnt + 1, cntS);
            visit[i] = false;
        }
    }

    public static boolean BFS() {
        int cnt = 1;
        boolean[] visit_bfs = new boolean[25];
        q = new LinkedList<>();
        q.add(tgt[0]);
        while (!q.isEmpty()) {
            int cur = q.poll();
            visit_bfs[cur] = true;

            for (int d = 0; d < 4; d++) {
                int nx = (cur / 5) + dx[d];
                int ny = (cur % 5) + dy[d];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                if (visit_bfs[nx * 5 + ny]) continue; // BFS돌았으면 패스염
                if (!visit[nx * 5 + ny]) continue; // tgt 의 친구

                cnt++;
                visit_bfs[nx * 5 + ny] = true;
                q.add(nx * 5 + ny);
            }
        }

        if (cnt == 7) return true; // 모두 인접했는지 체크 -> 뭔가 게리맨더링.. 하여튼 개수 체크 이걸로함
        else return false;
    }
}
