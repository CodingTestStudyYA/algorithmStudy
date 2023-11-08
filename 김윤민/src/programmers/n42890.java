package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class n42890 {
	public static void main(String[] args) {
		String[][] arr = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		System.out.println(solution(arr));
	}
	static ArrayList<Integer> CK;
	public static int solution2(String[][] relation) {
		CK = new ArrayList<>();
		int n = relation.length;
		int m= relation[0].length;
		//비트마스킹 사용.
		//4열이라고 한다면, 1<<m = 10000 이므로 모든 경우가 포함됨.
		for(int i=1;i< (1<<m);i++) {
			Set<String> unique = new HashSet<>();
			for(int j=0;j<n;j++) {//해당 데이터에 대해서 
				StringBuilder sb= new StringBuilder();
				for(int c=0;c<m;c++) {
					if((i&(1<<c))>0) { //i인 상태에서 현재 c열이 포함되는 상태라면.
						//만약 i=0100인데 c가 2면 포함되는 상태 ( 뒤에서부터 셈 )
						//i = 0010, 1<<c = 100
						// i&(1<<c) = 100 > 0  해당함.
						sb.append(relation[j][c]);
					}
				}
				unique.add(sb.toString());//저장.
			}
			if(unique.size()!=n) continue; //중복되면 넘어가고
			//아니면 해당 조합에 대한 최소성 확인.
			checkM(i);
		}
		return CK.size();
	}
	public static void checkM(int i) {
		for (int ck : CK) {
			if((ck&i)==ck) return;//중복되는 경우 바로 찾을 수 있다.
			//만약 ck에 이미 0110이 들어가있고,
			//새로운 값 i가 1110이라면
			//ck&i = 0110 즉 ck값이 되므로 중복됨을 바로 알 수 있음.
			
			//i값이 0부터 차례대로 올라가기 때문에 최소값을 찾기위한 조건에 알맞게 접근 가능.
		}
		CK.add(i);
	}
//존나 어케풀지

	/*
	 * 1. 각 컬럼이 1개 자체로 후보키가 되는가? 2. 안되는 애들끼리 모았을때는 무조건 2개부터 시작. 만약 다음 배열이랑 계산했는데 최소키
	 * 조건 만족 -> ++하고 return 안되면 ..
	 * 
	 * 묶는 개수를 점차 늘려가면 안되나?
	 */
	static ArrayList<Integer> ck;
	static int ans;
	public static boolean[] visit;
	public static int solution(String[][] relation) {
		int answer = 0;
		int n = relation.length;
		int m = relation[0].length;
		ck = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			ck.add(i);
		}
		for (int j = 0; j < m; j++) {
			ArrayList<String> tmp = new ArrayList<>();
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				String now = relation[i][j];
				if (tmp.contains(now)) {
					flag = true;
					break;
				}
				tmp.add(now);
			}
			if (!flag) {
				ck.remove((Integer) j);
			}
		}
		ans += m - ck.size();
		visit = new boolean[ck.size()];
		for(int i=2;i<=ck.size();i++) {
			COUNT=i;
			dfs(0,0,relation);
		}
		
		return ans;
	}
	static int COUNT;
	public static void dfs(int cnt, int idx,String[][] relation) {
		if(cnt==COUNT) {
			ArrayList<String> tmp = new ArrayList<>();
			for(int i=0;i<relation.length;i++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0;j<ck.size();j++) {
					if(visit[j]) sb.append(relation[i][ck.get(j)]);
				}
				if(tmp.contains(sb.toString())) return;
				else tmp.add(sb.toString());
			}
//			System.out.println(COUNT+" ");
//			for(int j=0;j<ck.size();j++) {
//				if(visit[j]) System.out.print(j+" ");
//			}
//			System.out.println();
			ans++;
			return;
		}
		for (int i = idx; i < ck.size(); i++) {
			if(visit[i]) continue;
			visit[i]=true;
			dfs(cnt+1,i+1,relation);
			visit[i]=false;
		}

	}
	public static void check(int num) {
		
	}
}
