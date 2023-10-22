import java.io.IOException;
import java.util.*;

/*

2023.10.18 못품 -> 주말에 다시 풀기

출발지점 - 산봉우리 - 출발지점이라고 나와있지만 출발지점 - 산봉우리로 가는 코스에 한해 최소 intensity를 찾는다면
돌아올 때도 똑같이 오면 되므로 [출발지점 - 산봉우리] 코스에 대해서만 생각하면 된다.

출발지점일 경우 다른 곳으로 가는 단방향 코스만, 산봉우리일 경우 다른 지점에서 오는 단방향 코스만

가장 긴 경로의 가중치(intensity)가 최소가 되도록 갱신해줘야 한다.


* */
public class Programmers_등산코스정하기 {

    static int n = 6; // 정점의 개수
    static int[][] paths = { { 1, 2, 3 }, { 2, 3, 5 }, { 2, 4, 2 }, { 2, 5, 4 }, { 3, 4, 4 }, { 4, 5, 3 }, { 4, 6, 1 },
            { 5, 6, 1 } }; // 2차원 정수 배열 -> 등산로 정보 (경로가 없는 경우는 없음)
    static int[] gates = { 1, 3 }; // 출입구의 번호
    static int[] summits = { 5 }; // 산봉우리의 번호
    // 나머지는 쉼터가 됨
    public static List<List<Node>> graph;

    // 출력값 -> 포함된 산봉우리 번호, intensity
    // 여러개라면 그 중 번호가 제일 낮은 산봉우리 번호 가져오기

    public static void main(String[] args) throws IOException {
        // 휴식없이 이동하는 가장 긴 시간 intensity
        // 산봉우리중 한곳만 방문한 뒤 돌아와야
        // 출입구는 처음과 끝에 한번씩 들어와야
        // intensity가 최소가 되도록 등산코스 지정
        int[] answer = new int[2];



        //그래프 만들기
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        //출입구->산봉우리 단방향 등산로
        //입구일 경우 다른 곳으로만 갈 수 있는 단방향
        //산봉우리일 경우 특정 한 곳에서 산봉우리로 가는 단방향
        for(int[] path: paths){
            int s = path[0]; // 시작
            int e = path[1]; // 도칙
            int cost = path[2]; // 비용

            // 시작점이 게이트이거나 도착점이 봉우리라면
            if(isGate(s, gates) || isSummit(e, summits)){
                graph.get(s).add(new Node(e, cost));
            }
            // 도착점이 게이트이거나 시작점이 봉우리라면
            else if(isGate(e, gates) || isSummit(s, summits)){
                graph.get(e).add(new Node(s, cost));
            }
            else{
                // 일반 등산로 양방향
                graph.get(s).add(new Node(e, cost));
                graph.get(e).add(new Node(s, cost));
            }
        }

        answer = dijkstra(n, gates, summits);
        // return answer;
        System.out.println(Arrays.toString(answer));
    }
    public static int[] dijkstra(int n, int[] gates, int[] summits){
        int[] intensity = new int[n+1];
        Queue<Node> q = new LinkedList<>();

        Arrays.fill(intensity, Integer.MAX_VALUE); // 일단 전부 INF로 채워줌

        for(int gate : gates){
            q.add(new Node(gate, 0)); // gate 0
            intensity[gate] = 0; //출입구
        }

        while(!q.isEmpty()){
            Node nn = q.poll(); // 큐에서 하나꺼냄

            // 꺼낸 nn까지 오는 intensity 가 nn의 비용보다 작다면?
            if(intensity[nn.v] < nn.cost){
                continue; // 무시
            }

            // 꺼낸 nn까지 오는 intensity가 nn의 비용보다 크다면
            // intensity 갱신
            for(int i=0; i<graph.get(nn.v).size(); i++){
                Node u = graph.get(nn.v).get(i);

                //최솟값 갱신
                // 더 큰 비용을 찾아서
                int c = Math.max(intensity[nn.v], u.cost);
                // 그 비용이 intensity보다 작으면
                if(intensity[u.v] > c){
                    // 그걸 넣어줌
                    intensity[u.v] = c;
                    // 큐에 그 노드를 넣어준다.
                    q.add(new Node(u.v, c));
                }
            }
        }

        //intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호와 intensity의 최솟값
        int sv = 0; //산봉우리 번호
        int smin = Integer.MAX_VALUE; //intensity 최솟값

        Arrays.sort(summits);

        // 산봉우리
        for (int summit : summits) {
            // 산봉우리 각각 intensity를 가짐
            if (intensity[summit] < smin) {
                smin = intensity[summit];
                sv = summit; // 정답을 위한 갱신
            }
        }

        int[] ans = {sv, smin};
        return ans;
    }

    public static boolean isGate(int v, int[] gates){
        for (int gate : gates) {
            if (v == gate) return true;
        }

        return false;
    }

    public static boolean isSummit(int v, int[] summits){
        for (int summit : summits) {
            if (v == summit) return true;
        }

        return false;
    }

    static private class Node{
        int v, cost;
        Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
    }
}