class Solution {
    public int solution(int[] players, int m, int k) {
        int[] leanHistory = new int[24];
        int curServer = 0;
        int answer = 0;
        
        for(int i = 0; i < players.length; i++) {
            if(i >= k) {
                curServer -= leanHistory[i - k];
            }
            
            int needed = players[i] / m;
            
            if(needed > curServer) {
                int rent = needed - curServer;
                leanHistory[i] += rent;
                curServer += rent;
                answer += rent;
            }
        }
        
        return answer;
    }
}