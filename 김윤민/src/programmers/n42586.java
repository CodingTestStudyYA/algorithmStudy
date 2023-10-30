package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
//기능개발
public class n42586 {
	public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> day = new ArrayDeque<>();
        int N = progresses.length;
        for(int i=0;i<N;i++){
            int percent = progresses[i];
            int nowDay = 0;
            while(percent<100){
                nowDay++;
                percent += speeds[i];
            }
            day.offer(nowDay);
        }
        while(!day.isEmpty()){
            int count= 1;
            int standard = day.poll();
            while(true){
            	if(!day.isEmpty()) {
            		int tmp = day.peek();
            		if(tmp<=standard){
                        count++;
                        day.poll();
                    }else {
                    	break;
                    }
            	}else {
            		break;
            	}
                
                
            }
            ans.add(count);
        }
        int size = ans.size();
        int[] answer = new int[size];
        for(int i=0;i<size;i++){
            answer[i]=ans.get(i);
        }
        return answer;
    }
}
