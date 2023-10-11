

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486_ver2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N+1번째날 퇴사 -> 최대한 많은 상담을 위함
        // 걸리는 기간, 금액 -> 최대 이익이 필요하다
        int N = Integer.parseInt(br.readLine());

        Consult[] con = new Consult[N + 1];
        int[] dp = new int[N + 1];
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            Consult c = new Consult(time, value);
            con[i] = c;
        }

        // i는 날짜를 의미함
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dp[i - 1]); // 이전의 최대
            int endDay = con[i].t + i - 1; // 걸리는 시간 + 현재 날짜 -> 끝나는 날짜
            if (endDay <= N) {
                dp[endDay] = Math.max(dp[endDay], answer + con[i].v); // 이전 최대 + 현재
            }
        }

        answer = Math.max(answer, dp[N]);

        System.out.println(answer);
    }

    static class Consult {
        int t, v;

        Consult(int t, int v) {
            this.t = t;
            this.v = v;
        }
    }

}