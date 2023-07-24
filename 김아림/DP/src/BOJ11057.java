import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11057 {

    static int N;
    static int[][] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        memory = new int[N+1][10];

        // 한자리수로 만드는 오르막수 0,1,2,3,4,5,6,7,8,9 -> 10개
        // 두자리수로 만드는 오르막수
        // 10의 자리가 1일때 10,11...19 -> 10개
        // 10의 자리가 2일때 22,23,24...29 -> 9개
        // 10의 자리가 9일때 99

        // 두자리수 기준 1+2+3+4+5+.. +10 => 55

        for(int i = 0; i<10; i++){
            memory[0][i] = 1; // 자릿수가 1인 경우 -> 뭐든간에 1임
        }

        for(int i = 1; i < N+1; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = j; k < 10; k++) {
                    memory[i][j] += memory[i-1][k];
                    memory[i][j] %= 10007;
                }
            }
        }

        System.out.println(memory[N][0] % 10007);

    }
}
