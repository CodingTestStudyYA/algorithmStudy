package DFS_BFS;
import java.util.*;
import java.io.*;

public class n2573_iceberg {
    static int[][] map; 
    static int[][] map2;
    static int n,m;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void bfs() {// 빙산이 있으면 감소
        Queue<Point> q = new LinkedList<>();
        visited = new boolean[n][m];
        
        //빙산인 것만 offer
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		if(map[i][j]!=0) {
        			q.offer(new Point(i,j));
        			visited[i][j]=true;
        		}
        		
        	}
        }
        while(!q.isEmpty()) {
        	Point p =q.poll();
        	int sea=0;
        	for(int i=0;i<4;i++) {
    			int nx = p.x+dx[i];
    			int ny= p.y+dy[i];
    			//범위인데 0이면 감소
    			if(nx>=0&&ny>=0&&nx<n&&ny<m&&visited[nx][ny]==false&&map[nx][ny]==0) {
    				sea++;
    			}
    		}
        	if(map[p.x][p.y]<sea) map[p.x][p.y]=0;
        	else map[p.x][p.y]-=sea;
        }

    }
    public static int Solution() {// 섬 개수 구하기
    	int[][] clone = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
    	int cnt=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(clone[i][j]!=0) {
                	cnt++;
                	dfs(i,j,clone);
                }
            }
        }
        return cnt;
    }
    
    public static void dfs(int x,int y,int[][] clone) {
        for(int i=0;i<4;i++) {
        	int nx = x+dx[i];
        	int ny = y+dy[i];
        	if(nx>=0&&nx<n&&ny>=0&&ny<m&&clone[nx][ny]!=0) {
        		clone[nx][ny]=0;
                dfs(nx,ny,clone);
            }
        }
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        int result=0;
        int cnt=0;
        while(cnt<2) {
        	cnt = Solution();
        	if(cnt>=2) break;
        	else if(cnt==0) {
        		result = 0;
        		break;
        	}
        	else {
        		result++;
        	}
        	bfs(); //1년후 빙산 높이 감소
        }
        System.out.println(result);
    }

}