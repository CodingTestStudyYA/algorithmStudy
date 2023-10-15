import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135 {

    static class Enemy implements Comparator<Enemy> {
        int y, x, d;

        Enemy(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d; // 거리까지 담아놔야지
        }

        @Override
        public int compare(Enemy o1, Enemy o2) {
            return (o1.d == o2.d) ? o1.x - o2.x : o1.d - o2.d;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, D;
    static int[][] map;
    static int[] tgt; // 조합
    static ArrayList<int[]> loc; // 모든 적의 좌표 모음 -> 얘를돌아야됨

    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tgt = new int[3];
        loc = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) // 적
                    loc.add(new int[]{i, j});
            }
        }

        comb(0,0);
        System.out.println(ans);

    }

    static void comb(int src_idx, int tgt_idx) {

        if (tgt_idx == 3) {
            // 이거 복사해줘야함 *****

            // 궁수 배치 조합을 다 만들었으면 해당 조합이 적을 얼마나 죽일 수 있는지 확인하러 가야함
            // 이때 적을 이동시키는 연산이나 죽이는게 들어가니까 복사본을 사용해야함
            List<int[]> temp = new ArrayList<>();

            for (int i = 0; i < loc.size(); i++) {
                int[] cur = loc.get(i);
                temp.add(new int[]{cur[0], cur[1]});
            }

            doGame(temp);
            return;
        }

        for (int i = src_idx; i < M; i++) {
            tgt[tgt_idx] = i;
            comb(src_idx + 1, tgt_idx + 1);
        }
    }

    // 게임을 돌면서 횟수를 카운팅해야함
    static void doGame(List<int[]> list) { // 적의 위치가 매개변수
        int cnt = 0;

        while (true) {
            if (list.isEmpty()) // 적을 다 죽였을 경우에 걍 바로 나와
                break;


            List<int[]> can_shoots = new ArrayList<>();
            // 이번 turn에 공격하는 적

            for (int k = 0; k < 3; k++) {
                // 궁수 하나씩 돌면서 체크함
                PriorityQueue<Enemy> queue = new PriorityQueue<>();

                for (int i = 0; i < list.size(); i++) { // 적들의 위치 리스트
                    int[] cur = list.get(i); // 적 하나하나 보면서
                    int d = Math.abs(cur[0] - N) + Math.abs(cur[1] - tgt[k]);
                    // 현재 궁수와 각 적간의 거리를 구해서 큐에 넣어줘야함
                    if (d <= D)
                        queue.add(new Enemy(cur[0], cur[1], d));
                    // 쏠 수 있는 애들만 넣어주는 거임
                }

                // 쏠 수 있는 적들을 다 담아 놓고
                if (!queue.isEmpty()) {
                    Enemy cur = queue.poll(); // 자동으로 정렬되어 있음요 (compareTo에서 잡아줌)
                    boolean flag = false;

                    for (int i = 0; i < can_shoots.size(); i++) {
                        int[] cur2 = can_shoots.get(i);
                        if (cur.x == cur2[0] && cur.y == cur2[1]) // 이미 다른 누군가가 잡으려 함
                            flag = true;
                    }
                    if (!flag) {
                        can_shoots.add(new int[]{cur.x, cur.y});
                    }
                }
            }

            // targets 리스트에 있는 애들 전부 제거
            for (int i = 0; i < can_shoots.size(); i++) {
                for (int j = list.size() - 1; j >= 0; j--) { // 뒤에서 부터 해줘야 안 흐트러지고 다 삭제 가능
                    if (can_shoots.get(i)[0] == list.get(j)[0] && can_shoots.get(i)[1] == list.get(j)[1]) {
                        list.remove(j);
                        cnt++;
                        break;
                    }
                }
            }

            // 한칸씩 이동 시켜
            for (int i = list.size() - 1; i >= 0; i--) {
                list.get(i)[0] += 1;
                if (list.get(i)[0] == N)
                    list.remove(i); // 벗어나면 죽음
            }
        }

        ans = Math.max(ans, cnt);
    }
}


