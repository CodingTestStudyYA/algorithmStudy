import java.util.*;
import java.io.*;

public class BOJ1965 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] box;
    static int[] dp; // i까지 왔을때 박스를 넣은 개수
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        box = new int[N];

        // 박스를 순차적으로 돌면서 최대를 찾고 넣을 수 있는 것들은 순차적으로 넣는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            box[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; // 자기자신을 자기자신을 넣었다고 표현하므로 1로 채워줌
        }


        for (int i = 1; i < N; i++) {
            int cur = box[i]; // 현재 index번째 박스의 크기
            for (int j = 0; j < i; j++) { // 앞의 박스를 순차적으로 검색하면서
                if (box[j] < cur) {// 만약 앞의 박스가 더 작으면
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
