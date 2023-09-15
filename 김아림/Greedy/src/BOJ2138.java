import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
    static int N; // 스위치 개수 N개, 전구의 개수 N개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        boolean[] inputStatus = new boolean[N];

        String str = br.readLine();
        for(int i = 0; i < N; i++){
            if(str.charAt(i) == '0')
                inputStatus[i] = true;
        }

        boolean[] target = new boolean[N];

        str = br.readLine();
        for(int i = 0; i < N; i++){
            if(str.charAt(i) == '0')
                target[i] = true;
        }


        // i를 누르면 양쪽 포함 3개 인덱스 전구 상태가 바뀜
        // 범위를 벗어나면 X
        // 최소 몇번을 누르는지
        // 불가능 하면 -1

        // 첫번째, 두번재, 세번째 각각 눌러보면서 상태 저장하기
        // 다 탐색했는데도 안되면 -1

        boolean[] status = new boolean[N];
        for(int i = 1; i < N; i++){
            status[i] = inputStatus[i];
        }

        int countClicked = 1;
        // 0번 스위치 누르는 경우 따로 체크하기
        inputStatus[0] = !inputStatus[0];
        inputStatus[1] = !inputStatus[1];
        // -1, 0, 1 이렇게 생각하지말고 하나씩 밀어서 생각하기 0, +1, +2
        for(int i = 1; i < N; i++){
            if(inputStatus[i-1] != target[i-1]) {
                countClicked++; // 스위치 키기
                clicked(inputStatus, i-1);
            }
        }

        boolean clicked_check = true;
        for(int i = 0; i < N; i++){
            if(inputStatus[i] != target[i]) {
                clicked_check = false;
            }
        }

        int countUnClicked = 0;
        // -1, 0, 1 이렇게 생각하지말고 하나씩 밀어서 생각하기 0, +1, +2
        for(int i = 1; i < N; i++){
            if(status[i-1] != target[i-1]) {
                countUnClicked++; // 스위치 키기
                clicked(status, i-1);
            }
        }

        boolean unclicked_check = true;
        for(int i = 0; i < N; i++){
            if(status[i] != target[i]) {
                unclicked_check = false;
            }
        }

        // 둘 다 해봐도 안되는 경우
        if(!clicked_check && !unclicked_check){
            System.out.println("-1");
        }else if(!clicked_check){
            System.out.println(countUnClicked);
        }else if(!unclicked_check){
            System.out.println(countClicked);
        }else{
            System.out.println(Math.min(countClicked, countUnClicked));
        }
    }

    static void clicked(boolean[] a, int i){
        a[i] = !a[i];
        a[i+1] = !a[i+1];
        if(i + 2 <= N - 1){
            a[i+2] = !a[i+2];
        }
    }

}
