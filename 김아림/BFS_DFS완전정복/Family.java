package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Family {

    static LinkedList<Integer>[] vertex;
    static boolean[] isVisited;
    static int N, M;
    static int x, y;
    static int[] chone ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        vertex = new LinkedList[N+1];
        isVisited = new boolean[N+1];
        chone = new int[N+1];

        for(int i = 1; i<=N; i++){
            vertex[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i =0 ; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            vertex[p1].add(p2);
            vertex[p2].add(p1);
        }

        DFS(x);
        System.out.println(chone[y] == 0 ? -1 : chone[y]);


    }

    static void DFS(int v){
        isVisited[v] = true;

        for(int i : vertex[v]){
            if(!isVisited[i]){
                chone[i] = chone[v] + 1;
                DFS(i);
                if(i == y){
                    return;
                }
            }
        }
    }
}
