import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2133 {

    static int N;
    static int memory[];
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        memory = new int[31]; // 31안잡아주고 N+1하니까 런타임 에러남
        memory [1] = 0;
        memory [2] = 3;

        for(int i = 4; i <= N; i+=2){
            // 방향 바꿔서 생각해야함 6부터 ~
            memory[i] = memory[2] * memory[i-2] + 2;
            if(i >= 6){
                 for(int j = 4; j < i; j +=2 ){
                    int add_case = memory[i-j] * 2;
                    memory[i] = memory[i] + add_case;
                }
            }
        }

        System.out.println(memory[N]);


        // memory [2] = 3 -> 위에 두개, 아래 하나 혹은 반대로 + 가로짜리 똑같은거 3개
        // memory [3] = 0 ...
        // memory [4] -> memory[2] 를 양쪽에 위 아래 같 위 아래 같 -> 3 * 3 => 9 개 + 2개 (중간에 타일 놓기 위 아래)
        // memory [5] -> 0... 일단 홀수는 안되는 삘링
        // memory [6]은 memory[2] 를 3칸씩 / memory[4]의 경우 배치 + 2의 경우  추가 고려 => + 2개 (중간에 타일 놓기 위 아래) -> 41개인 흠 ..........
        // 2일때 나온느 경우의 수 * 4 일때 나오는 경우의 수.. + 2


        // 6에서 4,2 - 2,4 같이 더하면 중복임 그래서 한번만 더하고(4,2) 나머지 (2,4)는 (4,2)에서의 중복을 빼야함여
        // 4,2 하고 나머지는 중복뺀 애들이니까 4에서 중복 빼고 나온는거 2,4
        // 8은 -> 6일때 나오는 경우의 수 * 2일때 나오는 경우의 수 + 2

    }
}
