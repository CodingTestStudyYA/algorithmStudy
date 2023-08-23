import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 컨베이어 벨트 위의 로봇
public class BOJ20055 {

    static int N, K;
    static int[] power; // 2차원 배열로 할필요 없음 -> 걍 벨트가 한줄이라고 생각
    static boolean[] robot; // index는 벨트 1칸을 의미 -> 로봇의 유무 표시 가능함
    static int stage;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        power = new int[2*N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*N; i++){
            power[i] = Integer.parseInt(st.nextToken()); // 각 칸의 내구도
        }

        move();
        System.out.println(stage);
    }

    static void move(){
        //벨트가 한칸씩 이동 -> 배열 한칸씩 밀어주면 됨요 (내구도 감소 없음)
        //어떤 로봇이 내리거나 하면 자리가 빔 -> 그전 로봇은 이동할수 있음(이때 내구도 하락)
        //비어있는 올리는 위치에 로봇 올리기 가능 -> 이때도 내구도 하락

        while(true){

            // 단계 끝나는 조건 검사
            int cnt0 = 0;
            for(int i = 0; i< 2*N; i++){
                if(power[i] == 0){
                    cnt0++;
                }
            }

            if(cnt0 >= K) break;

            // 한바퀴 먼저 돌리기
            rotate();

            // 비어있는 위치에 로봇 당기기 -> 내구도 하락
            // 뒤에서 부터 당기기
            for(int i = robot.length -1; i > 0; i--){
                if(robot[i-1] && !robot[i] && (power[i]>0)){ //현재 자리에 로봇이 없고 그전자리에 있으면 당길 수 있음요
                    robot[i] = true; // 현재에 로봇 당김
                    robot[i-1] = false; // 전자리 로봇을 옮김
                    power[i] --; // 현재 내구도 하락
                }
            }

            // 로봇 올려
            if(!robot[0] && power[0] > 0){
                robot[0] = true; // 올림
                power[0]--; // 내구도 하락
            }

            stage++;
        }

    }

    static void rotate() {

        // 내구도 먼저 맞춰 돌려줌
        int last = power[power.length -1]; // 기존 끝자리

        for(int i = power.length -1; i > 0; i--){
            power[i] = power[i-1];
        }
        power[0] = last; // 처음에 끝을 넣어줌

        // 로봇 돌리기
        for(int i = robot.length -1; i > 0; i--){
            robot[i] = robot[i-1];
        }

        robot[N-1] = false; // 내림
        robot[0] = false; // 회전 전에 로봇이 내림 -> 그 상태로 돌았으니까 첫칸이 빈 상태임

    }
}
