import java.util.*;

class Node {
    int y;
    int x;
    int time;
    
    Node(int y, int x, int time) {
        this.y = y;
        this.x = x;
        this.time = time;
    }
}

class Point {
    int y;
    int x;
    
    Point (int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    public PriorityQueue<Node> pq;
    public Point[] points;
    public int count;
    
    public int solution(int[][] points, int[][] routes) {
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
        this.points = new Point[101];
        
        for(int i = 0; i < points.length; i++) {
            this.points[i + 1] = new Point(points[i][0], points[i][1]);
        }
        
        for(int i = 0; i < routes.length; i++) {
            int time = 0;
            
            for(int j = 0; j < routes[0].length - 1; j++) {
                time = addRoute(routes[i][j],routes[i][j+1], time);
            }
        }
        
        return countAccident();
    }
    
    public int countAccident() {
        int count = 0;
        int time = 0;
        int[][] space = new int[101][101];
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(cur.time != time) {
                time = cur.time;
                space = new int[101][101];
            }
            
            if(space[cur.y][cur.x] == 1) {
                count++;
            }
            
            space[cur.y][cur.x]++;
        }
        
        return count;
    }
    
    public int addRoute(int start, int end, int time) {
        int curY = points[start].y;
        int curX = points[start].x;
        
        if(time == 0) pq.add(new Node(curY, curX, time));
        
        while(curY != points[end].y) {
            if(curY < points[end].y) {
                curY++;
            } else {
                curY--;
            }
            
            time++;
            pq.add(new Node(curY, curX, time));
        }
        
        while(curX != points[end].x) {
            if(curX < points[end].x) {
                curX++;
            } else {
                curX--;
            }
            
            time++;
            pq.add(new Node(curY, curX, time));
        }
        
        return time;
    }
    
}