import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2240 {

    // 자두 나무
    // 매 초마다 두개의 나무중 하나의 나무에서 떨어짐
    // 자두가 그 나무 아래에 있어야 먹을 수 있음
    // 하나의 나무 아래 서있다가 다른 나무 아래로 움직일 수 있음
    // 최대 W번만 움직일 수 있음
    // 자두가 받을 수 있는 개수 -> 최대



    // W를 저장하는 식으로 가야됨
    // T, W

    // 이차원 배열 -> [현재 위치][이동한 W]
    // 지금 위치에 따라서 ..?
    // [시간][움직인 횟수][지금 위치 1번 아니면 2번 나무]

    static int T, W;
    static int[] tree;
    static int[][][] dp;
    static boolean[][][] test;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T];
        dp = new int[T+1][W+1][3];
        test = new boolean[T+1][W+1][3];

        for(int i = 0; i < T; i++){
            tree[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getJadu(0,0,1));

    }


    static int getJadu(int idx, int move, int now){ // 현재 시간, 움직인 횟수, 현재위치

        if(idx >= T) return 0;  // 이제 끝

        int jadu = dp[idx][move][now];
        boolean cur = test[idx][move][now];
        if(cur) return jadu; // 이미 검사한거면 저장된 값 들고 ㄱㄱ -> 메모이제이션

        // 안움직이는 경우
        int notmoving = getJadu(idx+1, move, now); // 다음시간에 대한거 먼저
        if(tree[idx] == now) notmoving++; // 위치가 같으니까 개수 더하기

        // 움직이는 경우
        int moving_location = now == 1 ? 2 : 1; // 바꾼 위치
        int moving = 0;
        if(move < W){ // 움직일 횟수가 남은 경우에는 바꿔서 보기
            moving = getJadu(idx+1, move+1, moving_location);
            if(tree[idx] == moving_location) moving++; // 위치 바꾸고 가져오기
        }

        // 얻은 자두들의 최대를 출력해야함 -> 움직일때 안움직일때 모두 고려함
        jadu = Math.max(jadu, Math.max(notmoving, moving)); // 최대를 구하면 됨
        dp[idx][move][now] = jadu; // 넣어줘야 다음에 쓰지용
        test[idx][move][now] = true; // 검사 완료
        return jadu;
    }

}
