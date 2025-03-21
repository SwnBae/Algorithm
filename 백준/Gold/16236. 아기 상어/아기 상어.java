import java.io.*;
import java.util.*;

public class Main {
	public static int[] dy = { -1, 0, 0, 1 };
	public static int[] dx = { 0, -1, 1, 0 };

	public static int[][] space;
	public static boolean[][] visited;
	public static int n;

	public static int sharkY;
	public static int sharkX;
	public static int sharkSize;
	public static int time;
	public static int eatCount;
	
	public static PriorityQueue<int[]> pq;

	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= n;
	}

	public static void bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] { sharkY, sharkX, time });
		visited[sharkY][sharkX] = true;

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();

			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];

			for (int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];

				// 1. 범위 밖, 2. 방문처리된 것, 3. 사이즈보다 큰 것
				if (outBound(tmpY, tmpX) || visited[tmpY][tmpX] || space[tmpY][tmpX] > sharkSize) {
					continue;
				}

				// 순서 지켜서, 만약 상어라면 이동하고 visit과 큐를 초기화 후에 큐에 다시 넣어주자/
				if (space[tmpY][tmpX] > 0 && space[tmpY][tmpX] < sharkSize) {
					int distance = Math.abs(tmpY - sharkY) + Math.abs(tmpX - sharkX);
					
					pq.add(new int[] {tmpY,tmpX});
					
					find(distance);
					
					int[] next = pq.poll();
					
					sharkY = next[0];
					sharkX = next[1];
					space[sharkY][sharkX] = 0;
					
					eatCount++;
					time = cnt + 1;

					eat();

					visited = new boolean[n][n];
					queue.clear();
					pq.clear();

					visited[sharkY][sharkX] = true;
					queue.add(new int[] { sharkY, sharkX, cnt + 1 });
					
					// 샤크의 위치에서 다시 시작하기 위해 반복을 빠져나간다.
					break;
				}
				
				visited[tmpY][tmpX] = true;
				queue.add(new int[] { tmpY, tmpX, cnt + 1 });
			}
		}
	}
	
	public static void find(int distance) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		boolean[][] dVisited = new boolean[n][n];
		
		queue.add(new int[] {sharkY,sharkX,0});
		dVisited[sharkY][sharkX] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];
			
			if(cnt == distance) {
				if(space[y][x] < sharkSize && space[y][x] > 0) {
					pq.add(new int[] {y,x});
				}
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];

				// 1. 범위 밖, 2. 방문처리된 것, 3. 사이즈보다 큰 것
				if (outBound(tmpY, tmpX) || dVisited[tmpY][tmpX] || space[tmpY][tmpX] > sharkSize) {
					continue;
				}
				
				dVisited[tmpY][tmpX] = true;
				queue.add(new int[] { tmpY, tmpX, cnt + 1 });
			}
		}
	}

	public static void eat() {
		if (sharkSize == eatCount) {
			sharkSize++;
			eatCount = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		space = new int[n][n];
		visited = new boolean[n][n];

		sharkSize = 2;
		time = 0;
		eatCount = 0;
		
		pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				
				return Integer.compare(o1[0], o2[0]);
			}
		});

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				space[i][j] = Integer.parseInt(input[j]);

				if (space[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					
					space[i][j] = 0;
				}
			}
		}
		
		bfs();
		
		System.out.println(time);
	}
}
