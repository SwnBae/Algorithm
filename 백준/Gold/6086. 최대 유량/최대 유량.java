import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = Integer.MAX_VALUE;
	public static final int SIZE = 52;
	public static int n;
	
	public static int[] d;
	public static int[][] flow;
	public static int[][] capacity;
	public static List<List<Integer>> graph;
	
	public static int result;
	
	public static void maxFlow(int start, int end) {
		while(true) {
			Arrays.fill(d, -1);
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			
			d[start] = start;
			queue.add(start);
			
			while(!queue.isEmpty()) {
				int node = queue.poll();
				
				for(int next : graph.get(node)) {
					if(capacity[node][next] - flow[node][next] > 0 && d[next] == -1) {
						queue.add(next);
						d[next] = node;
						
						if(next == end) break;
					}
				}
			}
			
			if(d[end] == -1) break;
			int flw = INF;
			
			for(int i = end; i != start; i = d[i]) {
				flw = Math.min(flw, capacity[d[i]][i] - flow[d[i]][i]);
			}
			
			for(int i = end; i != start; i = d[i]) {
				flow[d[i]][i] += flw;
				flow[i][d[i]] -= flw;
			}
			
			result += flw;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		d = new int[SIZE];
		flow = new int[SIZE][SIZE];
		capacity = new int[SIZE][SIZE];
		result = 0;
		
		graph = new ArrayList<>();
		
		for(int i = 0; i < 52; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int a = input[0].matches("[A-Z]") ? input[0].charAt(0) - 'A' : input[0].charAt(0) - 'a' + 26;
			int b = input[1].matches("[A-Z]") ? input[1].charAt(0) - 'A' : input[1].charAt(0) - 'a' + 26;
			int c = Integer.parseInt(input[2]);
			
			graph.get(a).add(b);
			graph.get(b).add(a); // 음의 유량 체크
			
			capacity[a][b] += c;
			capacity[b][a] += c;
		}
		
		maxFlow(0,25);
		
		System.out.println(result);
	}

}
