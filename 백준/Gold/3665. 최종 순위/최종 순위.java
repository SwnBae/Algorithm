import java.io.*;
import java.util.*;

public class Main {
	public static int[] arr; // 기존 결과를 담음
	public static int[] degree;
	public static List<List<Integer>> graph;
	public static int n;
	public static int m;
	
	public static String topologySort() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		
		//n개 노드를 탐방
		for(int i = 1; i <= n; i++) {
			//이때, 큐가 비어있다면 사이클이 발생했다.
			if(queue.isEmpty()) {
				return "IMPOSSIBLE";
			}
			
			int x = queue.pop();
			sb.append(x + " ");
			
			for(int next : graph.get(x)) {
				degree[next]--;
				
				if(degree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		sb.setLength(sb.length() - 1);
		
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			
			graph = new ArrayList<>();
			degree = new int[n+1];
			arr = new int[n];
			
			for(int i = 0; i <= n ; i++) {
				graph.add(new ArrayList<>());
			}
			
			String[] input = br.readLine().split(" ");
			
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(input[i]);
			}
			
			// 간선 정의
			for(int i = 0; i < n-1; i++) {
				int a = arr[i];
				
				for(int j = i + 1; j < n; j++) {
					int b = arr[j];
					
					graph.get(a).add(b);
					degree[b]++;
				}
			}
			
			// 바뀌는 규칙
			m = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < m; i++) {
				String[] change = br.readLine().split(" ");
				int a = Integer.parseInt(change[0]);
				int b = Integer.parseInt(change[1]);
				
				if(graph.get(a).contains(b)) {
					graph.get(a).remove((Integer)b);
					graph.get(b).add(a);
					
					degree[a]++;
					degree[b]--;
				} else {
					graph.get(b).remove((Integer)a);
					graph.get(a).add(b);
					
					degree[a]--;
					degree[b]++;
				}
				
			}
			
			String result = topologySort();
			
			System.out.println(result);
		}
		
	}

}
