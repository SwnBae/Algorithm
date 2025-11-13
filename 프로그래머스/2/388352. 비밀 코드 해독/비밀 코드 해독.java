class Solution {
    int[] comb = new int[5];
    boolean[][] inQ;

    public int solution(int n, int[][] q, int[] ans) {
        int m = q.length;
        inQ = new boolean[m][n+1];
        for (int i = 0; i < m; i++) {
            for (int v : q[i]) inQ[i][v] = true;
        }
        return dfs(1, 0, n, m, ans);
    }

    int dfs(int start, int depth, int n, int m, int[] ans) {
        if (depth == 5) {
            for (int i = 0; i < m; i++) {
                int cnt = 0;
                for (int k = 0; k < 5; k++) if (inQ[i][comb[k]]) cnt++;
                if (cnt != ans[i]) return 0;
            }
            return 1;
        }
        
        int total = 0;
        for (int v = start; v <= n - (4 - depth); v++) {
            comb[depth] = v;
            total += dfs(v + 1, depth + 1, n, m, ans);
        }
        return total;
    }
}
