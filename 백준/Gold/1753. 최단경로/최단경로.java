import java.util.*;
import java.io.*;

public class Main {
    static List<List<Edge>> graph;
    static int v;
    
    static class Edge {
    int node;
    int val;
    
        public Edge (int node, int val) {
            this.node = node;
            this.val = val;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] ve = br.readLine().split(" ");
        
        graph = new ArrayList<>();
        
        v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        int start = Integer.parseInt(br.readLine());
        
        for(int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < e; i++) {
            String[] uvw = br.readLine().split(" ");
            int u = Integer.parseInt(uvw[0]);
            int v = Integer.parseInt(uvw[1]);
            int w = Integer.parseInt(uvw[2]);
            
            graph.get(u).add(new Edge(v, w));
        }
        
        System.out.println(dijk(start));
    }
    
    public static String dijk(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        int[] distance = new int[v + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[start] = 0;
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if(cur.val > distance[cur.node]) continue;
            
            for(Edge nxt : graph.get(cur.node)) {
                if(distance[nxt.node] <= cur.val + nxt.val) continue;
                
                distance[nxt.node] = cur.val + nxt.val;
                pq.add(new Edge(nxt.node, distance[nxt.node]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < distance.length; i++) {
            if(i > 1) sb.append("\n");
            sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
        }
        
        return sb.toString();
    }
}