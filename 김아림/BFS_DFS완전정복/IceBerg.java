package DFS_BFS;

import java.util.*;

public class IceBerg {

    static int N, M;
    static int[][] ogMap;
    static int[][] newMap;
    static int[] xWay = {1,-1,0,0};
    static int[] yWay = {0,0,1,-1};

    static Queue<Pointer> q;
    static boolean[][] isVisited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ogMap = new int[N][M];
        newMap = new int[N][M];

        for(int i =0; i< N; i++) {
            for(int j =0; j<M; j++) {
                newMap[i][j] = ogMap[i][j] = sc.nextInt();

            }
        }

        int group_count = 0;
        int year = 0;
        while(true) {
            q = new LinkedList<Pointer>();
            isVisited = new boolean[N][M];
            group_count = 0;
            for(int i = 0; i<N; i++) {
                for(int j =0; j<M; j++) {
                    if(newMap[i][j] > 0 && !isVisited[i][j]) {
                        q.add(new Pointer(i,j));
                        BFS();
                        group_count ++;
                    }
                }
            }
            if(group_count == 0 || group_count > 1) break;

            melt1year();
            year++;
        }

        if(group_count == 0) System.out.print(0);
        else
            System.out.println(year);
        sc.close();

    }


    static void BFS() {
        while(!q.isEmpty()) {
            Pointer cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            for(int i =0; i <4; i++) {
                int nextX = x + xWay[i];
                int nextY = y + yWay[i];
                if(isAvailable(nextX, nextY) && newMap[nextX][nextY] > 0) {
                    q.add(new Pointer(nextX, nextY));
                    isVisited[nextX][nextY] = true;
                }
            }
        }
    }

    static void melt1year() {

        for(int i = 0; i< N; i++) {
            for(int j =0; j < M; j++) {
                ogMap[i][j] = newMap[i][j];
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j =0; j < M; j++) {
                if(ogMap[i][j] > 0) {
                    for(int z = 0; z < 4; z ++) {
                        int x = i + xWay[z];
                        int y = j + yWay[z];

                        if(ogMap[x][y] == 0 && newMap[i][j] > 0) {
                            newMap[i][j]--;
                        }
                    }
                }
            }
        }
    }


    static boolean isAvailable(int x, int y) {
        if(0 <= x && x < N && 0 <= y && y < M) {
            if(!isVisited[x][y])
                return true;
        }
        return false;
    }

    static class Pointer{
        int x ;
        int y ;
        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
