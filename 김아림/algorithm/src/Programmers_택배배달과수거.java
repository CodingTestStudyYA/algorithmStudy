import java.util.*;
import java.util.stream.*;


class Solution {


    class House{
        int dist; // 출발지에서의 거리
        int amount; // 요구 박스량

        // 생성자
        House(int dist, int amount){
            this.dist = dist;
            this.amount = amount;
        }
    }



    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<House> del = new Stack<>(); // 배달 스택
        Stack<House> pick = new Stack<>(); // 수거 스택

        // 각 집 정보에서 0을 제외하곤 모두 순차적으로 스택에 담음
        for(int i = 0; i < n; i++){
            if(deliveries[i] != 0) del.push(new House(i+1,deliveries[i]));
            if(pickups[i] != 0) pick.push(new House(i+1,pickups[i]));
        }

        int delCnt = 0; // 배달 count
        int pickCnt = 0; // 수거 count
        int distance = 0; // 해당 왕복에서 가장 먼 거리를 담는 변수

        // 두 스택이 모두 비어야 종료
        while(!del.empty() || !pick.empty()){
            //양쪽다 cap보다 커지거나 스택이 비었거나하면 왕복 끝나는 것으로 간주
            if((del.empty()|| delCnt > cap) && (pickCnt > cap || pick.empty())){
                answer += distance*2; // 왕복 거리 계산

                // 초기화
                delCnt = 0;
                pickCnt = 0;
                distance = 0;
            }
            // 스택이 비어있지 않을 때와 용량이 남았을 때만 작업
            if((delCnt <= cap) && !del.empty()){
                House delivery = del.pop();
                // 배달했던 거리와 수거했던 거리 중 더 먼 거리 선택
                distance = Math.max(distance,delivery.dist);
                delCnt += delivery.amount;

                // 만약 count가 cap보다 더 커진다면 그 차이만큼만 빼주고 다시 큐에 추가
                if(delCnt > cap)
                    del.push(new House(delivery.dist,delCnt-cap));
            }

            // 위에 서술했던 delCount와 원리 똑같음
            if((pickCnt <= cap && !pick.empty())){
                House pickup = pick.pop();
                distance = Math.max(distance,pickup.dist);
                pickCnt += pickup.amount;

                if(pickCnt > cap)
                    pick.push(new House(pickup.dist,pickCnt-cap));
            }
        }
        // 마지막에 양 스택이 동시에 비어서 더해지지 않았을 수도 있음
        answer += distance*2;
        return answer;
    }
}
