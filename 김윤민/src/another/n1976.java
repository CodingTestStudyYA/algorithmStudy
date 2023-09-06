package another;
import java.io.*;
import java.util.*;
//여행가자
public class n1976 {
	static int N,M;//N : 전체 도시수, M : 여행 계획에 속한 도시들의 수
	static int[] parents; //부모
	public static void main(String[] args) throws Exception{
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine());
		prepare(); //초기화
		for(int i=0;i<N;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				//받아 오면서, 1이면 union(합친다)
				if(Integer.parseInt(st.nextToken())==1) { 
					union(i,j);
				}
			}
		}
		StringTokenizer st= new StringTokenizer(br.readLine());
		//이전값을 미리 저장해두고,
		int prev =Integer.parseInt(st.nextToken());
		for(int i=0;i<M-1;i++) {
			int now = Integer.parseInt(st.nextToken());//다음값과 비교하면서
			if(find(prev-1)!=find(now-1)) {//부모가 다르면 NO
				System.out.println("NO");
				return;
			}
			prev = now;
		}
		System.out.println("YES"); //모두 부모가 같으면 YES출력

	}
	public static void prepare() {
		parents = new int[N];
		for(int i=0;i<N;i++) {
			parents[i]=i;
		}
	}
	public static int find(int n) {
		if(parents[n]==n)return n;
		else return parents[n]=find(parents[n]);
	}
	public static boolean union(int a,int b) {
		int ap = find(a);
		int bp = find(b);
		if(ap==bp) return false; //부모가 같으면 union못하니까 false
		parents[bp]=ap;
		return true;
	}

}
