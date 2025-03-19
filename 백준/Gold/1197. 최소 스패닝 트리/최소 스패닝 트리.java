import java.io.*;
import java.util.*;

class Edge {
	int start;
	int end;
	int cost;

	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

public class Main {
	public static int[] parent;
	public static int[] rank;
	public static PriorityQueue<Edge> edges;
	public static int sum;
	
	public static int find(int node) {
		if(node != parent[node]) {
			parent[node] = find(parent[node]);
		}
		
		return parent[node];
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 부모가 같다.
		if(a == b) {
			return false;
		}
		
		if(rank[a] > rank[b]) {
			parent[b] = a;
			rank[a] += rank[b];
			return true;
		} else {
			parent[a] = b;
			rank[b] += a;
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] ve = br.readLine().split(" ");
		edges = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost, o2.cost);
			}
		});
		
		int v = Integer.parseInt(ve[0]);
		int e = Integer.parseInt(ve[1]);
		
		parent = new int[v+1];
		rank = new int[v+1];
		sum = 0;
		
		for(int i = 1; i <= v; i++) {
			rank[i] = 1;
			parent[i] = i;
		}
		
		for(int i = 0; i < e; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			edges.add(new Edge(a, b, c));
		}
		
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			if(union(edge.start, edge.end)) {
				sum += edge.cost;
			}
		}
		
		System.out.println(sum);
	}

}
