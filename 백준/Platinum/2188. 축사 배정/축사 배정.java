import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 1;
	public static final int START = 401;
	public static final int END = 402;
	
	public static int[] d;
	public static int[][] flow;
	public static int[][] capacity;
	public static int n;
	public static int m;
	
	public static List<List<Integer>> graph;
	
	public static int result;
	
	public static void maxFlow() {
		while(true) {
			Arrays.fill(d, -1);
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			
			queue.add(START);
			
			while(!queue.isEmpty()) {
				int node = queue.poll();
				
				for(int next : graph.get(node)) {
					if(capacity[node][next] - flow[node][next] > 0 && d[next] == -1) {
						queue.add(next);
						d[next] = node;
						
						if(next == END) break;
					}
				}
			}
			
			if(d[END] == -1) break;
			int flw = INF;
			
			for(int i = END; i != START; i = d[i]) {
				flw = Math.min(flw, capacity[d[i]][i] - flow[d[i]][i]);
			}
			
			for(int i = END; i != START; i = d[i]) {
				flow[d[i]][i] += flw;
				flow[i][d[i]] -= flw;
			}
			
			result += flw;
		}
	}

	//start = 401, end = 402
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		flow = new int[403][403];
		capacity = new int[403][403];
		d = new int[403];
		
		result = 0;
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= 402; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			graph.get(401).add(i);
			
			for(int j = 0; j < Integer.parseInt(input[0]); j++) {
				int b = Integer.parseInt(input[j + 1]) + 200;
				
				graph.get(i).add(b);
				graph.get(b).add(i);
				graph.get(b).add(402);
				
				capacity[401][i] = 1;
				capacity[i][b] = 1;
				capacity[b][402] = 1;
			}
		}
		
		maxFlow();
		
		System.out.println(result);

	}

}
