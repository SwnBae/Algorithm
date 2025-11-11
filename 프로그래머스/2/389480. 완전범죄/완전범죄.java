import java.util.*;

class History {
    int idx;
    int a;
    int b;
    
    public History (int idx, int a, int b){
        this.idx = idx;
        this.a = a;
        this.b = b;
    }
}

class Solution {
    public static int n;
    public static int m;
    
    public static boolean isPrison (int a, int b) {
        return a >= n || b >= m;
    }
    
    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        
        return dijk(info);
    }
    
    public int dijk(int[][] info) {
        PriorityQueue<History> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.a, o2.a));
        
        int[][] min = new int[info.length + 1][m];
        for(int i = 0; i <= info.length; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }
        
        pq.add(new History (0, 0, 0));
        min[0][0] = 0;
        
        while(!pq.isEmpty()) {
            History cur = pq.poll();
            
            if(cur.idx == info.length) {
                return cur.a;
            }
            
            if(!isPrison(cur.a + info[cur.idx][0], cur.b)) {
                if(min[cur.idx + 1][cur.b] > cur.a + info[cur.idx][0]){
                    pq.add(new History(cur.idx + 1, cur.a + info[cur.idx][0], cur.b));
                    min[cur.idx + 1][cur.b] = cur.a + info[cur.idx][0];
                }
            }
            
            if(!isPrison(cur.a, cur.b + info[cur.idx][1])) {
                if(min[cur.idx + 1][cur.b + info[cur.idx][1]] > cur.a){
                    pq.add(new History(cur.idx + 1, cur.a, cur.b + info[cur.idx][1]));
                    min[cur.idx + 1][cur.b + info[cur.idx][1]] = cur.a;   
                }
            }
        }
        
        return -1;
    }
}