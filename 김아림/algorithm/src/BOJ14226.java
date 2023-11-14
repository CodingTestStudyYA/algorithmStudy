import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ14226 {

    // 6개가 되려면
    // 1,1
    // 2, 1
    // 2, 2
    // 4, 2 (여기서 복붙 안함)
    // 6. 2
    // 1개 -> 복붙해서 클립에 넣고(+1) 다시 복붙하기(+1 => 2)
    // 복붙해서 넣고(+1) -> 2가 됨 다시 복붙하기 -> 4
    // 복붙해서 넣고 +1 -> 2+4 => 6

    static int s;
    static int[][] dp;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());

        dp = new int[1001][1001];
        for (int i = 0; i < 1001; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[1][0] = 0; // 초기 화면 세팅
        bfs();
        System.out.println(ans);
    }


    static void bfs () {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1,0}); // emo, clipboard

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int emo = cur[0];
            int clip = cur[1];

            if(emo == s) {
                ans = dp[emo][clip];
                break;
            }

            // 한번도 방문하지 않음
            if(dp[emo][emo] == -1) {
                // 클립 보드에 복붙 하는 경우
                dp[emo][emo] = dp[emo][clip] + 1;
                q.offer(new int[] {emo, emo});
            }

            // 클립 보드에 있는걸 화면으로 복붙
            if(emo + clip < 1001 && dp[emo + clip][clip] == -1) {
                dp[emo + clip][clip] = dp[emo][clip] + 1;
                q.offer(new int[] {emo + clip, clip});
            }

            // 지우기
            if(emo -1 > 0 && dp[emo-1][clip] == -1 ){
                dp[emo-1][clip] = dp[emo][clip] + 1;
                q.offer(new int[] {emo-1, clip});
            }

        }
    }
}
