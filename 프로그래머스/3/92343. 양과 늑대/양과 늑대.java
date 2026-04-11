import java.util.*;

class Solution {
    int answer = 0;
    int[] info; // 0: 양, 1: 늑대
    int[][] edges;
    List<Integer>[] children;
    
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        this.info = info;
        this.edges = edges;
        children = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
        children[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        children[edge[0]].add(edge[1]); // 부모 -> 자식 방향
    }
        
        dfs(1, 0, new ArrayList<>(children[0]));
        return answer;
    }
    
    void dfs(int sheep, int wolf, List<Integer> candidates) {
        answer = Math.max(answer, sheep);
    
    for (int next : candidates) {
        List<Integer> newCandidates = new ArrayList<>(candidates);
        newCandidates.remove((Integer) next);
        
        // next의 자식들을 후보에 추가
        newCandidates.addAll(children[next]);
        
        if (info[next] == 0) { // 양
            dfs(sheep + 1, wolf, newCandidates);
        } else { // 늑대
            if (wolf + 1 < sheep) { // 늑대 < 양 조건
                dfs(sheep, wolf + 1, newCandidates);
            }
        }
    }
}
}