import java.util.*;

class Node {
    int y;
    int x;
    
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    
    public static char[][] space;
    public static int result;
    
    public static boolean outBound(int y, int x) {
        return y < 0 || y >= space.length || x < 0 || x >= space[0].length;
    }
    
    public static boolean isEqual (char org, char opp) {
        return org == opp;
    }
    
    public void pickAndDelete (char c) {
        for(int i = 1; i < space.length - 1; i++) {
            for(int j = 1; j < space[0].length - 1; j++) {
                if(c == space[i][j]) {
                    space[i][j] = 0;
                    result--;
                }
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        space = new char[storage.length + 2][storage[0].length() + 2];
        result = storage.length * storage[0].length();
        
        for(int i = 0; i < storage.length; i++) {
            for(int j = 0; j < storage[0].length(); j++) {
                space[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        for(int i = 0; i < requests.length; i++) {
            if(requests[i].length() > 1) {
                pickAndDelete(requests[i].charAt(0));
                continue;
            }
            
            bfs(requests[i]);
        }
        
        return result;
    }
    
    
    public static void bfs (String command) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        
        ArrayDeque<Node> deleteQueue = new ArrayDeque<>();
        
        boolean[][] visited = new boolean[space.length][space[0].length];
        
        visited[0][0] = true;
        queue.add(new Node(0,0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nxtY = cur.y + dy[i];
                int nxtX = cur.x + dx[i];
                
                if(outBound(nxtY,nxtX) || visited[nxtY][nxtX]) continue;
                
                visited[nxtY][nxtX] = true;
                
                if(isEqual(space[nxtY][nxtX], command.charAt(0))) {
                    deleteQueue.add(new Node (nxtY, nxtX));
                }
                
                if(!isEqual(space[nxtY][nxtX], command.charAt(0)) && space[nxtY][nxtX] == 0) {
                    queue.add(new Node(nxtY, nxtX));
                }
            }
        }
        
        while (!deleteQueue.isEmpty()) {
            Node cur = deleteQueue.poll();
            
            space[cur.y][cur.x] = 0;
            result--;
        }
    }
        
        
}