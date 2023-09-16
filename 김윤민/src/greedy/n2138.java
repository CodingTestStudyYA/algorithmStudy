package greedy;
import java.io.*;
import java.util.*;

//전구와 스위치
public class n2138 {
	// 현재자리에서 이전자리를 봤을 때,
	// 이전자리가 일치하지 않으면 이전자리를 일치시켜주고 넘어간다고 생각하면 됨.
	// 그럼 그 이전자리에 대해서는 다시 탐색 안해도 됨 -> 왜냐면 같도록 맞춰주고 넘어갔으니까
	// 따라서, 이전값을 비교하고 현재 위치를 변경해주면 됨!
	
	static int N, min = Integer.MAX_VALUE;
	static boolean[] target;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s1 = br.readLine();
		String s2 = br.readLine(); // 타겟

		boolean[] firstTrue = new boolean[N]; //첫 전구 변경
		boolean[] firstFalse = new boolean[N]; //변경 x
		target = new boolean[N];
		for (int i = 0; i < N; i++) {
			firstTrue[i]= (s1.charAt(i)=='0')?false:true;
			firstFalse[i]= (s1.charAt(i)=='0')?false:true;
			target[i]= (s2.charAt(i)=='0')?false:true;
		}
		//first전구를 바꿨을때의 경우
		firstTrue = change(firstTrue,0);
		greedy(firstTrue,1);
		
		//first전구를 건드리지 않은 경우
		greedy(firstFalse,0);
		
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}

	public static void greedy(boolean[] arr, int cnt) {
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] != target[i - 1]) { //이전을 봤을때 같지않다면
				arr = change(arr, i); //현재 자리의 스위치를 켜서 같게 해준다. 
				cnt++;
			}
		}
		if (arr[N - 1] == target[N - 1]) { //N-2까지 일치시켜준 다음 N-1을 봤을때 같으면 목적에 도달한 경우.
			min = Math.min(cnt, min);
		}

	}

	public static boolean[] change(boolean[] arr, int idx) {
		for (int i = idx - 1; i <= idx + 1; i++) {
			if (i < 0 || i >= N)
				continue;
			arr[i] = !arr[i];
		}
		return arr;
	}
}
