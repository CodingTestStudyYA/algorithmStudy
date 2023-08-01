
import java.util.*;
import java.io.*;


public class BOJ9095 {

    static int N;
    static int[] memory;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        memory = new int[11]; // 11보다 작다...... 으아아
        memory[1] = 1;
        memory[2] = 2;
        memory[3] = 4;

        for (int j = 4; j <= 10; j++) {
            memory[j] = memory[j - 3] + memory[j - 2] + memory[j - 1];
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t= Integer.parseInt(st.nextToken());
            System.out.println(memory[t]);
        }

    }
}
