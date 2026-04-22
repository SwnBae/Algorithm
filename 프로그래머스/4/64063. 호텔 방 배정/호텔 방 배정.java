import java.util.*;

class Solution {
    Map<Long, Long> parents;
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        parents = new HashMap<>();
        
        for(int i = 0; i < room_number.length; i++) {
            long assigned = find(room_number[i]);
            answer[i] = assigned;
            parents.put(assigned, assigned + 1);
        }
        
        return answer;
    }
    
    public long find(long num) {
        if(parents.containsKey(num)) {
            parents.put(num, find(parents.get(num)));
            return parents.get(num);
        }
        
        return num;
    }
}