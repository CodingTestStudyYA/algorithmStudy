import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20187 {

    static int k;
    static ArrayDeque<Character> ops;
    static int[][] spots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        ops = new ArrayDeque<>();
        spots = new int[2005][2005];
        // 명령어 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * k; i++) {
            ops.push(st.nextToken().charAt(0));
        }

        // 구멍을 뚫은 위치임
        spots[0][0] = Integer.parseInt(br.readLine());

        // 맨 처음 가장 작은 사각형의 범위는 1 1 이므로 시작이 1,1
        int x = 1, y = 1;

        // 명령어가 다 떨어질때 까지 시행
        while (!ops.isEmpty()) {
            // 명령어를 하나씩 뽑아내면서 -> 이때 스택이므로 거꾸로 뽑아내짐
            switch (ops.poll()) {
                case 'L': // 오른쪽 면이 왼쪽면을 덮고 있음
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            // 현재 접힌상태 (항의 오른쪽)을 펼쳐서 대칭으로 만들어 줘야함
                            // 이때 대칭인 부분은 행이므로 행 부분의 대칭을 찾아서 똑같이 복사해주는 갓
                            spots[i][2 * y - 1 - j] = spots[i][j] ^ 1;
                        }
                    }
                    // 행 기준 범위가 넓어 졌으므로 곱하기 2
                    y *= 2;
                    break;
                case 'R': // 왼쪽면이 오른쪽 면을 덮고 있음
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            // 행에서 범위만큼 더해준 곳에 일단 대칭
                            spots[i][j + y] = spots[i][j];
                        }
                    }
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            // 이후 원래의 위치에 반대 대칭인 곳의 점을 옮겨줌 -> 펼치면서 상대적 위치 변경
                            spots[i][j] = spots[i][2 * y - 1 - j] ^ 1;
                        }
                    }
                    y *= 2;
                    break;
                case 'D':
                    // 얘네는 위 아래로 접는 것이기 때문에 열을 기준으로 달라지므로 열을 바꿔줌 (이하는 위와 동일함)
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            spots[i + x][j] = spots[i][j];
                        }
                    }
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            spots[i][j] = spots[2 * x - 1 - i][j] ^ 2;
                        }
                    }
                    x *= 2;
                    break;
                case 'U':
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            spots[2 * x - 1 - i][j] = spots[i][j] ^ 2;
                        }
                    }
                    x *= 2;
                    break;
            }
        }


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sb.append(spots[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}