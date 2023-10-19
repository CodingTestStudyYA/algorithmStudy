import java.io.IOException;
import java.util.Arrays;

public class Programmers_양궁대회 {

    // 라이언이 어피치를 "가장큰" 점수차로 이겨야함.
    static int[] answer = new int[11];
    static int n = 10;
    static int[] info = {0,0,0,0,0,0,0,0,3,4,3};
    static int[] tgt = new int[11];
    static int peach_score = 0;
    static int lion_score = 0;
    static int max = Integer.MIN_VALUE;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {

        // 가장 큰 점수차로 이기는 경우
        // 10번부터 1번까지 검사하면서.. 봐야할 거 같은데 ㅠㅠ
        // 어떻게 해도 이길 수 없는 경우 -1을 리턴한다
        // 총 n발의 화살 ...
        // 뭔가 dfs ..? 돌면서 검사하기 ? -> 경우의 수 / 아니면 순열..? -> n으로 순열처럼 돌기..?
        // 질거면 아예 화살을 안던지는게 포인트임
        // 이길거면 하나만 더해서 이기면 됨. 굳이 2개를 더 쏜다고 달라지지 않는다구 ...

        perm(0, n, n, info); // 0번부터 돌면서 총 n개의 화살을 가짐

        if(flag)
            System.out.println(Arrays.toString(answer));
        else
            System.out.println("-1");
        //return answer;
    }

    static void perm(int score, int arrow, int n, int[] info){ // 현재 검색중인 점수, idx, 남은 화살 개수
        // 더이상 던질 수 없으므로 남은 idx까지 계속 져야함

        if(score == 11 && arrow != 0){
            return;
        }

        if(score == 11 && arrow == 0) {
            // 10점까지 다 오면
            // 점수 계산하기
            peach_score = 0;
            lion_score = 0;
            for(int i = 0; i <= 10; i++){
                if(info[i] == 0 && tgt[i] ==0) continue;
                if(info[i] < tgt[i]) lion_score += (10-i);
                else if(info[i] > tgt[i]) peach_score += (10-i);
                else if(info[i] == tgt[i])peach_score += (10-i);
            }

//            System.out.println(Arrays.toString(tgt) + " | " + peach_score + "," + lion_score);

            if(peach_score < lion_score){ // 이기고
                flag = true;
                if(max < (lion_score-peach_score)){ // 가장 큰 점수차로 이길 경우
//                    System.out.println(Arrays.toString(tgt) + " | " + peach_score + "," + lion_score);
                    max = (lion_score-peach_score);
                    answer = tgt.clone();
                    // 같은 경우
                }else if(max == (lion_score-peach_score)){
                    // 더 적은 점수 화살을 많이 맞추는 경우를 고려
//                    System.out.println(Arrays.toString(tgt) + " | " + peach_score + "," + lion_score);
                    for(int i = 10; i >= 0; i--){
                        if(answer[i] > tgt[i]){
                            break;
                        }else if(answer[i] < tgt[i]){
                            answer = tgt.clone();
                            break;
                        }
                    }
                }
            }

            return;
        }

        if(info[score] <= (arrow+1)) {
            tgt[score] = info[score] + 1;
            perm(score+1, arrow - info[score] - 1, n, info); // 이김
        }

        for(int i = 0; i<= arrow; i++){
            tgt[score] = i; // 아예 안쏘는게 아니라 arrow보다 적게는 쏠 수 있음
            perm(score+1, arrow-i, n, info); // 지거나 이기지 않거나
        }
    }


}
