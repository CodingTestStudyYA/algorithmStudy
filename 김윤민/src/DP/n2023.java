package DP;

import java.io.*;
//신기한 소수
public class n2023 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		//input start
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//input end
		
		// logic
		// 하던대로 공간 선언해서 하면 메모리 초과남.
		// 10^9 까지 경우의 수 저장해야 하기 때문 = 재귀사용
		dfs(0,n);
		System.out.println(sb);
	}

	static boolean isPrime(int n) { // 소수인가?
		if (n == 1)
			return false;
		for (int i = 2; i * i <= n; i++) { // 제곱수까지 소수가 없으면 소수가 맞음. 2부터 쭉
			if (n % i == 0)
				return false;
		}
		return true;
	}

	static void dfs(int n, int cnt) {
		if(cnt==0) {//cnt=0이면 모든 소수인 경우가 완성된 경우기 때문에 sb에 추가.
			sb.append(n).append('\n');
			return;
		}
		for(int i=1;i<10;i++) { //뒷자리수는 1~9가 해당 자리에 올 수 있다.
			int tNum = 10*n+i; //이전 수가 누적되어 내려오는데 이전수*10+내 값이 소수이면
			if(isPrime(tNum)) { // 그 다음수에 대한 dfs진행.
				dfs(tNum,cnt-1);
			}
		}
	}
}
