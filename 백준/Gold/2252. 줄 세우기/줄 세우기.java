import java.io.*;
import java.util.*;

public class Main {
	public static int[] degree;
	public static List<List<Integer>> graph;
	public static int[] result;
	public static int n;
	public static int m;
	
	public static boolean topologySort() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) queue.add(i);
		}
		
		//n개 노드를 탐방
		for(int i = 1; i <= n; i++) {
			//이때, 큐가 비어있다면 사이클이 발생했다.
			if(queue.isEmpty()) {
				return false;
			}
			
			int x = queue.pop();
			result[i] = x;
			
			for(int next : graph.get(x)) {
				degree[next]--;
				
				if(degree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		
		degree = new int[n+1];
		result = new int[n+1];
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n ; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i< m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			graph.get(a).add(b);
			
			degree[b]++;
		}
		
		topologySort();
		
		for(int i = 1; i <= n; i++) {
			System.out.print(result[i] + " ");
		}

	}

}
