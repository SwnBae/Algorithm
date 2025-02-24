import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int[] dy = { -1, 1, 0, 0 ,1,1,-1,-1};
	public static int[] dx = { 0, 0, -1, 1 ,1,-1,1,-1};
	public static int h;
	public static int w;
	public static int[][] space;
	public static boolean[][] visited;
	public static int cnt;
	
	public static boolean check(int y, int x) {
		return y < 0 || y >= h || x < 0 || x >=w || space[y][x] == 0;
	}

	public static void dfs(int y, int x) {
		//System.out.println(y + ", " + x);
		visited[y][x] = true;
		
		for(int i = 0; i < 8; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];
			
			if(check(tmpY, tmpX) || visited[tmpY][tmpX]) continue;
			
			dfs(tmpY, tmpX);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;

		while ((input = br.readLine().split(" ")) != new String[] { "0", "0" }) {
			h = Integer.parseInt(input[1]);
			w = Integer.parseInt(input[0]);
			
			if(h == 0 && w == 0) break;

			space = new int[h][w];
			visited = new boolean[h][w];
			
			cnt = 0;

			for (int i = 0; i < h; i++) {
				String[] sp = br.readLine().split(" ");
				for (int j = 0; j < w; j++) {
					space[i][j] = Integer.parseInt(sp[j]);
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (space[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
						
						for(int p = 0; p < h; p++) {
							//System.out.println(Arrays.toString(visited[p]));
						}
						//System.out.println();
					}
				}
			}
			
			System.out.println(cnt);
		}

	}

}
