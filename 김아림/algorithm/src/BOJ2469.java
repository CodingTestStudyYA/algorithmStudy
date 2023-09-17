import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ2469 {

    static int K, N;
    static int[][] list;
    static int[] index;
    static int[] target;
    static char[] hiddenLine;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        int hiddenLineIdx = 0;
        list = new int[N][K]; // N줄마다 K 사람마다 가지는 각 단계에서 누구와 이어지는지를 표현함 -> 이어지지 않으면 X
        target = new int[K];
        hiddenLine = new char[K-1];

        String str = br.readLine();
        for(int i = 0; i < K; i++){
            target[i] = str.charAt(i);
        }

        index = new int[K];

        // index각 위치에 있는 알파벳-> 처음에 순서대로 시작함
        for(int i = 0; i < K; i++){
            index[i] = 'A' + i;
        }

        for(int i = 0; i < N; i++){
            str = br.readLine();
            for (int j = 0; j < K-1; j++){
                char now = str.charAt(j);
                // 이어지지 않는 경우는 0 이니까 빼기
                switch (now) {
                    case '-':
                        list[i][j] = 1 ;
                        break;
                    case '?':
                        hiddenLineIdx = i;
                        break;
                }
            }
        }

        // 사다리 게임 시작하기
        // 위에서 내려오고 아래에서 만나기 -> 한줄만 ?? 일 수 있으니까 가능
        // 만나서 비교하기. 숫자가 같으면 그냥 그대로 내려와

        for(int i = 0; i < hiddenLineIdx; i++){
            for(int j = 0; j < K-1; j++){
                int cur = list[i][j];
                if(cur == 1){
                    int temp = index[j];
                    index[j] = index[j+1];
                    index[j+1] = temp;
                }
            }
        }

        for(int i = N-1; i > hiddenLineIdx; i--){
            for(int j = 0; j < K-1; j++){
                int cur = list[i][j];
                if(cur == 1){
                    int temp = target[j];
                    target[j] = target[j+1];
                    target[j+1] = temp;
                }
            }
        }

//        System.out.println(Arrays.toString(index));
//        System.out.println(Arrays.toString(target));

        for(int i = 0; i < K-1; i++){

            // 라인이 없어도 되는 경우
            if(target[i] == index[i]) hiddenLine[i] = '*'; // 없어도 되므로

            // 서로 교차되어 있는 경우 (바꿔주기)
            else if(target[i] == index[i+1] && target[i+1] == index[i]) {
                hiddenLine[i] = '-';
                // 이후 바꿔주기
                int temp = index[i];
                index[i] = index[i+1];
                index[i+1] = temp;
            }

            else {
                System.out.println(i);
                for(int j = 1; j < K; j++){
                    System.out.print("x");
                }
                return;
            }
        }

        for(char c : hiddenLine){
            System.out.print(c);
        }
    }
}
