import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1744 {
    static int N;
    static Integer[] numbers;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new Integer[N];
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            numbers[i] = input;

        }

        // 음수가 문제임....
        // 내림차순 정렬해주기
        Arrays.sort(numbers, (o1, o2) -> o2 - o1);
//        System.out.println(Arrays.toString(numbers));

        // 무조건 곱할 수 없음. 일단 양수의 개수를 세서 짝수이면 다 곱해
        // 홀수라면 제일 작은 양수는 남겨두기
        // 0이라면 곱하지 않는 것이 더 이득이지만 양수의 경우임
        // 음수일땐 0을 곱하는게 더 이득임

        // 음수일 경우엔 작은애들끼리 곱하는게 더 좋음
        // 음수의 시작점 index를 기점으로 생각하기

        int index = 0;
        // 양수일때만 사용하는 조건
        while (index < N && numbers[index] > 0) {
//            System.out.println("plus 체크 시작");

            if (index == N - 1) break; // 범위 초과

            // 1은 곱하는거 보다 더하는게 나음.... ㅠㅠ
            if (numbers[index] == 1) {
                ans += 1;
                index += 1;
            } else {
                if (numbers[index] > 0
                        && numbers[index + 1] > 0) {
                    if (numbers[index + 1] == 1) {
                        // 1이라면 안곱하는게 나음...
                        ans += numbers[index] + 1;
                        index += 2;
                    } else {
                        ans += numbers[index] * numbers[index + 1];
                        index += 2;
                    }
                } else {
//                    System.out.println(" plus 범위 아웃 ");
                    break;
                }
            }

        }

//        System.out.println(Arrays.toString(numbers));

        boolean zero_flag = false;
        while (index < N) {
            if (numbers[index] > 0) {
//                System.out.println(
//                        "add " + numbers[index]
//                );
                ans += numbers[index];
            }
            if (numbers[index] == 0) zero_flag = true;
            if (numbers[index] < 0) break;
            index++;
        }


        int m_index = N - 1;
        while (numbers[m_index] < 0) {
//            System.out.println("minus 체크 시작");
            if (m_index == 0) break; // 범위 초과

            if (numbers[m_index] < 0
                    && numbers[m_index - 1] < 0) {
//                System.out.println(numbers[m_index] + " * "+ numbers[m_index-1]);
                ans += numbers[m_index] * numbers[m_index - 1];

                m_index -= 2;
            } else {
//                System.out.println(" minus 범위 아웃 ");
                break;
            }
        }

        for (int i = m_index; i >= index; i--) {
            if (numbers[i] < 0 && zero_flag) {
//                System.out.println("0 * " + numbers[i]);
                ans += 0 * numbers[i];
            } else {
//                System.out.println(
//                        "add " + numbers[i]
//                );
                ans += numbers[i];
            }
        }

        // 남을 수 있는 경우
        // 양수 하나 남은 경우
        // 양수 하나 음수 하나 남은 경우
        // 양수 하나 0 하나
        // 음수 하나 0 하나
        // 음수 하나 양수 하나 0 하나
        // 0이 남은 경우엔 음수가 있다면 음수랑 곱. 아니면 걍 다 더해줌


//        System.out.println(Arrays.toString(numbers));
        System.out.println(ans);

    }
}
