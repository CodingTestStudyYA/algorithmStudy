
import java.util.*;
import java.io.*;

public class BOJ2293 {
    private static int N, K;
    private static int[] coin_value;
    private static int[] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin_value = new int[N + 1];
        memory = new int[K + 1];
        memory[0] = 1;

        for (int i = 1; i <= N; i++) {
            coin_value[i] = Integer.parseInt(br.readLine());

        }

        for(int i = 1; i<= N; i++){
            for(int j = coin_value[i]; j <= K; j++){
                memory[j] += memory[j - coin_value[i]];
            }
        }

        System.out.println(memory[K]);
    }

}