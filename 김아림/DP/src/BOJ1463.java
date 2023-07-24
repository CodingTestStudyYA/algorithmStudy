import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1463 {


    static int[] memory;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        memory = new int[N+1];

        for(int i = 0; i <= N; i++){
            memory[i] = -1; // 값 없음 의미
        }

        memory[0] = memory[1] = 0;

        System.out.println(recursion(N));


    }

    static int recursion(int n){

        if(memory[n] == -1) { //없으면
            if(n % 6 == 0) memory[n] = Math.min(recursion(n-1), Math.min(recursion(n/3), recursion(n/2))) + 1;
            else if (n%3 ==0) memory[n] = Math.min(recursion(n/3), recursion(n-1)) + 1;
            else if (n%2==0) memory[n] = Math.min(recursion(n/2), recursion(n-1)) + 1;
            else memory[n] = recursion(n-1) + 1;
        }

        return memory[n]; // 있으면 걍 활용임
    }
}
