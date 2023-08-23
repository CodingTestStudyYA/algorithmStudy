import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17471 {

    static List<Integer>[] graph;
    static int N;
    static int[] population;
    static boolean[] select; // 조합생성용
    static boolean[] visit; // bfs 탐색용

    // 부분집합을 구한 다음 해당 부분집합을 bfs 해서


    static ArrayList<Integer> list1;
    static ArrayList<Integer> list2;
    static int min = Integer.MAX_VALUE;
    static boolean flag = false;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N];
        graph = new ArrayList[N];
        select = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<Integer>();
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int adj_size = Integer.parseInt(st.nextToken());

            // 간선 연결 
            for (int j = 0; j < adj_size; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()) - 1); // vertex 번호 0부터 시작
            }
        }

        subComb(0);
        if (flag)
            System.out.println(min);
        else
            System.out.println(-1);

    }

    static void subComb(int subCnt) {

        if (subCnt == N) {

            int true_cnt = 0;
            for(boolean b : select) {
                if(b)
                    true_cnt++;
            }

            if(true_cnt == N || true_cnt == 0){
                return; // 하나도 없거나 전부 한 곳에 들어간 경우
            }


            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (select[i]) list1.add(i);
                else list2.add(i);
            }

            int ans = 0;

            if (BFS(list1) && BFS(list2)) {
                flag = true;
                // 인구 무조건 양수라서 이렇게 해도 됨요
                // 차이 구하기이므로 걍 하나더해준거에 하나 빼주기 (abs)
                for (int i = 0; i < list1.size(); i++) {
                    ans += population[list1.get(i)];
                }

                for (int i = 0; i < list2.size(); i++) {
                    ans -= population[list2.get(i)];
                }

                min = Math.min(Math.abs(ans), min);
            }

            return;

        }


        select[subCnt] = true;
        subComb(subCnt + 1);
        select[subCnt] = false;
        subComb(subCnt + 1);
    }


    static boolean BFS(List<Integer> l) {

        Queue<Integer> q = new ArrayDeque<>();
        visit = new boolean[N];
        visit[l.get(0)] = true;
        q.offer(l.get(0)); // 처음 시작하기 ~

        int cnt = 1; // 하나 방문 완
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i : graph[cur]) {
                if (!visit[i] && l.contains(i)) {
                    q.offer(i);
                    visit[i] = true;
                    cnt++;
                }
            }
        }

        if (cnt == l.size()) return true;
        return false;
    }

}