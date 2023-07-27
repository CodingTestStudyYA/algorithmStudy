package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hiding {

    static int N, K;
    static Queue<Integer> queue;
    static int[] visited;
    static int cur;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[100001];

        for(int a : visited)
            a = 0;

        visited[N] = 0; // 방문
        queue = new LinkedList<>();
        queue.add(N);

        System.out.println(BFS());
    }

    static int BFS() {

        int count = 0;

        while(!queue.isEmpty()) {
            cur = queue.poll();
            if(cur == K) return visited[cur];
            count ++;
            if((cur -1)==K || (cur+1)==K || (cur*2)==K) return visited[cur] + 1 ;
            put((cur-1));
            put((cur+1));
            put((cur*2));
        }

        return visited[cur];
    }

    static void put(int a) {
        if((0 <= a) && (a <= 100000) && visited[a] == 0 ) {
            queue.add(a);
            visited[a] = visited[cur] + 1; // a까지 갔을때 경로 횟수는 지난 경로횟수 + 1
        }
    }
}
