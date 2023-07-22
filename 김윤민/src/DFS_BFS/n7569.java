package DFS_BFS;

import java.io.*;
import java.util.*;

public class n7569 {
	// 6방향 위, 아래, 오른 , 왼, 앞, 뒤
	// [H][N][M] & [z][y][x]
	static int[] dx = { 0, 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, 0, 1, -1 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };
	// 토마토 담긴 박스
	static int[][][] boxs;
	// 해당 토마토가 완숙 된 날짜
	static int[][][] day;

	// M,N,H
	static int M, N, H;
	// 결과값
	static int year;
	// 익은 토마토 리스트
	static ArrayList<pos> tomato;

	public static void bfs() {
		Queue<pos> q = new LinkedList<>();
		for (pos p : tomato) {
			q.add(p);
		}
		while (!q.isEmpty()) {
			pos now = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int nz = now.z + dz[i];
				if (can(nx,ny,nz)) {
					boxs[nz][ny][nx] = 1;
					day[nz][ny][nx] = day[now.z][now.y][now.x] + 1;
					year = Math.max(year, day[nz][ny][nx]);
					q.add(new pos(nz, ny, nx));
				}
			}

		}
	}

	public static boolean can(int x, int y, int z) { // z = H, y = N, x = M
		if (x >= 0 && y >= 0 && z >= 0 && z < H && y < N && x < M && boxs[z][y][x] == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// M,N,H
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		// 생성
		boxs = new int[H][N][M];
		day = new int[H][N][M];
		tomato = new ArrayList<>();

		int wall = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					boxs[i][j][k] = Integer.parseInt(st.nextToken());
					if (boxs[i][j][k] == 1) {
						tomato.add(new pos(i, j, k));
					} else if (boxs[i][j][k] == -1) {
						wall++;
					}
				}
			}
		}

		if ((wall + tomato.size()) != (H * N * M)) { // 모두 익은 상태가 아니면 bfs
			// bfs돌리고 만약 0 이 있는지 count해야함....-?for문 또..돌려?
			year = 0;
			bfs();
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						if (boxs[i][j][k] == 0)
							year = -1;
					}
				}
			}
			System.out.println(year);

		} else { // 모두 익은 상태면 0 출력
			System.out.println("0");
		}

	}

}

//토마토 위치
class pos {
	public int x, y, z;

	pos(int z, int y, int x) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
