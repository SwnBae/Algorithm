import java.util.*;

class Solution {
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(r - x) + Math.abs(c - y);
        
        if(dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }
        
        StringBuilder answer = new StringBuilder();
        int remain = k;
        int cy = x, cx = y;
        
        while (remain > 0) {
            int toDist = Math.abs(r - cy) + Math.abs(c - cx);
            
            //down
            if(cy < n) {
                int newDist = Math.abs(r - (cy + 1)) + Math.abs(c - cx);
                
                if(newDist <= remain - 1 && (remain - 1 - newDist) % 2 == 0) {
                    answer.append('d');
                    cy++;
                    remain--;
                    continue;
                }
            }
            
            //left
            if(cx > 1) {
                int newDist = Math.abs(r  - cy) + Math.abs(c - (cx - 1));
                
                if(newDist <= remain - 1 && (remain - 1 - newDist) % 2 == 0) {
                    answer.append('l');
                    cx--;
                    remain--;
                    continue;
                }
            }
            
            //right
            if(cx < m) {
                int newDist = Math.abs(r - cy) + Math.abs(c - (cx + 1));
                
                if(newDist <= remain - 1 && (remain - 1 - newDist) % 2 == 0) {
                    answer.append('r');
                    cx++;
                    remain--;
                    continue;
                }
            }
            
            //up
            if(cy > 1) {
                int newDist = Math.abs(r - (cy - 1)) + Math.abs(c - cx);
                
                if(newDist <= remain - 1 && (remain - 1 - newDist) % 2 == 0) {
                    answer.append('u');
                    cy--;
                    remain--;
                    continue;
                }
            }
            
        }
        
        return answer.toString();
    }
}