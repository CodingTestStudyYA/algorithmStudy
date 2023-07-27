package DFS_BFS;
import java.io.*;
import java.util.*;

public class StartLink {

    static int now, end, dest;
    static int[] dy;
    static boolean[] isVisited;
    static int[] btn_clicked;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        end = Integer.parseInt(st.nextToken());
        now = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
        dy = new int[] {Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())};

        isVisited = new boolean[end+1];
        btn_clicked = new int[end+1];
        q = new LinkedList<Integer>();
        q.add(now);
        isVisited[now] = true;
        BFS();

//		System.out.println(btn_clicked[dest] == 0 ? "use the stairs" : btn_clicked[dest]); // 이렇게 하면 안됨 ^^&... 바로 dest일 경우를 생각
        System.out.println(isVisited[dest] ?  btn_clicked[dest] : "use the stairs" );


    }



    static void BFS() {

        while(!q.isEmpty()) {

            int x = q.poll();
            for(int i = 0; i<2; i++) {
                int nx = x + dy[i];
                if(1<= nx && nx <=end && !isVisited[nx]) {
                    btn_clicked[nx] = btn_clicked[x] + 1;
                    isVisited[nx] = true;
                    if(nx == dest)return;

                    q.add(nx);
                }
            }
        }

    }
}