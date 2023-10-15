import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135_ver2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, D;
    static int[][] map;
    static int[] tgt; // 조합
    static ArrayList<int[]> enemys; // 모든 적의 좌표 모음 -> 얘를돌아야됨

    static int ans = Integer.MIN_VALUE;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tgt = new int[3];
        enemys = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)  enemys.add(new int[]{i, j});
            }
        }

        combination(0,0);
        System.out.println(ans);

    }


    static class Enemy implements Comparator<Enemy> {
        int y, x, d;

        Enemy(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d; // 거리까지 담아놔야지
        }

        @Override
        public int compare(Enemy e1, Enemy e2) {
            return (e1.d == e2.d) ? e1.x - e2.x : e1.d - e2.d;
        }
    }

    static void combination(int src_idx, int tgt_idx) {

        if (tgt_idx == 3) {
            List<int[]> temp = new ArrayList<>();
            for (int i = 0; i < enemys.size(); i++) {
                int[] cur = enemys.get(i);
                temp.add(new int[]{cur[0], cur[1]});
            }

            attack(temp);
            ans = Math.max(ans, count);
            return;
        }

        for (int i = src_idx; i < M; i++) {
            tgt[tgt_idx] = i;
            combination(src_idx + 1, tgt_idx + 1);
        }


    }

    // 선택 -> 죽이고 -> 한칸 내리고
    static void attack(List<int[]> enemy_list) {
        count = 0;

        while (true) {
            if (enemy_list.isEmpty())
                break;


            List<int[]> targets = new ArrayList<>();

            // 1) 적 선택하기
            for (int k = 0; k < 3; k++) {
                PriorityQueue<Enemy> queue = new PriorityQueue<>();

                for (int i = 0; i < enemy_list.size(); i++) {
                    int[] cur = enemy_list.get(i);
                    int d = Math.abs(cur[0] - N) + Math.abs(cur[1] - tgt[k]);
                    if (d <= D)
                        queue.add(new Enemy(cur[0], cur[1], d));
                }

                if (!queue.isEmpty()) {
                    Enemy cur = queue.poll();
                    boolean flag = false;

                    for (int i = 0; i < targets.size(); i++) {
                        int[] cur2 = targets.get(i);
                        if (cur.x == cur2[0] && cur.y == cur2[1])
                            flag = true;
                    }
                    if (!flag) {
                        targets.add(new int[]{cur.x, cur.y});
                    }
                }
            }

            // 2) 적 죽이기
            for (int i = 0; i < targets.size(); i++) {
                for (int j = enemy_list.size() - 1; j >= 0; j--) { // list 삭제 뒤에서 부터 하기......... 여기서 꼬임 ㅜ 아놔 바본가
                    if (targets.get(i)[0] == enemy_list.get(j)[0] && targets.get(i)[1] == enemy_list.get(j)[1]) {
                        enemy_list.remove(j);
                        count+=1;
                        break;
                    }
                }
            }

            // 3) 밑으로 내리기
            for (int i = enemy_list.size() - 1; i >= 0; i--) {
                enemy_list.get(i)[0] += 1;
                if (enemy_list.get(i)[0] == N)
                    enemy_list.remove(i); // 벗어나면 죽음
            }
        }

    }
}


