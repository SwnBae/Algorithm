class Solution {
    int[] parent;
    int count;
    
    public int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        
        return parent[x];
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a == b) return;
        
        parent[b] = a;
        count--;
    }
    
    public int solution(int n, int[][] computers) {
        count = n;
        parent = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if(i == j || computers[i][j] == 0) continue;
                
                union(i,j);
            }
        }
        return count;
    }
}