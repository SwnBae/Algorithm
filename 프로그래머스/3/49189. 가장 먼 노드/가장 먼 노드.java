import java.util.*;

class Edge {
    int node;
    int value;
    
    public Edge (int node, int value) {
        this.node = node;
        this.value = value;
    }
}

class Solution {
    List<List<Edge>> graph;
    int answer;
    int n;
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList<>();
        this.n = n;
        answer = 0;
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++) {
            graph.get(edge[i][0]).add(new Edge(edge[i][1], 1));
            graph.get(edge[i][1]).add(new Edge(edge[i][0], 1));
        }
        
        dijk();
        
        return answer;
    }
    
    void dijk() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.value, o2.value));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[1] = 0;
        pq.add(new Edge (1,0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if(distance[cur.node] < cur.value) continue;
            
            for(Edge nxt : graph.get(cur.node)) {
                if(distance[nxt.node] <= cur.value + nxt.value) continue;
                
                distance[nxt.node] = cur.value + nxt.value;
                pq.add(new Edge(nxt.node, distance[nxt.node]));
            }
        }
        
        int max = -1;
        for(int i = 1; i <= n; i++) {
            if(distance[i] == Integer.MAX_VALUE) continue;
            max = Math.max(max, distance[i]);
        }
        
        for(int i = 1; i <= n; i++) {
            if(distance[i] == max) answer++;
        }
    }
}