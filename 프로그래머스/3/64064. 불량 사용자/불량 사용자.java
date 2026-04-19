import java.util.*;

class Solution {
    List<Integer>[] banList;
    Set<Integer> checkedSet;
    boolean[] visited;
    
    public int solution(String[] user_id, String[] banned_id) {
        banList = new ArrayList[banned_id.length];
        checkedSet = new HashSet<>();
        visited = new boolean[user_id.length];
        
        for(int i = 0; i < banned_id.length; i++) {
            banList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < banned_id.length; i++) {
            setCandidate(user_id, banned_id[i], i);
        }
        
        dfs(0, 0);
        
        return checkedSet.size();
    }
    
    public void dfs(int banIdx, int result) {
        if(banIdx == banList.length) {
            checkedSet.add(result);
            return;
        }
        
        for(int idIdx : banList[banIdx]) {
            if(visited[idIdx]) continue;
            
            visited[idIdx] = true;
            dfs(banIdx + 1, result | (1 << idIdx));
            visited[idIdx] = false;
        }
    }
    
    public void setCandidate(String[] user_id, String bannedId, int idx) {
        for(int i = 0; i < user_id.length; i++) {
            if(user_id[i].length() != bannedId.length()) continue;
            
            if(checkBanned(user_id[i], bannedId)) {
                banList[idx].add(i);
            }
        }
    }
    
    public boolean checkBanned(String userId, String bannedId) {
        for(int i = 0; i < bannedId.length(); i++) {
            if(userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != '*') {
                return false;
            }
        }
        
        return true;
    }
}