import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2141 {

    static Town[] X;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        X = new Town[N];
        long people = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            X[i]= new Town(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
            people += X[i].pop;
        }

        Arrays.sort(X);

        // 마을 위치만 체크해주면 된다고 ...........
        // 마을을 기준으로 양쪽 마을의 사람의 수가 동일하면 됨
        // 위치를 기준으로 체크하여서 중간값을 찾아준다

        long ans = 0;
        for (Town a : X) {
            ans += a.pop; // 사람 하나씩 더해가면서
            if ((people + 1) / 2 <= ans) { // 중간값 보다 커지는 순간 바로 나옴 -> 중앙값과 유사하거나 바로 큰 값
                System.out.println(a.x);
                break;
            }
        }
    }

    static class Town implements Comparable<Town> {
        long x, pop;
        Town(long x, long pop){
            this.x = x;
             this.pop = pop;
        }


        @Override
        public int compareTo(Town o) {
            return (int) (this.x - o.x);
        }
    }
}
