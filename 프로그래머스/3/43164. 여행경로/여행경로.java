import java.util.*;

class Solution {
    Map<Integer, String> indexMap;
    Map<String, Integer> revIdxMap;
    List<Set<Integer>> graph;
    int[] tmp;
    int[][] ticketCnt;
    
    String[] answer;
    boolean find;
    
    public String[] solution(String[][] tickets) {
        indexMap = new HashMap<>();
        revIdxMap = new HashMap<>();
        tmp = new int[tickets.length + 1];
        graph = new ArrayList<>();
        answer = new String[tickets.length + 1];
        answer[0] = "ICN";
        find = false;
        
        for(int i = 0; i <= 10000; i++) {
            graph.add(new TreeSet<>());
        }
        
        Set<String> sortSet = new TreeSet<>();
        for(int i = 0; i < tickets.length; i++) {
            sortSet.add(tickets[i][0]);
            sortSet.add(tickets[i][1]);
        }
        
        int tmpIdx = 1;
        for(String str : sortSet) {
            indexMap.put(tmpIdx, str);
            revIdxMap.put(str, tmpIdx++);
        }
        
        ticketCnt = new int[tmpIdx][tmpIdx];
        ticketCnt[0][0] = tickets.length;
        for(int i = 0; i < tickets.length; i++) {
            int s = revIdxMap.get(tickets[i][0]);
            int e = revIdxMap.get(tickets[i][1]);
            graph.get(s).add(e);
            ticketCnt[s][e]++;
        }
        
        dfs(0,revIdxMap.get("ICN"));
        return answer;
    }
    
    void dfs(int used, int curNode) {
        if(find) return;
        if(used == ticketCnt[0][0]) {
            for(int i = 0; i < ticketCnt[0][0]; i++) {
                answer[i + 1] = indexMap.get(tmp[i]);
            }
            
            find = true;
            return;
        }
        
        for(int nxt : graph.get(curNode)) {
            if(ticketCnt[curNode][nxt] <= 0) continue;
            
            ticketCnt[curNode][nxt]--;
            tmp[used] = nxt;
            dfs(used + 1, nxt);
            ticketCnt[curNode][nxt]++;
        }
    }
}