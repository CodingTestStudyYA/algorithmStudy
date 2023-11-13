import java.util.ArrayDeque;
import java.util.Deque;

public class Programmers_두큐합같게만들기 {


//    static int min = -1;
//    static long limit;
//    static int[] queue1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 10};
//    static int[] queue2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//
//    public static void main(String[] args) {
//        int answer = -2;
//
//        // 한번의 pop + insert -> 한번의 과정으로 간주함
//        // 집어 넣은 원소가 먼저 나오는 구조
//        // index가 앞이면 먼저 넣은거
//
//        // 합을 같게 하기
//
//        // 1. 두 큐에 있는 원소를 다 빼서 더하기 => 총합/2 = 만들어야하는 합
//        // 2. 큐에 넣어주기
//        Deque<Integer> q1 = new ArrayDeque<>();
//        Deque<Integer> q2 = new ArrayDeque<>();
//
//        // 최대 방법
//        limit = (long) (queue1.length +queue2.length) * 2;
//
//        long sum1 = 0;
//        long sum2 = 0;
//        for(int i : queue1){
//            sum1 += (long) i;
//            q1.add(i);
//        }
//        for(int i : queue2){
//            sum2+= (long) i;
//            q2.add(i);
//        }
//        // 3. 각 큐에서 하나씩 빼고 넣어주면서 최소 이동횟수 구하기
//
//        dfs(q1, q2, 0, sum1, sum2);
//
//        if(min == -1){
//            answer = -1;
//        }else {
//            answer = min;
//        }
//
//        System.out.println(answer);
//        // return answer;
//    }
//
//    static void dfs(Deque<Integer> q1, Deque<Integer> q2, int cnt, long sum1, long sum2){
//
//        System.out.println(q1);
//        System.out.println(q2);
//        if(cnt > limit){
//            return;
//        }
//
//        if(sum1 == sum2){
//            min = cnt;
//            return;
//        }
//
//        int num = 0;
//
//        if(sum1 > sum2) {
//            // q1 앞에서 빼서 q2 뒤에 넣기
//            num = q1.pollFirst();
//            q2.addLast(num);
//            dfs(q1, q2, cnt+1, sum1-(long)num, (long)sum2+(long)num);
//
//        }else{
//            // q2에서 빼서 q1에 넣기
//            num = q2.pollFirst();
//            q1.addLast(num);
//            dfs(q1, q2, cnt+1, (long) sum1+(long)num, (long)sum2-(long)num);
//        }
//    }


    class Solution {

        public int solution(int[] queue1, int[] queue2) {
            int answer = 0;

            // 한번의 pop + insert -> 한번의 과정으로 간주함
            // 집어 넣은 원소가 먼저 나오는 구조
            // index가 앞이면 먼저 넣은거

            // 합을 같게 하기

            // 1. 두 큐에 있는 원소를 다 빼서 더하기 => 총합/2 = 만들어야하는 합
            // 2. 큐에 넣어주기
            Deque<Integer> q1 = new ArrayDeque<>();
            Deque<Integer> q2 = new ArrayDeque<>();

            // 최대 방법
            long limit = (long) (queue1.length +queue2.length) * 2;
            boolean flag = true;
            long sum1 = 0;
            long sum2 = 0;

            for(int i : queue1){
                sum1+= (long) i;
                q1.add(i);
            }
            for(int i : queue2){
                sum2+= (long) i;
                q2.add(i);
            }

            int num = 0;
            // 3. 각 큐에서 하나씩 빼고 넣어주면서 최소 이동횟수 구하기
            while(sum1 != sum2){

                if(answer > limit){
                    flag = false;
                    break;
                }

                if(sum1 > sum2) {
                    // q1 앞에서 빼서 q2 뒤에 넣기
                    num = q1.pollFirst();
                    q2.addLast(num);
                    sum1 -= (long) num;
                    sum2 += (long) num;

                }else if(sum1 < sum2){
                    // q2에서 빼서 q1에 넣기
                    num = q2.pollFirst();
                    q1.addLast(num);
                    sum2 -= (long) num;
                    sum1 += (long) num;

                }else {
                    break;
                }
                answer++;
            }

            if(!flag){
                return -1;
            }else {
                return answer;
            }

        }


    }
}
