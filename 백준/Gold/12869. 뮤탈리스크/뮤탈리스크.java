import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static int n;
	public static List<int[]> def;
	public static int start[];
	
	public static int bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		boolean[][][] visited = new boolean[61][61][61];
		
		visited[start[0]][start[1]][start[2]] = true;
		
		queue.add(new int[] {start[0],start[1],start[2],0});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			if(tmp[0] <= 0 && tmp[1] <= 0 && tmp[2] <= 0) {
				return tmp[3];
			}
			
			for(int[] dir : def) {
				int a = tmp[0] - dir[0] < 0 ? 0 : tmp[0] - dir[0];
				int b = tmp[1] - dir[1] < 0 ? 0 : tmp[1] - dir[1];
				int c = tmp[2] - dir[2] < 0 ? 0 : tmp[2] - dir[2];
				
				if(visited[a][b][c]) continue;
				visited[a][b][c] = true;
				
				queue.add(new int[] {a,b,c, tmp[3] + 1});
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		def = new ArrayList<>();
		start = new int[3];
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			start[i] = Integer.parseInt(input[i]);
		}
		
		def.add(new int[] {9,3,1});
		def.add(new int[] {9,1,3});
		def.add(new int[] {3,1,9});
		def.add(new int[] {3,9,1});
		def.add(new int[] {1,3,9});
		def.add(new int[] {1,9,3});
		
		System.out.println(bfs());
	}
}
