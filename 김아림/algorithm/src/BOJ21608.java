import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21608 {

    static int N;
    static int[][] info;
    static int[][] map;

    // 거리상 오직 상하좌우만 가능함
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    // 정답
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        // 상어 초등학교
        // N = 20 -> N^ => 400 밖에 안되긴 함 ....
        // 선생님이 자리를 정하는 순서대로 주어지므로 걍 순서대로 놓아주면 됨
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        info = new int[N * N + 1][4];
        map = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int[] like = new int[4];
            for (int j = 0; j < 4; j++) {
                like[j] = Integer.parseInt(st.nextToken());
            }
            info[student] = like;

            calc(student);
        }

        // 최종 만족도 계산은 자리 배치 전부 끝난 후에 해줘야함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int friends = 0;
                int student = map[i][j];
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                        continue;

                    // 내 친구가 그자리에 앉아있는지 체크함
                    for (int f = 0; f < 4; f++) {
                        if (info[student][f] == map[ny][nx]) {
                            // 인접한 친구인 경우
                            friends++;
                        }
                    }

                }

                if (friends != 0) {
                    ans += Math.pow(10, friends - 1);
                }

            }
        }

        System.out.println(ans);

    }

    // 한 학생을 기준으로 모든 자리를 돈다
    static void calc(int student) {

        PriorityQueue<Seat> pq = new PriorityQueue<>((s1, s2) -> {
            // 우선순위 결정하기
            if (s2.friends == s1.friends) {
                if (s2.emptySpot == s1.emptySpot) {
                    if (s1.y == s2.y) {
                        return s1.x - s2.x;
                    } else {
                        return s1.y - s2.y;
                    }
                } else {
                    return s2.emptySpot - s1.emptySpot;
                }
            } else {
                return s2.friends - s1.friends;
            }

        });

        // 자리를 전부 돌면서 각 자리마다 해당 학생이 앉기에 적합한 자리찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    // 비어있는 곳이라면
                    // 1) 인접 친구 개수 구하기
                    // 2) 인접 빈칸 구하기
                    Seat s = new Seat(i, j, 0, 0);
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                            continue;

                        // 내 친구가 그자리에 앉아있는지 체크함
                        for (int f = 0; f < 4; f++) {
                            if (info[student][f] == map[ny][nx]) {
                                // 인접한 친구인 경우
                                s.friends++;
                            } else if (map[ny][nx] == 0) {
                                // 인접 빈칸인 경우
                                s.emptySpot++;
                            }
                        }
                    }
                    pq.add(s);
                }
            }
        }

        // 자리 선택하기
        Seat s = pq.poll();
        map[s.y][s.x] = student; // 자리에 앉히기

    }

    static class Seat {
        // 인접한 좋아하는 학생 수
        // 인접한 빈칸의 수
        int y, x;
        int friends;
        int emptySpot;

        Seat(int y, int x, int friends, int emptySpot) {
            this.y = y;
            this.x = x;
            this.friends = friends;
            this.emptySpot = emptySpot;
        }
    }

}