import java.util.ArrayList;

public class Programmers_문자열압축 {
    static int MIN = Integer.MAX_VALUE;
    static String s = "a";
    public static void main(String[] args) {


        for(int i = 1; i < s.length(); i++){
            getShort(i, s);
        }
        int answer = MIN;
        if(s.length() == 1) {
            // 이거 안해주니까 2번테케 틀림 ㅇㄴ
            answer = 1;
        }
        System.out.println(answer);
    }

    static void getShort(int unit_size, String str){
        int idx = 0;
        StringBuilder sb = new StringBuilder();

        // 대상 문자열 하나씩 쪼개서 배열에 만들기
        ArrayList<String> list = new ArrayList<>();

        int least_idx = 0;
        int unit_cnt = 0;

        for(int i = 0; i < str.length(); i+= unit_size){
            if((i+unit_size) <= str.length()){
                list.add(str.substring(i, i +unit_size));
                unit_cnt++;
            }
            else {
                least_idx = i;
                break;
            }
        }

        //남은 애들 따로 구해두기
        // least_idx에서 나머지까지
        String least = str.substring(least_idx, str.length());
        if(least_idx == 0){
            least = "";
        }


        int cnt = 0;
        String pre = "";
        String now = "";
        while(idx < unit_cnt){
            if(idx == 0){
                pre = list.get(idx); // 0 : a
                cnt++;
            }else{
                now = list.get(idx); // 1 : a
                if(now.equals(pre)){
                    cnt ++; // 2
                }else{ // b
                    // 다르다면
                    if(cnt != 1){
                        sb.append(cnt);
                    }
                    sb.append(pre);
                    pre = now; // 지금껄로 기준 바꿔주기
                    cnt = 1; // 1로 바꿔주기
                }
            }
            idx++;
        }
        if(cnt > 1){
            sb.append(cnt).append(pre);
        }else{
            sb.append(pre);
        }

//        System.out.println("남음 문자열 : "+least);
        sb.append(least);
//        System.out.println("대상 문자열 : "+list);
        String answer = sb.toString();
//        System.out.println("문자열 정답 : " + answer);
        if(answer.length() < MIN){
            MIN = answer.length();
        }
    }
}
