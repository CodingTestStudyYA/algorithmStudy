
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1911 {

    static int N, L;
    static int[][] list;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N개의 물 웅덩이, 길이가 L인 널빤지 -> 개수 구하기 
        // 물웅덩이 마다 위치와 크기가 존재함 
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        list = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] loc = new int[3];
            // 시작, 끝, 길이 
            loc[0] = Integer.parseInt(st.nextToken());
            loc[1] = Integer.parseInt(st.nextToken());
            loc[2] = Math.abs(loc[1] - loc[0]);
            list[i] = loc;
        }



        Arrays.sort(list, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        int end = list[0][0] + L; // 널빤지 길이만큼
        int ans = 1;

        for(int i= 0; i<N; i++) {
            // 웅덩이의 시작점이 판 보다 큼 => 커버 못함
            if(list[i][0] > end){
                end = list[i][0]; // 새로 놓기
            }
            // 새로 놓고 나서 끝까지 커버하지 못하는 경우엔
            if(list[i][1] > end) {
                // 끝까지 커버할때 까지 판 놓기
                while(list[i][1] > end) {
                    end += L;
                    ans ++;
                }
            }
        }

        System.out.println(ans);
    }
}