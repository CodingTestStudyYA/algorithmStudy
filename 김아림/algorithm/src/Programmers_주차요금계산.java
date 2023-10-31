import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        // 저장 방식에서 머리가 안돌아가서 참고... 대충 String에 List결합해서 쓰다가 ㅠㅠ hashMap 쓰기! 
        Map<String, String> map = new HashMap<>();
        Map<String, Integer> fee_record = new HashMap<>();

        for(int i = 0; i < records.length; i++){
            // 차량별로 요금 먼저 담을 수 있게함 -> 일단 0으로 
            fee_record.put(records[i].split(" ")[1], 0);
        }

        // -----------------------------------------------------

        for(int i = 0; i < records.length; i++){
            // 하나의 기록
            String[] infos = records[i].split(" ");

            if(map.containsKey(infos[1])){ // 이미 차에 대한 정보가 있을 경우에 

                String[] inT = map.remove(infos[1]).split(":");
                // 지금 입차되고 출차되지 않은 차 목록에서 제거하면서 시간(입차시간)을 가져옴 
                String[] outT = infos[0].split(":");
                // 출차 시간 가져오기 

                int hour = Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]);
                int minute = Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);

                fee_record.replace(infos[1], fee_record.get(infos[1]) + 60 * hour + minute);
                // 요금에서 차번호에 대해서, 현재 차에 대한 시간 더해주기 
            }else{
                map.put(infos[1], infos[0]); // 차 번호, 시간 넣어주기 
            }
        }

        // 출차하지 않은 차 계산 
        for(String key : map.keySet()){
            String[] inTime = map.get(key).split(":");

            int hour = 23 - Integer.parseInt(inTime[0]);
            int minute = 59 -Integer.parseInt(inTime[1]);

            fee_record.replace(key, fee_record.get(key) + 60 * hour + minute);
        }

        // 시간 계산 
        List<Map.Entry<String, Integer>> list = new ArrayList(fee_record.entrySet());
        Collections.sort(list, (o1, o2) -> {
            return Integer.parseInt(o1.getKey()) > Integer.parseInt(o2.getKey())?1 :
                    Integer.parseInt(o1.getKey()) < Integer.parseInt(o2.getKey())?-1 : 0;
        });


        answer = new int[list.size()];

        // 정답에 대해서 
        for(int i = 0; i < answer.length; i++){
            // 기본 시간보다 많다면 
            if(list.get(i).getValue() > fees[0]){
                // 기본 요금 + 넘은 숫자 만큼의 단위요금제 추가 
                // ceil로 올림, 내림처리하기 
                answer[i] = fees[1] + (int) Math.ceil((list.get(i).getValue() - fees[0]) / (double)fees[2]) * fees[3];
            }else{
                // 기본 시간보다 적으면 그냥 기본 요금 
                answer[i] = fees[1];
            }
        }


        return answer;
    }
}