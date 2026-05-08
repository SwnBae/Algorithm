import java.util.*;

class Solution {
    Map<String, Integer> cache;
    int globalTime;
    int answer;
    
    public int solution(int cacheSize, String[] cities) {
        cache = new HashMap<>();
        answer = 0;
        globalTime = 0;
        
        for(String tmpCity : cities) {
            String city = tmpCity.toLowerCase();
            
            if(cache.containsKey(city)) {
                answer++;
            } else {
                answer += 5;
            }
            
            cache.put(city, globalTime++);
            
            cleanCache(cacheSize);
        }
        
        return answer;
    }
    
    public void cleanCache(int cacheSize) {
        if(cache.size() <= cacheSize) return;
        
        int min = 100001;
        String minKey = "";
        
        for(String key : cache.keySet()) {
            if(cache.get(key) < min) {
                min = cache.get(key);
                minKey = key;
            }
        }
        
        cache.remove(minKey);
    }
}