package DFS_BFS;

import java.io.*;
import java.util.*;

public class Beer {

    static int T, N;
    static Pointer home, fest;
    static ArrayList<Pointer> destination;
    static Queue<Pointer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T; i++){
            destination = new ArrayList<>();
            queue = new LinkedList<>();
            N = Integer.parseInt(br.readLine());

            // home
            StringTokenizer st = new StringTokenizer(br.readLine());
            home = new Pointer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            queue.add(home);
            // mart
            for(int j = 0; j < N; j++){
                st = new StringTokenizer(br.readLine());
                destination.add(new Pointer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            // fest
            st = new StringTokenizer(br.readLine());
            fest = new Pointer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            BFS();
        }
    }


    static void BFS() {
        // 1000미터 가기전에 다른 편의점이 있기만 하면 됨 혹은 페스티벌 장소에 도착
        while(!queue.isEmpty()) {
            Pointer cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            if((Math.abs(x - fest.x) + Math.abs(y - fest.y)) <= 1000) {
                System.out.println("happy");
                return;
            }
            for(int i = 0; i < destination.size(); i++) {
                if((Math.abs(x - destination.get(i).x) + Math.abs(y - destination.get(i).y)) <= 1000) {
                    queue.add(new Pointer(destination.get(i).x, destination.get(i).y));
                    destination.remove(i);
                }
            }
        }

        System.out.println("sad");
    }
}

class Pointer {
    int x, y;

    public Pointer(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
