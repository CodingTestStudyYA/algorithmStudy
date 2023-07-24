package DP;
import java.io.*;
import java.util.*;
public class n1463 {
	public static void dp(int[] list,int n) {
		if(n%3==0) list[n/3]=Math.min(list[n/3],list[n]+1);
		if(n%2==0) list[n/2]=Math.min(list[n/2],list[n]+1);
		list[n-1]=Math.min(list[n-1],list[n]+1);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n+1];
		Arrays.fill(list, n);
		list[n]=0;
		for(int i=n;i>=1;i--) {
			dp(list,i);
		}
		System.out.println(list[1]);
		
	}

}