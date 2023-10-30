package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//주차요금 계산 (미완성)
public class n92341 {
	/*
	 * 출차된 내역이 없다면, 23:59에 출차된 것으로 간주 누적주차 <= 기본시간 : 기본요금 누적주차 > 기본시간 : 기본요금 + 초과시간
	 * * 단위요금 기본적으로 올림.
	 */
	public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		System.out.println(Arrays.toString(solution(fees,records)));
	}

	// 0 : 기본시간, 1 : 기본요금, 2 : 단위 시간, 3 : 단위 요금
	static Map<Integer, Parking> park;
	static Map<Integer,Integer> result;
	public static int[] solution(int[] fees, String[] records) {
		park = new HashMap<>();
		int size = records.length;
		result = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String[] record = records[i].split(" ");
			// 시각, 차량번호, 내역
			int carNum = Integer.parseInt(record[1]);
			switch (record[2]) {
			case "IN":
				park.put(carNum, new Parking(record[0], carNum));
				break;
			case "OUT":
				Parking now = park.get(carNum);
//				result.put(carNum, result.getOrDefault(carNum, getResult()))
				park.get(carNum).out = record[0];
				break;
			}
		}
		PriorityQueue<Parking> q = new PriorityQueue<>((e1,e2)-> e1.carNum-e2.carNum);
		for (Integer key  : park.keySet()) {
			Parking now = park.get(key);
			int result = getResult(now.in,now.out,fees);
			q.offer(new Parking(now.carNum,result));
		}
		int s = q.size();
		int[] answer = new int[s];
		for(int i=0;i<s;i++) {
			answer[i]=q.poll().money;
		}
		return answer;
	}

	static int getResult(String in, String out, int[] fees) {
		String[] inArr = in.split(":");
		if (out==null) {
			out = "23:59";
		}
		String[] outArr = out.split(":");

		int inH = Integer.parseInt(inArr[0]);
		int inM = Integer.parseInt(inArr[1]);
		int outH = Integer.parseInt(outArr[0]);
		int outM = Integer.parseInt(outArr[1]);

		int m = (outH - inH) * 60 + (outM - inM);
		// 0 : 기본시간, 1 : 기본요금, 2 : 단위 시간, 3 : 단위 요금
		if (fees[0] < m) {
			int result = fees[1];
			result += Math.round((m-fees[0])/fees[2])*fees[3];
			return result;
		}else {
			return fees[1];
		}
	}

	static class Parking {
		String in, out;
		int money, carNum;

		Parking(String in, int carNum) {
			this.in = in;
			this.carNum = carNum;
		}
		Parking( int carNum,int money) {
			this.carNum= carNum;
			this.money=money;
		}
	}
}
