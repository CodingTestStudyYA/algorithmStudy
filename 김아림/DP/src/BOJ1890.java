import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1890 {

    static int N;
    static int[][] way;
    static long[][] memory; // long 필수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        way = new int[N+1][N+1];
        memory = new long[N+1][N+1];
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1; j<=N; j++){
                way[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memory[1][1] = 1;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int distance = way[i][j]; // 이걸 생각 못했음 으아아 - 현재 갈 수 있는 값을 여기에 두고
                if(distance==0) break; // 갈 수 없음 -- 멈춰
                if(j+distance<=N) memory[i][j+distance] += memory[i][j]; // 다음에 가는 곳에 더해주기
                if(i+distance<=N) memory[i+distance][j] += memory[i][j];
            }
        }

        System.out.println(memory[N][N]);


    }
}
