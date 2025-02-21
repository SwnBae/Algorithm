import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static final int RED = 1;
	public static final int BLUE = -1;
	public static List<List<Integer>> graph;
	public static boolean result;
	public static int[] visited;
	
	public static void dfs(int node, int color) {
		if(result == false) {
			return;
		}
		
		visited[node] = color;
		
		for(int nd : graph.get(node)) {
			if(visited[nd] == color) {
				result = false;
				return;
			}
			
			if(visited[nd] == 0) {
				dfs(nd, color * -1); // 다음 
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < tc; t++) {
			String[] ve = br.readLine().split(" ");
			
			int v = Integer.parseInt(ve[0]);
			int e = Integer.parseInt(ve[1]);
			
			visited = new int[v+1];
			graph = new ArrayList<>();
			
			result = true;
			
			for(int i = 0; i <= v; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i = 0; i < e; i++) {
				String[] input = br.readLine().split(" ");
				
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				
				graph.get(a).add(b);
				graph.get(b).add(a);
				
			}
			
			for(int i = 1; i <= v; i++) {
				if(result == false) break;
				
				if(visited[i] == 0) {
					dfs(i,RED);
				}
				
			}
			
			System.out.println(result ? "YES":"NO");
		}
		

	}

}
