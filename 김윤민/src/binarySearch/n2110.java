package binarySearch;
import java.io.*;
import java.util.*;
//공유기
public class n2110 {
	
	public static void main(String[] args) throws Exception{
		// 집 N개, 좌표 겹치지 않음
		// 공유기 C개
		// 한 집에 공유기 하나.
		// 가장 인접한 두 공유기 사이 거리 최대
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sT= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(sT.nextToken());
		int m = Integer.parseInt(sT.nextToken());
		
		int[] house = new int[n];
		//공유기는 2개부터.
		for(int i=0;i<n;i++) {
			house[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(house); //집 위치 sort
		
		//공유기의 개수도 랜덤.
		
		int st = 1;
		int ed= house[n-1]-house[0]+1; //최대거리 (2개일 때)
		int mid=0;
		while(st<ed) {
			//mid : 공유기 간 거리
			mid = (st+ed)/2;
			//큰 수 중에 가장 가까운 수 탐색
			//첫 번째에 공유기는 고정. 이후부터 탐색
			int cnt=1;
			int prev = house[0]; //이전 설치?
			for(int i=1;i<n;i++) {
				int now = house[i]; 
				if(now-prev>=mid) { //설치 가능하다면 count하고 
					cnt++;
					prev =now; //이전 위치 저장
				}
			}
			if(cnt<m) { //m개보다 숫자가 작으면 거리를 더 줄인다.
				ed=mid;
			}else { //많으면 거리 늘린다. mid+1부터 탐색.
				st = mid+1;
			}
		}
		
		System.out.println(ed-1);//ed 이전 거리가 가능한 최대거리.
	}
}