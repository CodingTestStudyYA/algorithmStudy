import java.util.*;

public class Programmers_메뉴리뉴얼 {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        // 최소 두가지 이상의 단품 메뉴
        // 최소 두명의 손님으로부터 주문된 메뉴조합에 대해서만

        // 스카피가 추가하고 싶어하는 코스요리를 구성하는 단품메뉴의 개수가 담긴 배열

        // 처음 course의 개수에 맞춰서 정하는 거 부터 난제 ^^&...

        // course 에 하나 들어갈걸 정하면 containsAll 이거 쓸래
        // 정렬해줘야함

        ArrayList<String> answer = new ArrayList<>();


        Arrays.sort(orders);
        ArrayList<String> target = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            int cnt = course[i]; // cnt짜리 메뉴를 선정해야함
            // 일단 cnt보다 긴 주문만 대상이 될 수 있으므로

            for (String order : orders) {
                if (order.length() >= cnt) target.add(order);
            }
            list = new ArrayList<>();

            for (String t : target) {
                // cnt개를 뽑는 모든 경우를 다 넣어놓고
                // cnt보다 큰 길이의 target을 대상으로 가지는지 체크하면서 수 ++
                int[] tgt = new int[cnt];
                comb(0, 0, t.length(), cnt, tgt, t);
            }

            HashMap<String, Integer> map = new HashMap<>();

            // cnt보다 큰 길이의 target을 대상으로 가지는지 체크하면서 수 ++
            // 최고값 찾아놓기 - 여러개 일 수 있음
            int max = Integer.MIN_VALUE;
            System.out.println("매핑 시작 ========== ");
            for (String t : list) {
                // 정답 t가 target에 얼마나 포함되는지
                for(String order_list : target){
                    if(order_list.contains(t)){
                        if (map.containsKey(t)) {
                            map.replace(t, map.get(t) + 1);
                        } else {
                            map.put(t, 1);
                        }
                        int now = map.get(t);
                        if (now > max) {
                            max = now;
                        }
                    }
                }
            }

            // max가 1인 경우 생각하기 *****
            for (String t : list) {
                if (map.get(t) == max) {
                    answer.add(t);
                }
            }
        }

        System.out.println(answer);
    }

    // 조합을 다 찾아내서.. cnt가 2인것만 걸러내는데....
    // 걸러내고 ????? ㅜㅠ
    // 시바 조합 어케 만들어.
    // visit 없이 돌면서..............
    // n개를 뽑는데 src의 크기가 다 다름. src의
    static void comb(int tgt_idx, int src_idx, int src_size, int n, int[] tgt, String str) {

        if(src_idx == src_size){
            return;
        }

        if (tgt_idx == n) {
            System.out.println(Arrays.toString(tgt));
            System.out.println(str);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < tgt.length; i++) {
                sb.append(str.charAt(tgt[i]));
            }
            if(!list.contains(sb.toString())){
                list.add(sb.toString());
            }
            return;
        }

        tgt[tgt_idx] = src_idx;
        comb(tgt_idx + 1, src_idx + 1, src_size, n, tgt, str);
        comb(tgt_idx, src_idx + 1, src_size, n, tgt, str);

    }

}
