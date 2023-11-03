
import java.io.*;
import java.util.*;

class Programmers_디스크컨트롤러 {
    static class Task {

        int start, time, idx;

        Task(int start, int time, int idx) {
            this.start = start;
            this.time = time;
            this.idx = idx;
        }
    }

    public int solution(int[][] jobs) {

        boolean[] visit = new boolean[jobs.length];
        int answer = 0;

        Arrays.sort(jobs, (o1, o2) ->
        {
            if (o1[0] - o2[0] == 0) {
                return o1[1] - o2[1];
            } else return o1[0] - o2[0];
        }); // 요청 시간 정렬 (오름차순) + 작업 시간 정렬 필요
        // 만약 현재 실행할 게 없을 경우 -> 앞에 있는거 하나를 빼낼 것이므로


        PriorityQueue<int[]> queue_task = new PriorityQueue<>(
                (o1, o2) -> (o1[1] - o2[1])); // 작업 시간 정렬 (오름차순)

        // 총 걸리는 시간으로 오름차순 정렬
        int time = 0; // 현재 시간
        int ans = 0; // 정답
        int count = 0; // 처리한 task 개수
        int idx = 0; // 지금 검사하는 task

        while (count < jobs.length) {

            // count는 현재 처리한 job의 개수를 의미함
            while (idx < jobs.length && jobs[idx][0] <= time) {
                queue_task.offer(jobs[idx++]); // 빼서 대기열에 넣어줌
            }

            // 만약 대기열에 하나도 없다면?
            if (queue_task.isEmpty()) {
                // 대기열에 하나도 없는 경우 idx(다음 task)를 그냥 시행해주면 됨 (먼저 요청이 들어온 작업부터 처리)
                time = jobs[idx][0] + jobs[idx][1]; // 시작 시간 + 걸리는 시간

                // 작업의 걸린 시간
                ans += jobs[idx][1];
                idx++;
            } else {
                int[] t = queue_task.poll(); // 대기열에 있는 것들 중 우선순위가 가장 높은 아이
                time += t[1]; // 진행 시간 더해줌
                ans += (time - t[0]);
            }

            count++;
        }

        answer = ans / jobs.length;
        return answer;
    }
}