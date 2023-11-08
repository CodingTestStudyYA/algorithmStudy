import java.io.IOException;
import java.util.*;

public class Programmers_후보키 {

    static String[][] relation = {
            {"100","ryan","music","2"},
            {"200","apeach","math","2"},
            {"300","tube","computer","3"},
            {"400","con","computer","4"},
            {"500","muzi","music","3"},
            {"600","apeach","music","2"}};
    static List<String> answer_list;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        // 후보키 유일성, 최소성 을 의미
        // 후보키의 최대 개수 구하기

        // 서로 겹치는게 없어야함..
        // 칼럼 개수가 8개 뿐이라서 다 해봐도 될거 같음 -> 부분 집합에 대한 모든 경우
        int[] set = new int[relation[0].length]; // 키의 개수만큼
        boolean[] visit = new boolean[relation[0].length];
        answer_list = new ArrayList<>();
        sub(visit, 0, set.length);
        System.out.println(answer);
    }

    // 순서를 반대로 해야
    static void sub(boolean[] visit, int idx, int n){
        if(idx == n){
            // 다 거침
            // 여기서 유일성, 최소성 확안해야...
            ArrayList<Integer> list = new ArrayList<>();
            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < visit.length; i++){
                if(visit[i]){
                    list.add(i);
                    ans.append(i);
                }
            }
            //System.out.println(list);
            // 최소성 체크 ㅜㅜ
            // answer_list에 있는 집합이 list의 집합이면 안됨
            // 최소성 체크를 어케하노 다시 부분집합????
            // boolean[] visit_for_list = new boolean[list.size()];
            boolean flag = true;
            if(answer_list.contains(ans.toString())){
                flag = false;
            }
            for(String answer_key : answer_list){
                //System.out.println(answer_key + ", " + ans);
                if(ans.toString().contains(answer_key))
                    flag = false;
            }

            if(flag)
                onlyCheck(list);
            return;
        }
        visit[idx] = false;
        sub(visit, idx+1, n);
        visit[idx] = true;
        sub(visit, idx+1, n);

    }


    static void onlyCheck(ArrayList<Integer> arr){

        HashSet<String> set = new HashSet<>();
        StringBuilder ans = new StringBuilder();

        for(int idx : arr){
            ans.append(idx);
        }

        for(String[] info : relation){
            StringBuilder sb = new StringBuilder();
            for(int idx : arr){
                sb.append(info[idx]);
            } // 문자열 합치기
            set.add(sb.toString());
        }
        //System.out.println("집합의 수 : " + set.size());

        // 집합의 크기가 학생의 크기랑 같으면
        if(set.size() == relation.length){
            //System.out.println("정답 더하기");
            answer++;
            // 해당 부분집합을 list에 넣어줘야함
            answer_list.add(ans.toString());
        }

    }
}
