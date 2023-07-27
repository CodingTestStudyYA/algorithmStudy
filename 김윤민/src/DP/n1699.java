package DP;
import java.io.*;
import java.util.*;
public class n1699 {
	
//	public static int dp(int i) {
//		if(i==1) return 1;
//		else if(i==2) return 2;
//		else {
//			
//		}
//	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		ArrayList<Integer> nlist = new ArrayList<>();
		
		//default
		nlist.add(1);
		dp[0]=0; dp[1]=1;
		
		for(int i=2;i<=n;i++) {
			if(Math.sqrt(i)%1==0) {
				nlist.add((int)Math.sqrt(i));
				dp[i]=1;
			}else {
				int min=Integer.MAX_VALUE;
				for(int j:nlist) {
					int pow= (int)Math.pow(j, 2);
					min=Math.min(dp[pow]+dp[i-pow], min);
				}
				dp[i]=min;
			}
		}
		System.out.println(dp[n]);
	}

}