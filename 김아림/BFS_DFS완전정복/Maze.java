package DFS_BFS;

import java.util.*;
public class Maze {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] xWay = {-1, 1, 0, 0};
    static int[] yWay = {0, 0, -1, 1};
    static Queue<Pointer> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        isVisited = new boolean[N][M];

        for(int i = 0; i< N; i++) {
            String str = sc.next();
            for(int j = 0; j< M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        isVisited[0][0] = true;
        bfs(0, 0);
        System.out.println(map[N -1][M -1]);

    }

    public static void bfs(int x, int y) {
        q = new LinkedList<Pointer>();
        q.add(new Pointer(x,y));

        while(!q.isEmpty()) {
            Pointer cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int i=0;i<4;i++) {
                int nextX = curX + xWay[i];
                int nextY = curY + yWay[i];

                if(nextX>=0 && nextX< N && nextY>=0 && nextY< M) {
                    if(map[nextX][nextY]==1 && !isVisited[nextX][nextY]) {
                        q.add(new Pointer(nextX, nextY));
                        isVisited[nextX][nextY]=true;
                        map[nextX][nextY] = map[curX][curY]+1;
                    }
                }
            }
        }
    }


    static class Pointer{

        int x;
        int y;
        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}