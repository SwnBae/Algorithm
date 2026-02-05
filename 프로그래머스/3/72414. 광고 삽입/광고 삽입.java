class Solution {
    int playTime;
    int advTime;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        playTime = toSeconds(play_time);
        advTime = toSeconds(adv_time);
        
        long[] diff = getDiff(logs);
        long[] viewers = getViewers(diff);
        long[] cumulative = getCumulative(viewers);
        
        long maxViews = cumulative[advTime];
        int answer = 0;
        
        for (int i = 1; i + advTime <= playTime; i++) {
            long current = cumulative[i + advTime] - cumulative[i];
            if (current > maxViews) {
                maxViews = current;
                answer = i;
            }
        }
        
        return toTimeString(answer);
    }
    
    private long[] getDiff(String[] logs) {
        long[] diff = new long[playTime + 1];
        
        for(String log : logs) {
            String[] parts = log.split("-");
            int start = toSeconds(parts[0]);
            int end = toSeconds(parts[1]);
            diff[start]++;
            diff[end]--;
        }
        
        return diff;
    }
    
    private long[] getViewers(long[] diff) {
        long[] viewers = new long[playTime + 1];
        viewers[0] = diff[0];
        
        for(int i = 1; i <= playTime; i++) {
            viewers[i] = viewers[i - 1] + diff[i];
        }
        
        return viewers;
    }
    
    private long[] getCumulative(long[] viewers) {
        long[] cumulative = new long[playTime + 1];
        
        for(int i = 1; i <= playTime; i++) {
            cumulative[i] = cumulative[i - 1] + viewers[i - 1];
        }
        
        return cumulative;
    }
    
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        
        return hours * 3600 + minutes * 60 + seconds;
    }

    private String toTimeString(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}