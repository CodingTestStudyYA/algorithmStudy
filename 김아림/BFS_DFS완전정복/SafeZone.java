package DFS_BFS;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafeZone {
    static int  N ;
    static int map[][];
    static int safeZone;
    static boolean visited[][];
    static Queue<Point> queue;
    static int[] nextX = {0,0,1,-1};
    static int[] nextY = {1,-1,0,0};
    static int MIN,MAX;
    static Queue<Integer> heightQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        heightQ = new LinkedList<>();
        map = new int[N][N];
        visited = new boolean[N][N];
        safeZone = -1;
        MIN = 101;
        MAX = -1;
        heightQ.add(0);
        for(int i =0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j =0; j < N; j++){
                int n = Integer.parseInt(input[j]);
                if(!heightQ.contains(n)){
                    heightQ.add(n);
                }
                map[i][j] = n;
                if(MIN > n) {
                    MIN = n;
                }
                if(MAX < n) {
                    MAX = n;
                }
            }
        }


        while (!heightQ.isEmpty()) {
            int i = heightQ.poll();
            int count = 0;

            // visited 초기화
            for(int j = 0; j < N; j++) {
                for(int z = 0; z < N; z++) {
                    visited[j][z] = false;
                }
            }

            // i이하의 공간은 물에 잠기므로 -> 갈 수 없음
            for(int j = 0; j < N; j++) {
                for(int z = 0; z < N; z++) {
                    if(map[j][z] <= i) {
                        visited[j][z] = true;
                    }
                }
            }

            for(int j =0; j < N; j++) {
                for(int z =0; z < N; z++){
                    if(!visited[j][z]) {
                        queue.add(new Point(j,z));
                        BFS();
                        count++;
                    }
                }
            }

            if(safeZone < count) {
                safeZone = count;
            }

        }

        System.out.println(safeZone);
    }


    static void BFS(){

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for(int i =0; i< nextX.length; i++){
                int Xway = x + nextX[i];
                int Yway = y + nextY[i];
                if(Xway >= 0 && Xway < N && Yway >=0 && Yway < N){
                    // 방문하지 않았다면
                    if(!visited[Xway][Yway]) {
                        // 큐에 추가 + 방문했음을 알림
                        queue.add(new Point(Xway, Yway));
                        visited[Xway][Yway] = true;
                    }
                }
            }
        }
    }

}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

