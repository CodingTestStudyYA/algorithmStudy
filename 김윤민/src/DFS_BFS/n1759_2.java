package DFS_BFS;
import java.io.*;
import java.util.*;
public class n1759_2 {
	static int L,C;
	static char[] cList;
	static char[] ans;
	static StringBuilder sb = new StringBuilder();
	/*
	 * L개 소문자
	 * a,e,i,o,u ( 최소 1개 O )
	 * 오름차순 배열 -> 가능한건?
	 */
	public static void main(String[] args) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		L =Integer.parseInt(st.nextToken()); //서로 다른 L개의 알파벳 소문자 ( 목표치 )
		C = Integer.parseInt(st.nextToken()); // 입력치
		
		cList = new char[C];
		st= new StringTokenizer(br.readLine());
		ans =new char[L];
		for(int i=0;i<C;i++) {
			cList[i]= st.nextToken().charAt(0);
		}
		Arrays.sort(cList); //오름차순으로 정렬
		// 오름차순으로 dfs돌면 자동 오름차순 완성
		
		search(0,0);
		System.out.println(sb.toString());
	}
	public static void search(int idx,int cnt) {
		if(cnt==L) {
			int aeiou =0;
			int other =0;
			
			for(int i=0;i<L;i++) {
				if(ans[i]=='a'||ans[i]=='e'||ans[i]=='i'||ans[i]=='o'||ans[i]=='u') {
					aeiou++;
				}else {
					other++;
				}
			}
			if(aeiou>=1&&other>=2) {
				sb.append(ans).append('\n');
			}
			return;
		}
		if(idx>=C) return; //입력치 개수보다 크면 return
		for(int i=idx;i<C;i++) {
			ans[cnt]=cList[i]; //결과 저장
			search(i+1,cnt+1); 
		}
	}
}