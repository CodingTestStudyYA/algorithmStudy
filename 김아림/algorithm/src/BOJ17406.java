import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17406 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static int[][] map;
    static int[][] ops;
    static boolean[] visit;
    static int[] tgt;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[K];
        tgt = new int[K];
        ops = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            // 범위 0부터 시작하니까 걍 하나씩 빼줘
            ops[i][0] = Integer.parseInt(st.nextToken()) -1 ; // r
            ops[i][1] = Integer.parseInt(st.nextToken()) -1 ; // c
            ops[i][2] = Integer.parseInt(st.nextToken()) ; // s

        }
        // 각 행에 있는 모든 수의 합 중 최소값 => map의 값임
        // 행을 더하려면 y는 고정이고 x를 돌아서

        // 회전 연산
        // 배열 돌릴때는 범위 존재함요
        // 연산 수행 순서 중요 -> 값이 달라짐
        // 순서는 마음대로 해도 되고 그 중에서 최소가 나오면 되는데 이때 A의 최소를 구하는 것임

        perm(0);
        System.out.println(ans);
    }

    // 일단 순서를 모두 나열해서 해봐야할 거 같음.. -> 순열
    static void perm(int idx) {
        if (idx == K) {
            // 돌리기
            rotate(tgt);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visit[i]) continue;

            tgt[idx] = i; // 명령어의 인덱스를 넣어줌
            visit[i] = true;
            perm(idx + 1);
            visit[i] = false;
        }
    }

    public static void rotate(int[] permm) {

        int[][] copyed = new int[N][M]; // 결과가 나오는 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyed[i][j] = map[i][j];
            }
        }

        for (int k = 0; k < K; k++) {
            int r = ops[permm[k]][0];
            int c = ops[permm[k]][1];
            int S = ops[permm[k]][2];

            for (int s = 1; s <= S; s++) {
                //위
                int temp0 = copyed[r - s][c + s];
                for (int y = c + s; y > c - s; y--) {
                    copyed[r - s][y] = copyed[r - s][y - 1];
                }
                //오른쪽
                int temp1 = copyed[r + s][c + s];
                for (int x = r + s; x > r - s; x--) {
                    copyed[x][c + s] = copyed[x - 1][c + s];
                }
                copyed[r - s + 1][c + s] = temp0;
                //아래
                int temp2 = copyed[r + s][c - s];
                for (int y = c - s; y < c + s; y++) {
                    copyed[r + s][y] = copyed[r + s][y + 1];
                }
                copyed[r + s][c + s - 1] = temp1;
                //왼쪽
                for (int x = r - s; x < r + s; x++) {
                    copyed[x][c - s] = copyed[x + 1][c - s];
                }
                copyed[r + s - 1][c - s] = temp2;
            }
        }

        calc(copyed);
    }

    static void calc(int[][] copyedMap) {

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += copyedMap[i][j];
            }
            ans = Math.min(ans, sum);
        }
    }
}
