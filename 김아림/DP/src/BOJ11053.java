import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] map;
	static int[] dp; // i번째까지 왔을때 순열의 최대 길이
	static int max;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1];
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}

		max = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (map[j] < map[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}

			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
	}
}

