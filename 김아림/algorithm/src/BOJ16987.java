import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] eggs;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2]; // 0 내구도 1 무게
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        eggGame(0,0);
        System.out.println(max);
    }

    static void eggGame(int i, int count) {

        // 다 돌았음
        if (i == N) {
            max = Math.max(max, count);
            return;
        }

        if (eggs[i][0] <= 0) {
            eggGame(i + 1, count); // 다음 계란 치러 가기
            return;
        }

        if(count == N - 1) {
            eggGame(i + 1, count); // 다음 계란 치러 가기
            return;
        }

        int cnt = count; // 현재 깬 계란 갯수 유지해줘야 다른 경로로 또 가볼 수 있으므로
        for (int j = 0; j < N; j++) {
            if (i == j) continue; // 자기 빼고 다른거 검색하기 ********** 필수
            if (eggs[j][0] <= 0) continue; // 이미 깨졌음

            // 현재 계란 정보
            int holding_power = eggs[i][0];
            int holding_weight = eggs[i][1];
            int target_power = eggs[j][0];
            int target_weight= eggs[j][1];

            // 계란 깨기 - 무게 적용
            eggs[i][0] -= target_weight;
            eggs[j][0] -= holding_weight;

            // 둘이 동시에 깨질 수 있음 ****
            // 지금든 계란이 깨짐..
            if (eggs[i][0] <= 0) {
                count++;
            }
            // 타겟 계란이 깨짐
            if (eggs[j][0] <= 0) {
                count++;
            }

            eggGame(i + 1, count); // 현재 계란 놓고, 오른쪽 계란들기
            eggs[i][0] += target_weight;
            eggs[j][0] += holding_weight;
            count = cnt;
        }
    }
}
