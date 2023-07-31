package DP;
import java.io.*;
import java.util.*;
public class n9095 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(br.readLine());
			int[] dp=new int[num+1];
			dp[1]=1;
			//이게 맞나..?
			if(num==2||num==1) {
				System.out.println(num);
				continue;
			}else if(num==3) {
				System.out.println(4);
				continue;
			}
			dp[2]=2;
			dp[3]=4;
			for(int j=4;j<=num;j++) {
				dp[j]=dp[j-1]+dp[j-2]+dp[j-3];
			}
			System.out.println(dp[num]);
		}
	}

}