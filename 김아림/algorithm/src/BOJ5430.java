import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ5430 {

    static ArrayDeque<Integer> deque = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++){
            deque = new ArrayDeque<>();
            sb = new StringBuilder();

            String operator = br.readLine(); // 명령어 집합
            int n = Integer.parseInt(br.readLine()); // 배열 크기

            // 원래 했던 방법 -> 읽어와서 스트링에 넣고 잘라서 배열 만들기 -> 만약 개수가 0이라면 걍 0 배열 바로 생성

//            String arr = br.readLine();
//            String[] c ;
//            if(n > 0) {
//                c = arr.substring(1, 2*n).split(",");
//            }
//            else {
//                c = new String[0];
//            }

//
//            for(int i = 0; i < c.length; i++){
//                deque.add(Integer.parseInt(c[i])); //
//            }

            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            for(int i= 0; i<n; i++){
                deque.add(Integer.parseInt(st.nextToken()));
            }

            boolean rotated = false;
            boolean error = false;

            // 명령어 실행부분
            for(int i = 0; i < operator.length(); i++){

                switch (operator.charAt(i)) {
                    case 'R' :
                        rotated = !rotated; // 반대가 되도록 함
                        break;
                    case 'D' :
                        if(deque.isEmpty()) {
                            error = true;
                            break;
                        }
                        if(rotated) deque.pollLast(); // 돌아있는 상태
                        else deque.pollFirst(); // 결과적으로 안돈것(원래)과 마찬가지임
                        break;
                }

            }

            if(error) System.out.println("error");
            else {
                sb.append("[");
                while(!deque.isEmpty()){
                    if(rotated)
                        sb.append(deque.pollLast()).append(",");
                    else
                        sb.append(deque.pollFirst()).append(",");
                }
                sb.setLength(sb.length()-1);
                sb.append("]");
                System.out.println(sb);
            }


        }

    }
}
