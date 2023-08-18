package backtracking;
import java.io.*;
import java.util.*;

public class n16987 {
	// 계란 내구도 & 무게
	// 계란 치면 각 계란 내구도는 상대만큼 깎임
	// 내구도 0 이하 : 계란 빠자작

	// 가장 왼쪽의 계란 pick

	// 깨지지 않은 다른 계란 중 하나 친다.
	// 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
	// 이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.

	// 가장 최근 계란의 한칸 오른쪽 계란을 들고 반복 진행.
	// 젤 오른쪽이면 과정 종료.

	// 깰 수 있는 계란 최대 개수?
	static Egg[] tmp;
	static int n,  max;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tmp = new Egg[n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tmp[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// input end
		// logic
		dfs(0,0);
		System.out.println(max);
		// 순서를 재정렬 할 필요 없음.

	}

	// 해당 순서에서 계란을 최대 몇 개 깰 수 있어???
	static void dfs(int idx, int cnt) {
		if (idx == n) { // 끝까지 왔으면 cnt비교
			max = Math.max(cnt, max);
			return;
		}
		if (tmp[idx].s == 0 || cnt == n - 1) { // 손에 든계란이 깨졌거나, 다깨졌으면 다음으로 이등.
			dfs(idx + 1, cnt);
			return;
		}
		// 가장 왼쪽의 계란 pick (idx = 0부터 증가)
		Egg now = tmp[idx];
		// 계란 깨진 갯수가 누적이니까 저장하고 나중에 원복에 사용.
		int nCnt = cnt;
		for (int i = 0; i < n; i++) {
			if (idx == i)
				continue;// 같은건 안되니까 넘어감.
			if (tmp[i].s == 0)
				continue; // 내구도가 0이면 안되니까 넘어감
			int targetS = tmp[i].s - now.w; // 부딪혔을 때 타겟 내구도
			int nowS = now.s - tmp[i].w; // 부딪혔을 때 현재 계란 내구도
			int tS = tmp[i].s;
			int nS = now.s;
			
			// 내꺼 아니면 타겟이 깨지면 cnt++ 
			if (targetS <= 0)
				cnt++;
			if (nowS <= 0)
				cnt++;
			tmp[i].s = targetS > 0 ? targetS : 0;
			tmp[idx].s = nowS > 0 ? nowS : 0;
			dfs(idx + 1,cnt);
			//원복 - 다른 계란을 치러갈 때 
			tmp[i].s = tS;
			tmp[idx].s = nS;
			cnt= nCnt;
		}
	}

	static class Egg {
		int s, w;// 내구도, 무게

		Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Egg [s=" + s + ", w=" + w + "]";
		}
	}
}
