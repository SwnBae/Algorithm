import java.util.*;

class Node {
    int idx;
    int value;
    
    Node (int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<Node> dq = new ArrayDeque<>();
        
        for(int i = 0; i < stones.length; i++) {
            Node node = new Node(i, stones[i]);
            
            while(!dq.isEmpty() && i - dq.peekFirst().idx >= k) {
                dq.pollFirst();
            }
            
            while(!dq.isEmpty() && node.value > dq.peekLast().value) {
                dq.pollLast();
            }
            
            dq.addLast(node);
            
            if(i >= k - 1) {
               answer = Math.min(answer, dq.peekFirst().value); 
            }
        }
        
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}