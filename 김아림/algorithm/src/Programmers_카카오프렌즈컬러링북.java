
import java.util.*;
import java.io.*;

class Solution {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visit;

    // bfs로 푸는 문제인듯
    // 영역의 총 개수는 ? 큐에서 하나씩 꺼내면서 +1
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;

        visit = new boolean[m][n];

        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ;j++){
                if(!visit[i][j] && picture[i][j] != 0){
                    numberOfArea ++;
                    int cnt = BFS(i, j, picture[i][j], m,n, picture);
                    //System.out.println(cnt);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
                }
            }
        }

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    static int BFS(int i, int j, int value, int m, int n, int[][] picture){
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i,j}); // 처음 시작점
        visit[i][j] = true;
        // value가 같은 애들까지만 돌기
        while(!q.isEmpty()){
            cnt++;
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            //System.out.println(y + "," + x);
            for(int d = 0; d < 4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny < 0 || ny >= m || nx < 0 || nx >= n) continue;

                if(picture[ny][nx] == value && !visit[ny][nx]){
                    q.offer(new int[] {ny ,nx});
                    visit[ny][nx] = true;
                }
            }
        }

        return cnt;
    }
}