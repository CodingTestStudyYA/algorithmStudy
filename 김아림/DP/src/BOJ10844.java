import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10844 {

    static int N;
    static int[][] memory;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int sum = 0;
        // 0으로 시작하면 계단수가 아님.

        memory = new int[N+1][11];
        memory[1][0] = 0 ;
        for(int i = 1; i < 10; i++){
            memory[1][i] = 1;
        }

        for(int i = 2; i<= N; i++){
            for(int j = 0; j< 10; j++){
                if(j ==0) memory[i][j] = memory[i-1][j+1] %  1000000000;
                else if(j == 9) memory[i][j] = memory[i-1][j-1] % 1000000000;
                else memory[i][j] = (memory[i-1][j-1]% 1000000000 + memory[i-1][j+1]% 1000000000) % 1000000000;
            }
        }

        for(int i =0; i< 10; i++){
            sum = (sum + memory[N][i])  % 1000000000;
        }

        System.out.println(sum);
        // N -> 1일때 1,2,3,4,..,9 까지 존재 => 총 9개
        // N -> 2일때, 10 12 + 21 23 + 32 34 + 43 45 + 54 56 + 65 67 + 76 78 + 87 89 + 98
        // N -> 3일때, 121, 123 + 210 212, 232, 234 + 321 323 343 345 ....

    }
}
