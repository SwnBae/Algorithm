import java.util.*;

class Solution {
    private int n, m, r, c, k;
    private final int[] dy = {1, 0, 0, -1};  // d, l, r, u
    private final int[] dx = {0, -1, 1, 0};
    private final char[] dir = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > k || (k - dist) % 2 != 0) return "impossible";
        
        StringBuilder path = new StringBuilder();
        return dfs(x, y, path) ? path.toString() : "impossible";
    }
    
    private boolean dfs(int y, int x, StringBuilder path) {
        // 기저 조건
        if (path.length() == k) return y == r && x == c;
        
        // d, l, r, u 순서로 탐색
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            // 경계 체크
            if (isOutBound(ny, nx)) continue;
            
            // 도달 가능성 체크
            int nextRemain = k - path.length() - 1;
            int nextDist = Math.abs(r - ny) + Math.abs(c - nx);
            if (nextDist > nextRemain || (nextRemain - nextDist) % 2 != 0) continue;
            
            // 선택 → 재귀 → 백트래킹
            path.append(dir[i]);
            if (dfs(ny, nx, path)) return true;
            path.deleteCharAt(path.length() - 1);
        }
        
        return false;
    }
    
    private boolean isOutBound(int y, int x) {
        return y < 1 || y > n || x < 1 || x > m;
    }
}