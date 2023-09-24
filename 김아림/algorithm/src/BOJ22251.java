import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22251 {

    static int N, K, P, X;
    static int answer ;
    static int[][] num =
            {
                    {1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 1, 0, 0, 0, 0},
                    {1, 1, 0, 1, 1, 0, 1},
                    {1, 1, 1, 1, 0, 0, 1}
                    , {0, 1, 1, 0, 0, 1, 1}
                    , {1, 0, 1, 1, 0, 1, 1}
                    , {1, 0, 1, 1, 1, 1, 1}
                    , {1, 1, 1, 0, 0, 0, 0}
                    , {1, 1, 1, 1, 1, 1, 1}
                    , {1, 1, 1, 1, 0, 1, 1}
            };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        System.out.println(flip());
    }


    // 걍 완탐으로 재껴야함
    // 반전 경우 ?

    static int flip() {
        int result = 0;

        for(int i = 1; i <= N; i++){ // 1 이상 - N 까지만 보면 됨

            if(i == X) continue; // 실제 X층이니까 반전 없음
            int count = 0; // 개수 체크
            int original = X; // X에서 i로 가는거니까
            int target = i;
            for(int j = 0; j < K; j++){
                for(int z = 0; z < 7; z++){
                    if(num[original % 10][z] != num[target%10][z]) count++;
                } // 다른거 찾기 ~ 자릿수 비교하기
                original /= 10; // 하나씩 줄이면서 나가기
                target/=10;
            }

            // 최대 P개만 할 수 있음요
            if(count <= P) result++;
        }

        return result;
    }

}
