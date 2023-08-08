package DP;

import java.util.*;
import java.io.*;
public class n2011 {
	static int num=1000000;
	public static void main(String[] args)throws Exception {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] temp=br.readLine().toCharArray();
		int length= temp.length;
		char[] s = new char[length+1];
		for(int i=1;i<=length;i++) {
			s[i]=temp[i-1];
		}
		//input end
		
		//default 
		int[]dp = new int[length+1];
		int prev = s[1] -'0';//이전 수 저장.
		if(prev!=0) {
			dp[0]=dp[1]=1;
		}
		
		//logic
		//내 값이 0일경우는 가져오면 안됨. 10이나 20의 경우 
		for(int i=2;i<=length;i++) {
			int now = s[i]-'0';
			if(now==0) {//내 자리가 0일때는 10,20이 반드시 와야함.
				if(prev==1||prev==2) { //1,2이면 경우는 하나. 10,20-> 앞2자리의 경우의 수.
					dp[i]=dp[i-2]%num;
				}
			}else {
				dp[i]=dp[i-1];//now값이 1~9일때 경우의 수는 그대로 가져옴
				int tmp = prev*10+now;
				if(tmp>=11&&tmp<=26) { //11~26의 범위에 들어가면,
					dp[i]=(dp[i]%num+dp[i-2]%num)%num; //더해줌.
				}
			}
			prev = now;//prev갱신
		}
		//output
		System.out.println(dp[length]);
	}

}