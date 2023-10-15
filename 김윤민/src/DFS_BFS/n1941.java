package DFS_BFS;
import java.io.*;
import java.util.*;
public class n1941 {
	static char map[][] = new char[5][5];
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static int combX[] = new int[25];
    static int combY[] = new int[25];
    static int ans = 0;
    
    public static void main(String[] args)throws Exception {
    	//input start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<5; i++) {
        	map[i]= br.readLine().toCharArray();
        }
        // 좌표
        for(int i=0; i<25; i++) {
            combX[i] = i % 5;
            combY[i] = i / 5;
        }
        
        combination(new int[7], 0,0,7);
        System.out.println(ans);
        
    }
    //조합.
    //comb : 조합 상태, idx : 현재 몇번째?, depth : 얼만큼 진행?, left : 남은 자릿수
    
    public static void combination(int[] comb, int idx, int depth, int left ) {
        if(left == 0) { //조합이 완료된 경우 
            bfs(comb); //인접하는지 bfs진행
            return;
        }
        
        if(depth == 25) return; //25명 다 탐색했다면 return
        
        comb[idx] = depth;
        combination(comb, idx+1, depth+1, left-1);    // 선택한 경우
        combination(comb, idx, depth+1, left);    // 선택하지 않은 경우
    }
    
    public static void bfs(int comb[]) {
        Deque<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[7];
        
        visited[0] = true;
        q.add(comb[0]); //첫요소 
        int cnt = 1, sCnt = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(map[combY[cur]][combX[cur]] == 'S') sCnt++; //다솜파만 count
            
            for(int i=0; i<4; i++) {
                for(int next=1; next<7; next++) {
                	//6명 다 연결됐는지 확인하고 visit체크해줌
                    if(!visited[next] && combX[cur]+dx[i] == combX[comb[next]] && combY[cur]+dy[i] == combY[comb[next]]) {
                        visited[next] = true;
                        q.add(comb[next]);
                        cnt++;
                    }
                }
            }
        }
        
        //모두 연결됐고, 다솜파가 4명 이상이라면
        if(cnt == 7 && sCnt >=4) {
            ans++;
        }
        
    }
}	
