import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] diff = new int[board.length + 1][board[0].length + 1];
        int answer = 0;
        
        for(int i = 0; i < skill.length; i++) {
            if(skill[i][0] == 2) {
                diff[skill[i][1]][skill[i][2]] += skill[i][5];
                diff[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
                
                diff[skill[i][1]][skill[i][4] + 1] -= skill[i][5];
                diff[skill[i][3] + 1][skill[i][2]] -= skill[i][5];
            } else {
                diff[skill[i][1]][skill[i][2]] -= skill[i][5];
                diff[skill[i][3] + 1][skill[i][4] + 1] -= skill[i][5];
                
                diff[skill[i][1]][skill[i][4] + 1] += skill[i][5];
                diff[skill[i][3] + 1][skill[i][2]] += skill[i][5];
            }
        }
        
        for (int i = 0; i < diff.length - 1; i++) {
            for (int j = 0; j < diff[0].length - 1; j++) {
                if (i > 0) diff[i][j] += diff[i-1][j];
                if (j > 0) diff[i][j] += diff[i][j-1];
                if (i > 0 && j > 0) diff[i][j] -= diff[i-1][j-1];
                
                board[i][j] += diff[i][j];
                
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}