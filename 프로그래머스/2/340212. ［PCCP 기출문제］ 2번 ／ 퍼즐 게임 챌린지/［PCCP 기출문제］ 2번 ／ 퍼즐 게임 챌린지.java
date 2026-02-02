import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        int answer = right;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(getTotalTime(mid, diffs, times, limit) != -1) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public long getTotalTime(int level, int[] diffs, int[] times, long limit) {
        long result = getTime(level, diffs[0], times[0], 0);
        
        for(int i = 1; i < diffs.length; i++) {
            result += getTime(level, diffs[i], times[i], times[i-1]);
        
            if(result > limit) return -1;
        }
        
        return result;
    }
    
    public long getTime (int level, int diff, int timeCur, int timePrev) {
        if(diff <= level) {
            return timeCur;
        }
        
        return ((timePrev + timeCur) * (diff - level)) + timeCur;
    }
}