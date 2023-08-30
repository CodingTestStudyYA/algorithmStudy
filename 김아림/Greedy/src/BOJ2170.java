import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2170 {

    // 문제는 1차원 좌표임 *****

    static int[][] lines;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        // x 기준 정렬 (2순위가 y임)
        Arrays.sort(lines, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });


        int min_limit = lines[0][0];
        int max_limit = lines[0][1];
        ans = max_limit - min_limit;
        for (int i = 1; i < N; i++) {
            if (lines[i][0] >= min_limit && lines[i][1] <= max_limit) continue;
            else if (lines[i][0] < max_limit && lines[i][1] > max_limit) { // 현재 x가 걸치는 경우
                ans += lines[i][1] - max_limit; // 벗어난 범위 만큼만 더해주기
            } else if (lines[i][0] >= max_limit) { // 아예 범위 다 벗어남
                ans += lines[i][1] - lines[i][0];
            }
            max_limit = lines[i][1];
            min_limit = lines[i][0];
        }

        System.out.println(ans);
    }
}
