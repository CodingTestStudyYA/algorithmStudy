package another;
import java.io.*;
import java.util.*;
//수 묶기
public class n1744 {
	/*
	 * 수는 항상 한번만 묶거나, 묶지 않아야 한다.
	 * 
	 */
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> plus = new ArrayList<>();
		ArrayList<Integer> minus = new ArrayList<>();
		boolean zero = false;
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp>0) {
				plus.add(tmp);
			}else if(tmp<0){
				minus.add(tmp);
			}else {
				zero=true;
			}
			
		}
		//input end
		
		//logic
		Collections.sort(plus,Collections.reverseOrder());
		Collections.sort(minus);
		int result=0;
		
		//양수
		while(plus.size()!=0) {
			if(plus.size()==1) {
				result+=plus.get(0);
				plus.remove(0);
			}else {
				int a = plus.get(0);
				int b = plus.get(1);
				if(a==1||b==1) {
					result += (a+b);
				}else {
					result+= (a*b);
				}
				
				plus.remove(1);
				plus.remove(0);
			}
		}
		/* 음수
		 * 남은 가장 큰 음수를 0과 곱함. 
		 */
		while(minus.size()>1) {
			result+=(minus.get(0)*minus.get(1));
			minus.remove(1);
			minus.remove(0);
		}
		if(minus.size()==1) {
			if(!zero) {
				result+=minus.get(0);
			}
		}
		
		System.out.println(result);
	}
}
