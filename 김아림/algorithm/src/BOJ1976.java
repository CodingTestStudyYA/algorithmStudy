import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {

    static int N, M;
    static int[] graph;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // make
        graph = new int[N + 1];
        make();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken())== 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            int to = Integer.parseInt(st.nextToken());
            if (start != find(to)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static void make() {
        for (int i = 1; i <= N; i++) {
            graph[i] = i;
        }
    }

    static int find(int x) {
        if (x == graph[x]) {
            return x;
        }

        return graph[x] = find(graph[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        graph[y] = x;
    }
}
