import java.io.*;
import java.util.*;

public class Main {
	public static int id;
	public static int[] d;
	public static boolean[] finished;
	public static List<List<Integer>> graph;
	public static List<List<Integer>> sccList;
	public static ArrayDeque<Integer> stack;
	
	public static int dfs(int node) {
		d[node] = ++id;
		stack.add(node);
		//push는 addFirst와 동일
		
		int parent = d[node];
		
		for(int next : graph.get(node)) {
			if(d[next] == 0) { // 방문했다면?
				parent = Math.min(parent, dfs(next));
			} else if(!finished[next]) { // 방문했지만, dfs 처리중인 정점이라면?
				parent = Math.min(parent, d[next]);
			}
		}
		
		if(parent == d[node]) {
			List<Integer> scc = new ArrayList<>();
			
			while(true) {
				int tmp = stack.pollLast();
				scc.add(tmp);
				finished[tmp] = true;
				if(tmp == node) break;
			}
			
			Collections.sort(scc, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o1, o2);
				}
			});
			
			sccList.add(scc);
		}
		
		return parent;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		graph = new ArrayList<>();
		sccList = new ArrayList<>();
		stack = new ArrayDeque<>();
		id = 0;
		
		String[] ve = br.readLine().split(" ");
		
		int v = Integer.parseInt(ve[0]);
		int e = Integer.parseInt(ve[1]);
		
		d = new int[v + 1];
		finished = new boolean[v + 1];
		
		for(int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < e; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			graph.get(a).add(b);
		}
		
		for(int i = 1; i <= v; i++) {
			if(d[i] == 0) dfs(i);
		}
		
		Collections.sort(sccList, new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return Integer.compare(o1.get(0), o2.get(0));
			}
		});
		
		System.out.println(sccList.size());
		
		for(List<Integer> nodes : sccList) {
			for(int nd : nodes) {
				sb.append(nd + " ");
			}
			sb.append("-1\n");
		}

		System.out.println(sb.toString());
	}

}
