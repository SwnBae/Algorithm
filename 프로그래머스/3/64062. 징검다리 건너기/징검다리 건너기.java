import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int r = 200000000;
        int l = 0;
        
        while(l < r) {
            int mid = (l + r) / 2;
            
            if(canCross(stones, k, mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    public boolean canCross(int[] stones, int k, int mid) {
        int consecutive = 0;
        
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] <= mid) {
                consecutive++;
                if(consecutive >= k) return false;
            } else {
                consecutive = 0;
            }
        }
        
        return true;
    }
}