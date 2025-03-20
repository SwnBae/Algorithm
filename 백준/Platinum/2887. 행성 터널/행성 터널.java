import java.io.*;
import java.util.*;

class Node {
	int id;
	int x;
	int y;
	int z;
	
	public Node(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

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
	public static PriorityQueue<Edge> pq;
	public static int n;

	public static int[] parent;
	public static int[] rank;
	
	public static List<Node> xSet;
	public static List<Node> ySet;
	public static List<Node> zSet;

	public static int find(int node) {
		if(node != parent[node]) {
			parent[node] = find(parent[node]);
		}
		
		return parent[node];
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(rank[a] > rank[b]) {
			parent[b] = a;
			rank[a] += rank[b];
		} else {
			parent[a] = b;
			rank[b] += rank[a];
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		parent = new int[n + 1];
		rank = new int[n + 1];

		pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost, o2.cost);
			}
		});
		
		xSet = new ArrayList<>();
		
		ySet = new ArrayList<>();
		
		zSet = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			rank[i] = 1;
			parent[i] = i;
		}

		// i를 id로 하자
		for (int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			xSet.add(new Node(i, a, b, c));
			ySet.add(new Node(i, a, b, c));
			zSet.add(new Node(i, a, b, c));
		}
		
		Collections.sort(xSet, Comparator.comparingInt(node -> node.x));
		Collections.sort(ySet, Comparator.comparingInt(node -> node.y));
		Collections.sort(zSet, Comparator.comparingInt(node -> node.z));
		
		for(int i = 0; i < n - 1; i++) {
			pq.add(new Edge(xSet.get(i).id, xSet.get(i + 1).id, Math.min(Math.abs(xSet.get(i).x - xSet.get(i + 1).x),
					Math.min(Math.abs(xSet.get(i).y - xSet.get(i + 1).y), Math.abs(xSet.get(i).z - xSet.get(i + 1).z)))));
			
			pq.add(new Edge(ySet.get(i).id, ySet.get(i + 1).id, Math.min(Math.abs(ySet.get(i).x - ySet.get(i + 1).x),
					Math.min(Math.abs(ySet.get(i).y - ySet.get(i + 1).y), Math.abs(ySet.get(i).z - ySet.get(i + 1).z)))));
			
			pq.add(new Edge(zSet.get(i).id, zSet.get(i + 1).id, Math.min(Math.abs(zSet.get(i).x - zSet.get(i + 1).x),
					Math.min(Math.abs(zSet.get(i).y - zSet.get(i + 1).y), Math.abs(zSet.get(i).z - zSet.get(i + 1).z)))));
		}
		
		int cnt = 0;
		int result = 0;
		
		//간선을  n-1개만큼 쓴다.
		while(cnt != n - 1) {
			Edge edge = pq.poll();
			
			if(union(edge.start, edge.end)) {
				result += edge.cost;
				cnt++;
			}
		}
		
		System.out.println(result);

	}

}
