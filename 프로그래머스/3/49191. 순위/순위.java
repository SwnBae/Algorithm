import java.util.*;

class Solution {
    int n;
    
    public int solution(int n, int[][] results) {
        this.n = n;
        
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> revGraph = new ArrayList<>();
        int answer = 0;
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < results.length; i++) {
            graph.get(results[i][0]).add(results[i][1]);
            revGraph.get(results[i][1]).add(results[i][0]);
        }
        
        for(int i = 1; i <= n; i++) {
            int total = bfs(i, graph) + bfs(i, revGraph);
            
            if(total == n - 1) answer++;
        }
        
        return answer;
    }
    
    int bfs(int start, List<List<Integer>> graph) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int cnt = 0;
        
        visited[start] = true;
        q.add(start);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int nxt : graph.get(cur)) {
                if(visited[nxt]) continue;
                
                visited[nxt] = true;
                cnt++;
                q.add(nxt);
            }
        }
        
        return cnt;
    }
}