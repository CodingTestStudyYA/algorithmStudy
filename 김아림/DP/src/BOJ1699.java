
import java.io.*;
import java.util.*;

public class BOJ1699 {

    static int N;
    static int[] memory;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());
        memory = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            memory[i] = i; // 최대
            for (int j = 1; j * j <= i; j++) {
                if (memory[i] > memory[i - j * j] + 1) {  // 제곱수
                    memory[i] = memory[i - j * j] + 1;
                }
            }
        }
        System.out.println(memory[N]);
    }
}
