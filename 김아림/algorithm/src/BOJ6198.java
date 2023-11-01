import java.io.*;
import java.util.Stack;

public class BOJ6198 {
    public static void main(String[] args) throws IOException {
        // 옥상정원 꾸미기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 스택 쓰면서 하나씩 빼기 .. ?
        // 순서를 유지해야
        // stack -> 뒤에서 부터 생각 ㅠㅠ
        // 뒤에서 부터 검사해야 편할 거 같은디 .......

        // 10  stack : 10  -> 0 개
        // 3  stack : 10 3 -> 1개
        // 7 -> 3 빼고 7 넣음 => 1개 stack : 10 7
        // 4 stack : 10 7 4 -> 2개
        // 12 -> 4 빼고 7 빼고 10 빼고 => 0개 stack : 12
        // 6 stack 12 6 -> 1개

        // => 1 + 1 + 2 + 1 => 5개

        // 보는 빌딩 관점에서 보고 안풀리면 => 보이는 빌딩의 관점에서 생각하기
        // 나보다 왼쪽에 있으면서 나보다 큰 애들의 개수를 세워준다고 생각

        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++){
            // 탑 처럼 넣어주면서 처리해주는 방법이 있음 !!!!!!!
            int cur = Integer.parseInt(br.readLine()); // 일단 하나 넣어줌

            // 지금 넣은것 중 나의 왼쪽(가장 최근에 넣은거)을 기준으로 나보다 작으면 ->
            // 걔는 빼버려 (나를 못보자나) - 그걸 반복해
            // 그러면 결국 나를 볼 수 있는 애가 남음
            // 그게 그 애가 볼 수 있는 것의 개수가 되니까

            // 더 높아질때까지 빼줘야 지금의 빌딩을 볼 수 있음
            while(!stack.isEmpty() && stack.peek() < cur){ // 비어있음
                stack.pop();
            }

            stack.push(cur);
            answer += stack.size() -1; // 지금(cur)의 빌딩을 보는 빌딩의 개수
        }

        System.out.println(answer);
    }
}
