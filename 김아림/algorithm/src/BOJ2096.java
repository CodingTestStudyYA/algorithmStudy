
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N][3];
        int[][][] dp = new int[N][3][2];

        int max_ans = Integer.MIN_VALUE;
        int min_ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    dp[i][j][0] = input[i][j];
                    dp[i][j][1] = input[i][j];
                }
            }
        }



        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j][0] = input[i][j] + Math.max(dp[i-1][j][0], dp[i-1][j + 1][0]);
                } else if (j == 2) {
                    dp[i][j][0] = input[i][j] + Math.max(dp[i-1][j - 1][0], dp[i-1][j][0]);
                } else {
                    dp[i][j][0] = input[i][j] + Math.max(dp[i-1][j - 1][0], Math.max(dp[i-1][j][0], dp[i-1][j + 1][0]));
                }

            }
        }

        for(int i = 0; i < 3; i++) {
            max_ans = Math.max(max_ans, dp[N-1][i][0]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j][1] = input[i][j] + Math.min(dp[i-1][j][1], dp[i-1][j + 1][1]);
                } else if (j == 2) {
                    dp[i][j][1] = input[i][j] + Math.min(dp[i-1][j - 1][1], dp[i-1][j][1]);
                } else {
                    dp[i][j][1] = input[i][j] + Math.min(dp[i-1][j - 1][1], Math.min(dp[i-1][j][1], dp[i-1][j + 1][1]));
                }

            }
        }

        for(int i = 0; i < 3; i++) {
            min_ans = Math.min(min_ans, dp[N-1][i][1]);
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(dp[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("==========================");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(dp[i][j][1] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(max_ans + " " + min_ans);

    }

}