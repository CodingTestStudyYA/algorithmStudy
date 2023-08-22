import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5653_v2 {

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static int R;
    static int C;
    static int K;

    static boolean[][] check;

    static PriorityQueue<Pos> pq;
    static Queue<Pos> temp;

    static class Pos implements Comparable<Pos> {
        int i, j, originalLife, curLife;

        Pos(int i, int j, int o, int c) {
            this.i = i;
            this.j = j;
            originalLife = o; // 생명력
            curLife = c; // 현재 생명
        }

        @Override
        public int compareTo(Pos o) {
            return -Integer.compare(originalLife, o.originalLife);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 충분한 사이즈를 잡아줘야함
            R = r + K * 2 + 4;
            C = c + K * 2 + 4;

            check = new boolean[R][C];
            pq = new PriorityQueue<>();
            temp = new LinkedList<>();

            for (int i = R / 2 - 1; i < R / 2 - 1 + r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = C / 2 - 1; j < C / 2 - 1 + c; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    // 생명력
                    if (num != 0) {
                        check[i][j] = true;
                        pq.offer(new Pos(i, j, num, num * 2));
                        // c를 두배로 넣어줌
                    }
                }
            }

            func();
            System.out.println("#" + tc + " " + pq.size());

        }
    }

    // BFS는 방문여부 판단을 boolean[] 혹은 int[]로 체크할 수 있음
    // int[]는 더 무겁지만 길이를 표시해야할 때 (방법의 횟수.. )


    static void func() {
        for (int t = 1; t <= K; t++) {

            while (!pq.isEmpty()) {
                Pos cur = pq.poll();

                cur.curLife = cur.curLife - 1; // 현재 생명에 - 해줌

                if (cur.originalLife > cur.curLife) {    //활성화
                    for (int dir = 0; dir < 4; dir++) {
                        int nextI = cur.i + di[dir];
                        int nextJ = cur.j + dj[dir];

                        if (nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C)
                            continue;

                        if (check[nextI][nextJ] == false) {
                            check[nextI][nextJ] = true;
                            temp.offer(new Pos(nextI, nextJ, cur.originalLife, cur.originalLife * 2));
                        }
                    }
                }


                if (cur.curLife != 0) {    //안죽은 경우
                    temp.offer(new Pos(cur.i, cur.j, cur.originalLife, cur.curLife));
                }

            }

            while (!temp.isEmpty()) {
                pq.offer(temp.poll());
            }

        }


    }
}