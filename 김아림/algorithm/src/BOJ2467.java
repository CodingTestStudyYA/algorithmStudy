
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[] input = new Long[N]; // 입력

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            input[i] = Long.parseLong(st.nextToken());
        }

        Long[] answer = new Long[2];
        long min = Long.MAX_VALUE;

        // 중성을 만드는데 있어서 필요하 것은 산성/알칼리성의 비율 
        // 배열에서 왼쪽으로 갈수록 알칼리 오른쪽으로 갈수록 산임 
        int closeAcid = N - 1;
        int closeAlk = 0;

        while ( closeAlk < closeAcid ) {

            long now_sum = input[closeAcid] + input[closeAlk];

            if(now_sum == 0) {
                answer[0] = input[closeAlk];
                answer[1] = input[closeAcid];
                break;
            }

            // 만약에 값이 음수라면? 산을 더 큰값을 써줘야함 
            // 만약 값이 양수라면? 알칼리를 더 작은값 써줘야함 
            if(Math.abs(now_sum) < min) {
                answer[0] = input[closeAlk];
                answer[1] = input[closeAcid];
                min = now_sum;
            }

            if(now_sum > 0) {
                closeAcid --;
            }else {
                closeAlk++;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);

    }

}