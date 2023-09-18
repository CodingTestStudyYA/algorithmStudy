import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {

        // N개의 서로 다른 탑
        // 왼쪽으로 발사함
        // 어느 탑에서 수신하는지 구하시오
        // 어차피 자기보다 오른쪽의 것들은 수신하지 못함 -- !

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            int input = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()){
                int[] top = stack.pop();
                if(input <= top[1]){
                    stack.push(top);
                    System.out.print(top[0] + " ");
                    break;
                }
            }
            if(stack.isEmpty()){
                System.out.print("0 ");
            }

            stack.push(new int[] {i, input});

        }

    }
}
