package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DFSNBFS {


    static int N, M, v;
    static int[][] map ;
    static Queue<Integer> queue;
    static boolean[] isVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        isVisited = new boolean[N+1];
        queue = new LinkedList<>();

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }

        DFS(v);
        System.out.println();
        Arrays.fill(isVisited, false);
        queue.add(v);
        isVisited[v] = true;
        BFS();



    }


    static void BFS(){
        while(!queue.isEmpty()){
            int x = queue.poll();
            System.out.print(x + " ");
            for(int i =0; i< N+1; i++){
                if(map[x][i] == 1 && !isVisited[i]){
                    queue.add(i);
                    isVisited[i] = true;
                }
            }
        }
    }

    static void DFS(int x){
        if(isVisited[x])
            return ;
        isVisited[x] = true;
        System.out.print(x + " ");

        for(int i = 0; i< N+1; i++){
            if(map[i][x] == 1 && !isVisited[i]) {
                DFS(i);
            }
        }
    }

}
