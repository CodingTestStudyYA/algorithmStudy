import java.util.*;
import java.io.*;

public class BOJ11048 {

    static int N, M;
    static int[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 2; i<=M; i++) {
            maze[1][i] += maze[1][i-1];
        }

        for(int i = 2; i<=N; i++) {
            maze[i][1] += maze[i-1][1];
        }


        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= M; j++) {
                int max = Math.max(maze[i][j - 1], Math.max(maze[i - 1][j], maze[i - 1][j - 1]));
                maze[i][j] += max;
//				System.out.println(max + " : " + maze[i][j]);

            }
        }

        System.out.println(maze[N][M]);
    }

}