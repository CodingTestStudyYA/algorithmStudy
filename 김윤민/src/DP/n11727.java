package DP;
import java.io.*;
import java.util.*;
public class n11727 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp=new int[n+1];
		dp[1]=1;
		if(n!=1) {
			dp[2]=3;
			for(int i=3;i<=n;i++) {
				//홀수인 경우 : 짝수의 경우에서 앞에 1칸이 있는 경우와 뒤에 1칸이 있는 경우
				if(i%2==1) {
					dp[i]=(dp[i-1]*2-1)%10007; 
				}else { //짝수인 경우 이전에 홀수에서 뒤에 1 추가한경우 = dp[i-1]
					//dp[i-2]*2 : 홀수의 경우에서 2칸의 경우의 수는 3개
					//하지만 이미 홀수에서 있는경우 3은 빼고 2개의 경우의 수만 곱해준다.
					dp[i]=(dp[i-1]+dp[i-2]*2)%10007;
				}
			}
		}
		System.out.println(dp[n]);

	}

}
