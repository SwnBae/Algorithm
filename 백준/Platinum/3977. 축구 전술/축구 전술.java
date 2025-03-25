import java.io.*;
import java.util.*;

public class Main {
	public static int id;
	public static int[] d;
	public static boolean[] finished;
	public static int[] sccIdx;
	
	public static int[] degree;
	
	public static List<List<Integer>> graph;
	public static List<List<Integer>> sccList;
	public static List<List<Integer>> sccGraph;
	public static ArrayDeque<Integer> stack;
	
	public static Set<Integer> result;
	
	public static int n;
	
	public static int dfs(int node) {
		d[node] = ++id;
		stack.add(node);
		
		int parent = d[node];
		
		for(int next : graph.get(node)) {
			if(d[next] == 0) { // 아직 탐색하지 않은 곳
				parent = Math.min(parent, dfs(next));
			} else if(!finished[next]) {
				parent = Math.min(parent, d[next]);
			}
		}
		
		if(parent == d[node]) {
			List<Integer> scc = new ArrayList<>();
			
			while(true) {
				int tmp = stack.pollLast();
				
				finished[tmp] = true;
				scc.add(tmp);
				sccIdx[tmp] = sccList.size();
				
				if(tmp == node) break;
			}
			
			sccList.add(scc);
		}
		
		return parent;
	}
	
	public static void culculateDegree() {
		//관계 초기화
		for(int i = 0; i < sccList.size(); i++) {
			sccGraph.add(new ArrayList<>());
		}
		
		degree = new int[sccList.size()];
		
		for(int cur = 0; cur < n; cur++) {
			for(int next : graph.get(cur)) {
				if(sccIdx[cur] != sccIdx[next]) {
					sccGraph.get(sccIdx[cur]).add(sccIdx[next]);
					degree[sccIdx[next]]++;
				}
				
			}
		}
	}
	
	public static void calculateStart() {
		List<Integer> tmpScc = new ArrayList<>();
		
		for(int i = 0; i < sccList.size(); i++) {
			if(degree[i] == 0) tmpScc.add(i);
		}
		
		for(int sccNum : tmpScc) {
			if(topologySort(sccNum)) {
				for(int n : sccList.get(sccNum)) {
					result.add(n);
				}
			}
		}
	}
	
	public static boolean topologySort(int start) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		
		deque.add(start);
		
		for(int i = 0; i < sccList.size(); i++) {
			if(deque.isEmpty()) {
				return false;
			}
			
			int node = deque.poll();
			
			for(int next : sccGraph.get(node)) {
				degree[next]--;
				
				if(degree[next] == 0) {
					deque.add(next);
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String[] nm = br.readLine().split(" ");
			
			id = 0;
			
			n = Integer.parseInt(nm[0]);
			int m = Integer.parseInt(nm[1]);
			
			graph = new ArrayList<>();
			sccList = new ArrayList<>();
			sccGraph = new ArrayList<>();
			
			stack = new ArrayDeque<>();
			result = new TreeSet<>();
			
			d = new int[n];
			finished = new boolean[n];
			sccIdx = new int[n];
			
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i = 0; i < m; i++) {
				String[] input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				
				graph.get(a).add(b);
			}
			
			//빈줄
			if(t != tc) {
				br.readLine();
			}
			
			
			for(int i = 0; i < n; i++) {
				if(!finished[i]) dfs(i);
			}
			
			culculateDegree();
			calculateStart();
			
			if(result.size() == 0) {
				System.out.println("Confused\n");
			} else {
				StringBuilder sb = new StringBuilder();
				
				for(int node : result) {
					sb.append(node + "\n");
				}
				
				System.out.println(sb.toString());
			}
			
		}
	}

}
