import java.util.*;

class Solution {
    static class Node {
        int state, sheep, wolf;
        Node(int state, int sheep, int wolf) {
            this.state = state;
            this.sheep = sheep;
            this.wolf = wolf;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int[] edge : edges) children[edge[0]].add(edge[1]);
        
        int answer = 0;
        boolean[] vis = new boolean[1 << n];
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(new Node(1, 1, 0)); // 0번은 양
        vis[1] = true;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            answer = Math.max(answer, cur.sheep);
            
            for (int i = 0; i < n; i++) {
                if ((cur.state & (1 << i)) == 0) continue;
                for (int child : children[i]) {
                    int newState = cur.state | (1 << child);
                    if (vis[newState]) continue;
                    int newSheep = cur.sheep + (info[child] == 0 ? 1 : 0);
                    int newWolf = cur.wolf + (info[child] == 1 ? 1 : 0);
                    if (newWolf >= newSheep) continue;
                    vis[newState] = true;
                    queue.add(new Node(newState, newSheep, newWolf));
                }
            }
        }
        return answer;
    }
}