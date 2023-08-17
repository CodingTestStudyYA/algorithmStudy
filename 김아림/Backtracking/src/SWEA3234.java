import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3234 {

    static int T,N;
    static int ans;
    static boolean[] select;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] arr; // 전체 무게를 올리는 순서 -> 왼 오 는 안정함
    static int[] leftArr, rightArr;
    static int[] s;
    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            sb.append("#").append(test_case).append(" ");
            ans = 0;
            N = Integer.parseInt(br.readLine());
            s = new int[N];
            select = new boolean[N];

            arr = new int[N];
            leftArr = new int[N];
            rightArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                s[i] = Integer.parseInt(st.nextToken());
            }

            seq(0);
//            makeSeq(0,0,0);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);

    }

    static void makeSeq(int idx, int left, int right){ // idx 현재 추, right 지금까지 right무게, left 지금까지 left의 무게

        // 가지치기
        if(left < right) return;

        // 최종 결과
        if(idx == N){ // 최종적으로 전부 배치했을때
            ans ++;
            return ;
        }

        // 순열 만들기 .... => 왜 순열이 안되나...
        for(int i = 0; i < N; i++){
            if(select[i]) continue; // 중복 제거
            select[i] = true;
            makeSeq(idx+1, left+s[i], right); // 왼쪽에 두기
            if(right + s[i] <= left) {
                makeSeq(idx+1, left, right + s[i]); // 오른쪽에 두기
            }
            select[i] = false;
        }
    }

    static void seq(int cnt){
        // 여기 최종 완성됨
        if(cnt == N) {
            calc(0, 0, 0, arr);
            return;
        }


        for(int i = 0; i < N; i++){
            if(select[i]) continue;

            arr[cnt] = s[i];
            select[i] = true;
            seq(cnt+1);
            select[i] = false;
        }
    }

    static void calc(int cnt, int left, int right, int[] weight ){

        if(cnt == N) {
            ans ++;
            return;
        }

        // 왼쪽에 놓는 경우 오른쪽에 놓는 경우 따지는데
        // 다음거를 넣었을때 규칙(오 < 왼)이 안되면 걍 부르면 안됨...

        // 걍 가지를 여기서 치는거라고 생각

        calc(cnt+1, left + weight[cnt] , right, weight);


        if(left >= right + weight[cnt]){
            calc(cnt+1, left , right+ weight[cnt], weight);
        }

    }

}