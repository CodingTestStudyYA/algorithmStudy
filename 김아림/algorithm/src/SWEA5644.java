import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA5644 {

    // 움직이지 않음, 상 우 하 좌
    // 추가로 사용할 것들 -> 우상 좌상 우하 좌하

    static int[] dx = {0, 0, 1, 0, -1, 1, -1, 1, -1};
    static int[] dy = {0, -1, 0, 1, 0, -1, -1, 1, 1};

    static int T;
    static int M, A;
    static int[] dA;
    static int[] dB;
    static List<BC> BCs;
    // List에 모든 위치를 넣고 나서, 해당 위치가 List에 있으면 후보군을 다 빼냄
    // A의 후보군과 B의 후보군을 모두 검사해서 만약 두명이 같은 후보군이라면 각 후보군의 파워를 나누기 2로 업데이트

    static int[] AP;
    static int[] BP;

    static int[] sum;
    static int time = 0;
    static int ans = 0;


    public static void main(String[] args) throws IOException {

        // A의 위치, B의 위치..
        // BC의 위치를 모두 표현해야하는데 겹치는 부분이 존재함을 어떻게 표현하지
        // string으로 표현해서 이미 존재한다면 더 붙이는 걸로?
        // 선택지가 하나인 사람은 무조건 그것을 선택
        // 선택지가 두개인 사람은 다른 사람 먼저 고려한 후에 고려해야함

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            time = 0;
            ans = 0;
            sb.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 이동시간
            A = Integer.parseInt(st.nextToken()); // BC의 개수
            BCs = new ArrayList<>(); // BC의 리스트
            dA = new int[M];
            dB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                dA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                dB[i] = Integer.parseInt(st.nextToken());
            }

            sum = new int[M + 1];

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                BCs.add(new BC(i, y, x, p)); // 정점 먼저 넣어주고
                BFS(i, y, x, c, p);
            }

            // 시작위치
            AP = new int[]{1, 1}; // A의 위치
            BP = new int[]{10, 10}; // B의 위치

            move();

            for(int i = 0; i < M; i++){
                time++;
                // 움직이기
                int dirA = dA[i];
                int dirB = dB[i];

                // A 움직이기
                int Ax = AP[1];
                int Ay = AP[0];
                AP = new int[]{Ay+dy[dirA], Ax+dx[dirA]};

                // A 움직이기
                int Bx = BP[1];
                int By = BP[0];
                BP = new int[]{By+dy[dirB], Bx+dx[dirB]};
                move();
            }


            for(int i = 0; i <= M; i++){
                ans += sum[i];
            }
            System.out.println(Arrays.toString(sum));
//            System.out.println(ans);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);

    }


    static void move() {
        // 시작점 먼저 검사해야함
        PriorityQueue<BC> Alist = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        PriorityQueue<BC> Blist = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        // A가 충전 가능한 곳
        for (BC bc : BCs) {
            if (bc.x == AP[1] && bc.y == AP[0]) {
                Alist.offer(bc);
            }
        }

        // B가 충전 가능한 곳
        for (BC bc : BCs) {
            if (bc.x == BP[1] && bc.y == BP[0]) {
                Blist.offer(bc);
            }
        }

        // 둘다 없는 경우엔 채우지 않고 다음으로 넘어감
        if (Alist.isEmpty() && Blist.isEmpty())
            return;

        // 하나만 채울 수 있는 경우
        if (Alist.isEmpty() && !Blist.isEmpty()) {
            sum[time] = Blist.poll().p; // 그냥 최대 파워를 내는 애를 넣어주면 됨
            return;
        }

        if (Blist.isEmpty() && !Alist.isEmpty()) {
            sum[time] = Alist.poll().p; // 그냥 최대 파워를 내는 애를 넣어주면 됨
            return;
        }

        // 이제 겹치는 경우와 안겹치는 경우를 고려함
        // 1순위가 둘다 안겹치면? 그냥 그대로 쓰면 됨
        BC ABest = Alist.poll();
        BC BBest = Blist.poll();

        if (ABest.type != BBest.type) {
            sum[time] = ABest.p; // 그냥 최대 파워를 내는 애를 넣어주면 됨
            sum[time] += BBest.p; // 그냥 최대 파워를 내는 애를 넣어주면 됨
        } else { // 최대 이익의 두 충전소가 겹치는 경우에 선택지
            // 이제 겹치는 경우를 고려 - 중 둘다 한가지 선택지 밖에 없을 경우
            if (Alist.isEmpty() && Blist.isEmpty()) {
                int power = ABest.p; // 어차피 두개 같으니까 반반해서 넣어줌
                sum[time] = power;
            } else {
                // 그렇지 않은 경우엔 모든 것을 다 고려해봐야함
                // 반반해서 쓰는 경우
                // 한 사람은 1순위가 아니라 2순위를 쓰는 경우 -> A가 2순위 쓰기, 혹은 B가 2순위 쓰기
                if (Alist.isEmpty()) {
                    // 만약 Alist만 비어있는 경우라면
                    // A가 1순위 , B가 2순위
                    // 혹은 A, B 반반 나누기
                    int half = ABest.p;
                    int Bprefer = Blist.poll().p + ABest.p;
                    int bestSum = Math.max(half, Bprefer);
                    sum[time] = bestSum;
                } else if (Blist.isEmpty()) {
                    // 만약 Blist만 비어있는 경우라면
                    // B가 1순위 , A가 2순위
                    // 혹은 A, B 반반 나누기
                    int half = ABest.p;
                    int Aprefer = Alist.poll().p + BBest.p;
                    int bestSum = Math.max(half, Aprefer);
                    sum[time] = bestSum;
                } else { // 두개 다 비어있지 않은 경우
                    int half = ABest.p;
                    int Aprefer = Alist.poll().p + BBest.p; // B의 우선순위 2 + B의 첫번째 뽑았던 것(우선순위1)
                    int Bprefer = Blist.poll().p + ABest.p; // A의 첫번째 뽑았던 것(우선순위1) + B의 우선순위 2
                    int bestSum = Math.max(half, Math.max(Aprefer, Bprefer));
                    sum[time] = bestSum;
                }
            }
        }
    }


    // 시작점
    static void BFS(int type, int y, int x, int c, int p) {
        // 오직, y,x와의 거리만 고려해주면 됨

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[11][11];
        q.offer(new int[]{y, x});
        isVisit[y][x] = true; // 방문 찍고

        while (!q.isEmpty()) {

            int[] o = q.poll();

            for (int i = 1; i <= 8; i++) { // 상 하 좌 우 대각선 모두 검사해주기
                int ny = o[0] + dy[i];
                int nx = o[1] + dx[i];
                if (ny >= 1 && ny <= 10 && nx >= 1 && nx <= 10) {
                    // 무조건 y,x(충전기가 놓인곳)와의 거리가 중요한 것이므로 상대적으로 따지지 않음
                    if (!isVisit[ny][nx] && distance(y, x, ny, nx) <= c) {
                        isVisit[ny][nx] = true;
                        q.offer(new int[]{ny, nx});
                        BCs.add(new BC(type, ny, nx, p));
                    }
                }
            }
        }

    }

    static int distance(int y1, int x1, int y2, int x2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class BC {
        int type;
        int y, x, p;

        BC(int type, int y, int x, int p) {
            this.type = type;
            this.y = y;
            this.x = x;
            this.p = p;

        }

    }
}
