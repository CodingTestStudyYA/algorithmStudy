package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {


    static int N, M, H;
    static int[][][] ogBox;
    static int[][][] newBox;

    static int[] Nway = {-1,1,0,0,0,0};
    static int[] Mway = {0,0,1,-1,0,0};
    static int[] Hway = {0,0,0,0,1,-1};
    static Queue<Place> queue;
    static Queue<Place> Nqueue;
    static boolean[][][] isVisited;
    static int days;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ogBox = new int[H][M][N];
        queue = new LinkedList<>();
        Nqueue = new LinkedList<>();
        isVisited = new boolean[H][M][N];
        int count = 0;

        for(int h = 0; h < H; h++){
            for(int m= 0; m < M; m++){
                st = new StringTokenizer(br.readLine());
                for(int n = 0; n < N; n++){
                    ogBox[h][m][n] = Integer.parseInt(st.nextToken());
                    if(ogBox[h][m][n] == 1) {
                        queue.add(new Place(h,m,n));
                        isVisited[h][m][n] = true;
                    }if(ogBox[h][m][n] == 0){
                        count++;
                    }
                }
            }
        }

        if(count == 0){
            System.out.println(0);
            return;
        }

        BFS(); // 첫날 익은 토마토
        while(!Nqueue.isEmpty()){
            days++;
            queue = Nqueue;
            Nqueue = new LinkedList<>();
            BFS();
        }

        for(int h = 0; h < H; h++){
            for(int m= 0; m < M; m++){
                for(int n = 0; n < N; n++){
                    if(ogBox[h][m][n] == 0) {
                        System.out.println(-1); // 다 돌았는데도 안익은 거
                        return;
                    }
                }
            }
        }

        System.out.println(days);
    }

    static void BFS(){

        while(!queue.isEmpty()){
            Place cur = queue.poll();
            int h = cur.h;
            int m = cur.m;
            int n = cur.n;
            for(int i = 0; i< 6; i++){ // 방향대로 검사 1회
                int nh = h + Hway[i];
                int nm = m + Mway[i];
                int nn = n + Nway[i];
                if(isAvailable(nh, nm, nn) && ogBox[nh][nm][nn] == 0){ //갈 수 있고 익지 않은 토마토가 존재한다면
                    Nqueue.add(new Place(nh, nm, nn)); // 둘째날 익은 토마토들 전부 넣어주기
                    ogBox[nh][nm][nn] = 1;
                    isVisited[nh][nm][nn] = true;
                }
            }
//            System.out.println();
        }

    }

    static boolean isAvailable(int h, int m, int n){
        if(0 <= h && h < H && 0<= m && m < M && 0<= n && n < N){
            if(!isVisited[h][m][n])
                return true;
        }
        return false;
    }
    static class Place{
        int h;
        int m;
        int n;

        public Place(int x, int y, int z){
            this.h = x;
            this.m = y;
            this.n = z;
        }

        @Override
        public String toString() {
            return "Place{" +
                    "h=" + h +
                    ", m=" + m +
                    ", n=" + n +
                    '}';
        }
    }
}
