package DFS_BFS;

import java.io.*;
import java.util.*;

public class Robot {

    static int N, M;
    static int[][] map;
    static int d; // 바라보는 방향

    static int[] xWay = {-1,0,1,0};
    static int[] yWay = {0,1,0,-1}; // 북 동 남 서
    static int cleanedRoom = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());


        map = new int[N][M];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(x,y);
        System.out.print(cleanedRoom);

    }

    static void DFS(int x, int y) {

        if(map[x][y] == 1) // 벽이라면 아예 갈 수 없음
            return;

        if(map[x][y] == 0) { // 청소되지 않은 칸에 들어온 경우
            map[x][y] = -1; // do clean
            cleanedRoom++;
        }

        int count = 0 ;
        for (int i = 0; i < 4; i++) {
            if(d == 0) d = 3;
            else
                d = (d-1) % 4; // rotate d=0으로 시작해서 1,2,3,0으로 돌아옴
            int nx = x + xWay[d]; // go
            int ny = y + yWay[d];
            if(isNotCleaned(nx, ny)) { // 청소되지 않은 곳이라면
                DFS(nx, ny); // 청소하러 감
                break;
            }else {
                count++; // 청소되지 않은 빈칸이 아닌 경우 -> 개수 ++
            }
        }

        // 청소되지 않은 빈칸 없음
        if(count == 4) {
            int nx = x + xWay[(d+2)%4]; // rear - 방향 유지
            int ny = y + yWay[(d+2)%4];
            if(isAvailable(nx, ny)) { // 벽이 아니라면 뒤로 간다.
                DFS(nx, ny);
            }else {
                return;
            }
        }
    }


    // 청소되지 않음
    static boolean isNotCleaned(int x, int y) {
        if (x>=0 && x<N && 0<=y && y<M) {
            if(map[x][y] == 0)
                return true;
        }

        return false;
    }


    // 벽이 아님
    static boolean isAvailable(int x, int y) {
        if (x>=0 && x<N && 0<=y && y<M) {
            if(map[x][y] != 1)
                return true;
        }

        return false;
    }

}