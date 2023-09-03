import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int ans = 0;
        boolean[][] map = new boolean[H][W];

        st = new StringTokenizer(br.readLine());
        for(int i  = 0 ; i < W; i++){
            int h = Integer.parseInt(st.nextToken());
            for(int j = 0; j < h; j++){
                map[H-j-1][i] = true;
            }
        }

        // 젤 위 열 부터 검사하기
        for(int i = H-1; i>= 0; i--){
            boolean wall = false;
            int count = 0;
            for(int j = 0; j < W; j++){
                //  최초의 벽 발견
                if(!wall && map[i][j]) {
                    wall = true;
                    // 빗물 받기
                }else if (wall && !map[i][j]){
                    count++;
                    // 최초의 벽이 아닌 두번째 벽 발견
                }else if(wall && count != 0 && map[i][j]){
                    ans += count ;
                    count = 0;
                }
            }

        }

        System.out.println(ans);
    }
}
