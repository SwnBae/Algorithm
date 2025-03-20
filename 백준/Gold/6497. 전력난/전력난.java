import java.io.*;
import java.util.*;

//크루스칼
//유니온 파인드 구현하기
//pq 사용하기
class Edge{
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
	public static int[] parent;
	public static int[] rank;
	
	public static int n;
	public static int m;
	
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
			rank[b] += a;
		}
		
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			int result = 0;
			
			String[] ipt = br.readLine().split(" ");
			
			m = Integer.parseInt(ipt[0]);
			n = Integer.parseInt(ipt[1]);
			
			if(m == 0 && n == 0) {
				break;
			}
			
			parent = new int[m];
			rank = new int[m];
			
			pq = new PriorityQueue<>(new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return Integer.compare(o1.cost, o2.cost);
				}
			});
			
			for(int i = 0 ; i < m; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			
			for(int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				int c = Integer.parseInt(input[2]);
				
				pq.add(new Edge(a,b,c));
			}
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				
				if(!union(edge.start, edge.end)) {
					result += edge.cost;
				}
			}
			
			System.out.println(result);
		}
	}
}
