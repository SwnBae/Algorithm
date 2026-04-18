class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int startPoint = 1;
        int wide = 2 * w + 1;
        
        for(int station : stations) {
            int stationLeft = station - w;
            int reqWide = stationLeft - startPoint;
            
            if(reqWide <= 0) {
                startPoint = station + w + 1;
                continue;
            }
            
            answer += reqWide / wide + (reqWide % wide > 0 ? 1 : 0);
            startPoint = station + w + 1;
        }
        
        if(startPoint <= n) {
            int reqWide = n - startPoint + 1;
            answer += reqWide / wide + (reqWide % wide > 0 ? 1 : 0);
        }

        return answer;
    }
}