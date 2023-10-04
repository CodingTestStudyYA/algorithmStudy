import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138_ver2 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        boolean[] input = new boolean[N];

        String str = br.readLine();
        for(int i = 0; i < N; i++){
            if(str.charAt(i) == '0')
                input[i] = true;
        }

        boolean[] answer = new boolean[N];

        str = br.readLine();
        for(int i = 0; i < N; i++){
            if(str.charAt(i) == '0')
                answer[i] = true;
        }

        boolean[] inputCloned = new boolean[N]; // 얘로 저장해서 돌려야지
        inputCloned = input.clone();


        // --------------------
        int count1 = 1; // 0번 누름
        input[0] = !input[0];
        input[1] = !input[1];
        for(int i = 1; i < N; i++){
            if(input[i-1] != answer[i-1]) {
                count1++; // 다르니까 키고
                switching(input, i-1); // 위치 전송해주기
            }
        }

        boolean isSuccess_zero = true;
        for(int i = 0; i < N; i++){
            if(input[i] != answer[i]) {
                // false -> 여기서 실패함
                isSuccess_zero = false;
            }
        }

        // -------------------------
        int count2 = 0; // 0 번 안누름
        for(int i = 1; i < N; i++){
            if(inputCloned[i-1] != answer[i-1]) {
                count2++; // 스위치 키기
                switching(inputCloned, i-1);
            }
        }

        boolean isSuccess_unZero = true;
        for(int i = 0; i < N; i++){
            if(inputCloned[i] != answer[i]) {
                isSuccess_unZero = false;
            }
        }

        // 0을 누른경우 안누른 경우를 중심으로 해서 둘다 false(실패)
        if(!isSuccess_zero && !isSuccess_unZero){
            System.out.println("-1");
        }else if(!isSuccess_zero){
            // 마지막 출력부 에서 걍 처리하게 max로만 설정(거름)
            count1 = Integer.MAX_VALUE;
        }else if(!isSuccess_unZero){
            // 마지막 출력부 에서 걍 처리하게 max로만 설정(거름)
            count2 = Integer.MAX_VALUE;
        }

        System.out.println(Math.min(count1, count2));

    }

    static void switching(boolean[] status, int i){

        status[i] = !status[i];
        status[i+1] = !status[i+1];

        if(i + 2 <= N - 1){ // 범위 제한해야함요
            status[i+2] = !status[i+2];
        }
    }

}