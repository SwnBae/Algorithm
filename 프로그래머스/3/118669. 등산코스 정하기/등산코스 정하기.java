import java.util.*;

class Edge {
    int node, val;
    
    Edge(int node, int val) {
        this.node = node;
        this.val = val;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 1. 그래프 구성
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();
        
        for (int gate : gates) gateSet.add(gate);
        for (int summit : summits) summitSet.add(summit);
        
        for (int[] path : paths) {
            graph.get(path[0]).add(new Edge(path[1], path[2]));
            graph.get(path[1]).add(new Edge(path[0], path[2]));
        }
        
        // 2. 모든 gate에서 동시 출발 (summit까지)
        int[] fromGate = dijkstraFromGates(n, graph, gates, gateSet, summitSet);
        
        // 3. 모든 summit에서 동시 출발 (gate까지)
        int[] fromSummit = dijkstraFromSummits(n, graph, summits, gateSet, summitSet);
        
        // 4. 결과 계산
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        Arrays.sort(summits); // 번호 순으로 정렬
        
        for (int summit : summits) {
            // gate -> summit -> gate 왕복 intensity
            int intensity = Math.max(fromGate[summit], fromSummit[summit]);
            
            if (intensity < answer[1] || 
                (intensity == answer[1] && summit < answer[0])) {
                answer[0] = summit;
                answer[1] = intensity;
            }
        }
        
        return answer;
    }
    
    // 모든 gate에서 출발하여 각 노드까지의 최소 intensity
    int[] dijkstraFromGates(int n, List<List<Edge>> graph, int[] gates,
                            Set<Integer> gateSet, Set<Integer> summitSet) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        // 모든 gate를 시작점으로
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.add(new Edge(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (intensity[cur.node] < cur.val) continue;
            
            // summit에 도착하면 더 이상 진행하지 않음
            if (summitSet.contains(cur.node)) continue;
            
            for (Edge next : graph.get(cur.node)) {
                // 다른 gate 방문 금지
                if (gateSet.contains(next.node)) continue;
                
                int maxIntensity = Math.max(cur.val, next.val);
                
                if (intensity[next.node] > maxIntensity) {
                    intensity[next.node] = maxIntensity;
                    pq.add(new Edge(next.node, maxIntensity));
                }
            }
        }
        
        return intensity;
    }
    
    // 모든 summit에서 출발하여 각 노드까지의 최소 intensity
    int[] dijkstraFromSummits(int n, List<List<Edge>> graph, int[] summits,
                              Set<Integer> gateSet, Set<Integer> summitSet) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        // 모든 summit을 시작점으로
        for (int summit : summits) {
            intensity[summit] = 0;
            pq.add(new Edge(summit, 0));
        }
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (intensity[cur.node] < cur.val) continue;
            
            // gate에 도착하면 더 이상 진행하지 않음
            if (gateSet.contains(cur.node)) continue;
            
            for (Edge next : graph.get(cur.node)) {
                // 다른 summit 방문 금지
                if (summitSet.contains(next.node)) continue;
                
                int maxIntensity = Math.max(cur.val, next.val);
                
                if (intensity[next.node] > maxIntensity) {
                    intensity[next.node] = maxIntensity;
                    pq.add(new Edge(next.node, maxIntensity));
                }
            }
        }
        
        return intensity;
    }

}