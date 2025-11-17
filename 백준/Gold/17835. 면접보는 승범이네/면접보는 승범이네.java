import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int node;
        long val;
        public Edge(int node, long val) {
            this.node = node;
            this.val = val;
        }
    }
    public static int n;
    public static Set<Integer> interview;
    public static List<List<Edge>> graph;
    public static int mNode;
    public static long mVal;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        mNode = 0;
        mVal = Long.MIN_VALUE;
        
        interview = new HashSet<>();
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < m; i++) {
            String[] uvc = br.readLine().split(" ");
            int u = Integer.parseInt(uvc[0]);
            int v = Integer.parseInt(uvc[1]);
            int c = Integer.parseInt(uvc[2]);
            
            graph.get(v).add(new Edge(u, c));
        }
        
        String[] inRoom = br.readLine().split(" ");
        
        for(int i = 0; i < k; i++) {
            interview.add(Integer.parseInt(inRoom[i]));
        }
        
        dijk();
        
        System.out.println(mNode);
        System.out.println(mVal);
    }
    
    public static void dijk(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.val, b.val));
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        
        for(int node : interview) {
            distance[node] = 0;
            pq.add(new Edge(node, 0));
        }
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if(distance[cur.node] < cur.val) continue;
            
            for(Edge nxt : graph.get(cur.node)) {
                if(distance[nxt.node] <= cur.val + nxt.val || interview.contains(nxt.node)) continue;
                
                distance[nxt.node] = cur.val + nxt.val;
                pq.add(new Edge(nxt.node, distance[nxt.node]));
            }
        }
        
        for(int i = 1; i <= n; i++) {
            if(!interview.contains(i) && mVal < distance[i]) {
                mNode = i;
                mVal = distance[i];
            }
        }
    }
}