package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {

    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static LinkedList<Integer>[] list;
    static Queue<Idx_count> q;
    static boolean[] visit;
    static int ans;
    static int min = Integer.MAX_VALUE;
    static int[] steps;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new LinkedList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            if (!list[p1].contains(p2)) list[p1].add(p2);
            if (!list[p2].contains(p1)) list[p2].add(p1);

        }

        for (int i = 1; i <= N; i++) {
            BFS(i);
        }

        System.out.println(ans);
    }

    // 최단 단계거리를 찾으므로 BFS 써줌
    static void BFS(int s) { // 도착점
        visit = new boolean[N+1];
        q = new LinkedList<>();
        q.offer(new Idx_count(s, 0));
        int steps = 0;

        while (!q.isEmpty()) {
            Idx_count cur = q.poll();

            for (int friend : list[cur.idx]) {
                if (!visit[friend]) {
                    steps += cur.count + 1;
                    visit[friend] = true;
                    q.offer(new Idx_count(friend, cur.count + 1));
                }

            }
        }
        if(steps < min) {
            min = steps;
            ans = s;
        }
    }
}

class Idx_count{
    int idx, count;
    Idx_count(int idx, int count){
        this.idx = idx;
        this.count = count;
    }
}
