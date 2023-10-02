import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12869 {

    static int N ; // 강호의 SCV의 개수 -> 각각 체력을 가짐
    // 뮤탈리스크만 SCV를 공격할 수 있음
    // 한번에 3개의 SCV 공격 가능 -> 하나에 3번 빠바방은 안됨
    // 공격해야하는 횟수의 최솟값
    // 9, 3, 1

    static int[] scv;
    static int[][][] dp = new int[61][61][61];

    static int[][] way= {
            {1,9,3}, {1,3,9},
            {3,1,9}, {3,9,1},
            {9,1,3}, {9,3,1}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        scv = new int[3]; // 최대 3개임
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }

        BFS(scv[0], scv[1],scv[2]);
        System.out.println(dp[0][0][0] - 1); // 모두 0이 되었다는 의미 (다 죽음)
    }

    static void BFS(int s1, int s2, int s3){
        dp[s1][s2][s3] = 1;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {s1, s2, s3}); // 현재 남은 체력을 넣어줌

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 6; i++){
                int now1 = cur[0] - way[i][0]; // 체력에서 -- 공격해줌
                int now2 = cur[1] - way[i][1];
                int now3 = cur[2] - way[i][2];

                if(now1 < 0 ) now1 = 0; // 만약 공격해서 죽게되면 0으로(음수안됨)
                if(now2 < 0 ) now2 = 0;
                if(now3 < 0 ) now3 = 0;

                if(dp[now1][now2][now3] != 0) continue;
                // 만약에 0이 아니면?(즉 이미 이상태가 되도록 공격해봄) -> 가장 최소값이 들어있을 것임
                // 그러니까 갱신 안함

                dp[now1][now2][now3] =dp[cur[0]][cur[1]][cur[2]] +1 ;
                // 이전 체력에서 공격추가(+1) => 현재 우리가 깎은 체력까지 도달하는데 들인 공격임

                q.add(new int[] {now1, now2, now3});
                // 큐에 넣어줘야함
            }
        }
    }
}
