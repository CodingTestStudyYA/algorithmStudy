import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11727 {


    static int N;
    static int[] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        memory[0] = 0;
        memory[1] = 1;
        memory[2] = 3;

        for(int i =3; i<=N; i++) {
            memory[i] = (memory[i-1] + 2*memory[i-2])%10007;
        }

        System.out.println(memory[N] % 10007);

    }
}
