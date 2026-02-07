import java.util.*;

class Solution {
    static class Node {
        int y, x;
        StringBuilder path;
        
        public Node(int y, int x, StringBuilder path) {
            this.y = y;
            this.x = x;
            this.path = path;
        }
    }
    
    private static int n, m;
    private static final int[] dy = {1, 0, 0, -1};
    private static final int[] dx = {0, -1, 1, 0};
    private static final char[] dir = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        
        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }
        
        return bfs(x, y, r, c, k);
    }
    
    private String bfs(int startY, int startX, int endY, int endX, int k) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, new StringBuilder()));
        
        while (!q.isEmpty()) {
            int qSize = q.size();
            
            for (int level = 0; level < qSize; level++) {
                Node cur = q.poll();
                
                // 목표 도달 체크
                if (cur.path.length() == k) {
                    if (cur.y == endY && cur.x == endX) {
                        return cur.path.toString();
                    }
                    continue;
                }
                
                // d, l, r, u 순서로 확장
                for (int i = 0; i < 4; i++) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];
                    
                    if (isOutBound(ny, nx)) continue;
                    
                    int remain = k - cur.path.length() - 1;
                    int nextDist = Math.abs(endY - ny) + Math.abs(endX - nx);
                    
                    if (nextDist > remain || (remain - nextDist) % 2 != 0) continue;
                    
                    // 통과한 경우만 큐에 추가
                    StringBuilder newPath = new StringBuilder(cur.path);
                    newPath.append(dir[i]);
                    q.add(new Node(ny, nx, newPath));
                    break;
                }
            }
        }
        
        return "impossible";
    }
    
    private boolean isOutBound(int y, int x) {
        return y < 1 || y > n || x < 1 || x > m;
    }
}