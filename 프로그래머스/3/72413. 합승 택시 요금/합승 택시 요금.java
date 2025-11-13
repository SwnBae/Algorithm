import java.util.*;

class Edge {
    int node;
    int fee;
    
    public Edge (int node, int fee) {
        this.node = node;
        this.fee = fee;
    }
}

class Solution {
    List<List<Edge>> graph;
    Set<Integer> set;
    int n;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList<>();
        set = new HashSet<>();
        this.n = n;
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new Edge (fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Edge (fares[i][0], fares[i][2]));
            
            set.add(fares[i][0]);
            set.add(fares[i][1]);
        }
        
        int soloFee = dijk(s, a) + dijk(s, b);
        int fairFee = Integer.MAX_VALUE;
        
        for(int i : set) {
            if (i == s) continue;
            
            fairFee = Math.min(fairFee, dijk(i,s) + dijk(i,a) + dijk(i,b));
        }
        
        return Math.min(soloFee, fairFee);
    }
    
    public int dijk (int st, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.fee, o2.fee));
        int[] distance = new int[n + 1];
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[st] = 0;
        pq.add(new Edge (st, 0));
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if(cur.node == end) {
                return cur.fee;
            }
            
            if(distance[cur.node] < cur.fee) continue;
            
            for(Edge nxt : graph.get(cur.node)) {
                if(distance[nxt.node] <= cur.fee + nxt.fee) continue;
                
                distance[nxt.node] = cur.fee + nxt.fee;
                pq.add (new Edge (nxt.node, distance[nxt.node]));
            }
        }
        
        return distance[end];
    }
}