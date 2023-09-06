import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1976 {


    static List<Integer>[] list;
    static Queue<Integer> plan;
    static int[] graph;
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int[] plan = new int[M];
        graph = new int[N+1];
        for(int i=1; i<N+1; i++) {
            graph[i] = i;
        }

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num ==1) {
                    union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int starter = find(Integer.parseInt(st.nextToken()));

        // M-1까지 왜 안해줘서... 아놔
        for(int i=0; i<M-1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(starter != find(num)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    //a의 집합 찾기 : a의 대표자 찾기
    private static int find(int a) {
        if (a == graph[a]) return a;
        return graph[a] = find(graph[a]);  //path compression
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            if (a < b) {
                graph[b] = a;
            } else {
                graph[a] = b;
            }
        }
    }

}