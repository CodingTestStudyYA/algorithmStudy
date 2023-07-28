import java.io.*;
import java.util.*;

public class BOJ11052 {

    static int N;
    static int[] price;
    static int[] maxPrice; // index개를 살때 최대값

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        price = new int[N+1];
        maxPrice = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            maxPrice[i] = price[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for(int i = 2; i<=N; i++) {
            max = maxPrice[i];
            for(int j = 1; j <= i/2; j++) {
                if(max < maxPrice[j] + maxPrice[i-j]) max = maxPrice[j] + maxPrice[i-j];
            }
            maxPrice[i] = max;
        }

        System.out.println(maxPrice[N]);
    }
}
