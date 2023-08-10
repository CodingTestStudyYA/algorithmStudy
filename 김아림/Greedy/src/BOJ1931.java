import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int count; // 정답
    static Time[] times;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        times = new Time[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // sorting시 배열 주의 -> 제대로 안되어 있으면 NullPointerError 발생
        // 정렬 -> 끝나는 시간이 같을땐 시작시간을 가지고 비교해서 기본 종료시점을 기준으로 오름차순 정렬 **
        // 왜 이렇게 정렬? 종료시간이 빠를 수록 더 많은 활동 선택 가능
        // 시작시간 고려해주는 이유 -> 종료시간이 같을때 시작시간 차이로 하나를 더 선택 가능하기도 함
        Arrays.sort(times, new Comparator<Time>() {

            @Override
            public int compare(Time arg0, Time arg1) {
                if (arg0.end == arg1.end) {
                    return arg0.start - arg1.start;
                }

                return arg0.end - arg1.end;
            }

        });

        int t = 0;
        for (int i = 0; i < N; i++) {
            if (t <= times[i].start) { // 이전값 끝난 시간이 다음값 시작시간보다 작으면 됨 -> 스케줄에 넣기 가능
                count++;
                t = times[i].end; // 다음 스케줄 채우기 위해서 끝나는 시간 업데이트
            }
        }

        System.out.println(count);

    }

    static class Time {
        int start, end; // 시작시간, 끝나는 시간

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

}