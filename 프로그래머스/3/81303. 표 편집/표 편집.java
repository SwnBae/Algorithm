import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        int cursor = k;

        for (String c : cmd) {
            String[] input = c.split(" ");
            
            if (input[0].equals("U")) {
                int x = Integer.parseInt(input[1]);
                while (x-- > 0) cursor = prev[cursor];
                
            } else if (input[0].equals("D")) {
                int x = Integer.parseInt(input[1]);
                while (x-- > 0) cursor = next[cursor];
                
            } else if (input[0].equals("C")) {
                stack.push(cursor);
                deleted[cursor] = true;

                if (prev[cursor] != -1) next[prev[cursor]] = next[cursor];
                if (next[cursor] != -1) prev[next[cursor]] = prev[cursor];

                cursor = (next[cursor] != -1) ? next[cursor] : prev[cursor];
                
            } else { // Z
                int restore = stack.pop();
                deleted[restore] = false;

                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (boolean d : deleted) {
            sb.append(d ? 'X' : 'O');
        }

        return sb.toString();
    }
}