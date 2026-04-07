import java.util.*;

class Node {
    int y;
    int x;
    int cost;
    int preDir;
    
    Node (int y, int x, int cost, int preDir) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.preDir = preDir;
    }
}

class Solution {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static final int STRAIGHT = 100;
    public static final int CORNER = 500;
    
    public int[][] board;
    public int N;
    
    public int solution(int[][] board) {
        this.board = board;
        N = board.length;
        return dijk();
    }
    
    public int dijk() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int[][][] dist = new int[N][N][4];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        
        dist[0][0][1] = 0;
        dist[0][0][3] = 0;
        pq.add(new Node(0, 0, 0, 1));
        pq.add(new Node(0, 0, 0, 3));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(dist[cur.y][cur.x][cur.preDir] < cur.cost) continue;
            
            if(cur.y == N - 1 && cur.x == N - 1) {
                return cur.cost;
            }
            
            for(int d = 0; d < 4; d++) {
                int nY = cur.y + dy[d];
                int nX = cur.x + dx[d];
                
                if(isOutBound(nY, nX) || board[nY][nX] == 1|| dist[nY][nX][d] < cur.cost + getCost(cur.preDir, d)) continue;
                
                dist[nY][nX][d] = cur.cost + getCost(cur.preDir, d);
                
                pq.add(new Node(nY, nX, cur.cost + getCost(cur.preDir, d), d));
            }
        }
        
        return -1;
    }
    
    public int getCost(int curDir, int nxtDir) {
        if(Math.min(curDir, nxtDir) < 2 && Math.max(curDir, nxtDir) >= 2) {
            return STRAIGHT + CORNER;
        }
        return STRAIGHT;
    }
    
    public boolean isOutBound(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}