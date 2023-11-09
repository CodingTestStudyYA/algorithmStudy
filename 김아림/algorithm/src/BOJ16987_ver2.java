import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16987_ver2 {

    static int N;
    static int[] power;
    static int[] weight;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        power = new int[N];
        weight = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            power[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 내구도는 상대 계랸의 무게만큼 깎임
        // 내구도가 0이하가 되는 순간 게란은 깨짐
        /*
        * 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
        * 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        * 이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다
        * */

        doEgg(0, 0, power);
        System.out.println(max);
    }

    // index, 지금까지 깬 계란의 개수
    // 남은 계란의 수를 구해놓고?? 시간초과 대비...

    static void doEgg(int idx, int cnt, int[] power_clone){ // 현재 들고있는 계란의 번호,

        // 이미 계란이 다 깨져있다면 깨는게 의미가 없음
        boolean flag = false;
        for(int i = 0; i < N; i++){
            if(i == idx) continue;
            if(power_clone[i] > 0){
                flag = true;
            }
        }

        // 깰 게 하나도 남아있지 않은 경우
        if(!flag){
            max = Math.max(cnt, max);
            return;
        }

        if(idx == N) {
            max = Math.max(cnt, max);
            return;
        }

        if(power_clone[idx] <=0) {
            doEgg(idx+1, cnt, power_clone);
            return;
        }

        // 계란 고르기
        for(int i = 0; i < N; i++){
            if(i == idx) continue; // 자신은 치지 않음

            // 이때 계란은 깨지지 않은 애들만 치기 때문에
            if(power_clone[i] <= 0) {
                continue; // 이미 깨져있으므로 치지 않음
            }

            // 계란 치기
            int target_egg_power = power_clone[i];
            int target_egg_weight = weight[i];
            int now_egg_power = power_clone[idx];
            int now_egg_weight = weight[idx];

            int result_tegg_power = target_egg_power - now_egg_weight;
            int result_negg_power = now_egg_power - target_egg_weight;

            int[] power_cloned = power_clone.clone();
            power_cloned[i] = result_tegg_power;
            power_cloned[idx] = result_negg_power;

            // 이때 깨진 개수 세줘야함
            int count = 0;
            if(result_tegg_power <= 0) count++;
            if(result_negg_power <= 0) count++;

            doEgg(idx+1, cnt+count, power_cloned);
        }
    }
}
