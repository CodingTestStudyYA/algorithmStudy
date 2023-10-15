import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3151 {

    // 10
    // -1 -1 0 0 0 1 1 1 1 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = 0; // 정답

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        for (int i = 0; i < N; i++) {
            if (list[i] > 0)
                break;
            long student = list[i]; // 얘는 무조건 포함함

            int leftIdx = i + 1;
            int rightIdx = N - 1;
            long target = (-1) * student; // 나머지 두 수로 만들어야 -> 합이 0

            while (leftIdx < rightIdx) {
                long sum = list[leftIdx] + list[rightIdx];

                if (sum == target) {
                    // 이때를 개선해줘야함
                    int l = 1;
                    int r = 1;

                    if (list[leftIdx] == list[rightIdx]) {
                        int n = rightIdx - leftIdx + 1; // 사이의 개수 -> 다 같음
                        // 개수 만큼의 조합 생성 가능함
                        ans += (n * (n - 1) / 2);
                        break;
                    }
                    while (list[leftIdx] == list[leftIdx + 1]) {
                        l++;
                        leftIdx++;
                    }

                    while (list[rightIdx] == list[rightIdx - 1]) {
                        r++;
                        rightIdx--;
                    }

                    ans += (r * l);

                }

                if (sum < target) {
                    leftIdx++;
                } else {
                    rightIdx--;
                }
            }

        }

        System.out.println(ans);

    }
}