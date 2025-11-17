import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        return bfs(numbers, target);
    }
    
    int bfs(int[] numbers, int target) {
        Deque<int[]> dq = new ArrayDeque<>();
        int result = 0;
        
        dq.add(new int[]{-1, 0}); //idx, sum
        
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int curIdx = cur[0];
            int curNum = cur[1];
            
            if(curIdx == numbers.length - 1) {
                if(curNum == target) result++;
                continue;
            }
            
            dq.add(new int[]{curIdx + 1, curNum + numbers[curIdx + 1]});
            dq.add(new int[]{curIdx + 1, curNum - numbers[curIdx + 1]});
        }
        
        return result;
    }
}