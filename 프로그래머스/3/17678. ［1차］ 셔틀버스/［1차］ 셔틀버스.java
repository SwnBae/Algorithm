import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < timetable.length; i++) {
            String[] timeInput = timetable[i].split(":");
            int time = Integer.parseInt(timeInput[0]) * 60 + Integer.parseInt(timeInput[1]);
            if(time > 540 + n * t) continue;
            
            pq.add(time);
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(pq.isEmpty() || pq.peek() > 540 + (i * t)) break;
                
                if(i < n - 1) {
                    pq.poll();
                    continue;
                }
                
                stack.addLast(pq.poll());
            }
        }
        
        int answer = 540 + (n - 1) * t;
        
//         System.out.println(stack.size());
//         System.out.println(stack.peekLast());
        if(stack.size() == m) {
            
            answer =  stack.peekLast() - 1;
        }
        
        return String.format("%02d:%02d", answer / 60, answer % 60);
    }
}