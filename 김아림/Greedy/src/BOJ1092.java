import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1092 {

    static int N, M; // 1분에 박스 하나
    static Integer[] limit;
    static Integer[] boxes;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        limit = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            limit[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        boxes = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            boxes[i] = Integer.parseInt(st.nextToken());
        }


        // 불가능 하면 -1
        // 하나씩만 움직여야됨..........
        // 큰 포크레인으로는 최대한 큰 박스를 옮기는게 맞음 -> 정렬
        // 최대로 한번에 들 수 있는 개수 -> 포크레인의 수
        Arrays.sort(limit, (o1, o2) -> o2-o1);
        Arrays.sort(boxes, (o1, o2) -> o2-o1);

        // 만약에 모든 박스가 가장 작은 포크레인의 한계보다 작으면? 박스 수 / 포크레인 수
//
//
//        int i = 0; // box index
//        int count = 0; // 정답
//        while(i < M){
//            count++; // 호출될때 마다 1분임
//            // 만약 제일 큰 포크레인이 제일 큰 박스를 들지 못한다면? -> 어차피 못옮김
//            if(limit[0] < boxes[i]) break;
//
//            for(int x = 0; x < N; x++){
//                // 포크레인 개수만큼 비교해서 i 증가시키기
//
//                if(limit[x] >= boxes[i]) {
////                    System.out.println(boxes[i] + "들었음");
//                    i++;
//                    if(i == M) break;
//                }else{
//
//                    // 만약 못넣을 경우엔 다음 박스를 찾아서 넣고 그 이전 박스는 다음 박스가 처리해주어야함
//                }
//            }
//            // 더이상 들 수 없으면 나오게됨
//        }
//
//        if(i == M) {
//            System.out.println(count);
//        }else{
//            System.out.println(-1);
//        }


        // 제일 큰 포크레인이 제일 큰 박스를 옮기지 못하면 -> 걍 안됨
        if(limit[0] < boxes[0]){
            System.out.println(-1);
            return;
        }

        int i = 0; // box index
        int ex = 0; // 포크레인 index
        int count = 0;
        while( i != M){
            count++;
            ex = 0;
            for(int x = 0; x < M ; x++){ // 박스 개수만큼
                if(boxes[x] == -1) continue; // 박스 이미 옮김
                if(ex == N) break; // 범위 넘어가는거
                if(boxes[x] <= limit[ex]){
                    ex++;
                    i++;
                    boxes[x] = -1;
                }
            }
        }

        System.out.println(count);

    }


}
