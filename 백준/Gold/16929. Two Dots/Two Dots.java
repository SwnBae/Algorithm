import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static int n;
	public static int m;
	public static String[][] space;
	public static boolean[][] visited;
	public static boolean have;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static boolean notSame(String a, String b) {
		return !a.equals(b);
	}
	
	public static void dfs(String value, int preY, int preX, int y, int x) {
		if(have) return;
		
		if(visited[y][x]) {
			have = true;
			return;
		}
		
		visited[y][x] = true;
		
		for(int i = 0; i < 4; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];
			
			//1. 범위 체크, 2. 되돌아가기 x, 3. 값이 다른 경우
			if(outBound(tmpY, tmpX) || (preY == tmpY && preX == tmpX) 
					|| notSame(value, space[tmpY][tmpX])) continue;
			
			dfs(value, y, x, tmpY, tmpX);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		space = new String[n][m];
		visited = new boolean[n][m];
		have = false;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for(int j = 0; j < m; j++) {
				space[i][j] = input[j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(have) break;
			
			for(int j = 0; j < m; j++) {
				if(!visited[i][j]) {
					dfs(space[i][j], -1, -1, i, j);
				}
			}
		}
		
		System.out.println(have ? "Yes" : "No");

	}

}
