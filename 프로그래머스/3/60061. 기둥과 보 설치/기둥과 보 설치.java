import java.util.*;

class Solution {
    boolean[][][] board;
    PriorityQueue<int[]> pq;
    
    public int[][] solution(int n, int[][] build_frame) {
        board = new boolean[n + 1][n + 1][2];
        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] == o2[0] && o1[1] == o2[1]) return Integer.compare(o1[2], o2[2]);
            if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            
            return Integer.compare(o1[0], o2[0]);
        });
        
        for(int i = 0; i < build_frame.length; i++) {
            if(build_frame[i][3] == 1) {
                build(build_frame[i][1], build_frame[i][0], build_frame[i][2]);
                
            } else {
                delete(build_frame[i][1], build_frame[i][0], build_frame[i][2]);
            }
        }
        
        return getAnswer();
    }
    
    public int[][] getAnswer() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                for(int k = 0; k < 2; k++) {
                    if(board[i][j][k]) pq.add(new int[]{j, i, k});
                }
            }
        }
        
        int[][] answer = new int[pq.size()][3];
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = pq.poll();
        }
        
        return answer;
    }
    
    public void build(int y, int x, int structure) {
        if(structure == 0 && canBuildPole(y, x)) {
            board[y][x][0] = true;
        } else if(structure == 1 && canBuildPaper(y, x)) {
            board[y][x][1] = true;
        }
    }
    
    public void delete(int y, int x, int structure) {
        if(structure == 0) {
            board[y][x][0] = false;
            
            if((canEndurePaper(y + 1, x - 1) && canEndurePaper(y + 1, x)) 
                && canEndurePole(y + 1, x)) return;
            
            board[y][x][0] = true;
        } else if(structure == 1) {
            board[y][x][1] = false;
            
            if((canEndurePaper(y, x - 1) && canEndurePaper(y, x + 1)) 
                && (canEndurePole(y, x) && canEndurePole(y, x + 1))) return;
            
            board[y][x][1] = true;
        }
    }
    
    public boolean canEndurePole(int y, int x) {
        if(isOutBound(y, x) || !board[y][x][0]) return true;
        if(canBuildPole(y, x)) return true;
        return false;
    }
    
    public boolean canEndurePaper(int y, int x) {
        if(isOutBound(y, x) || !board[y][x][1]) return true;
        if(canBuildPaper(y, x)) return true;
        return false;
    }
    
    public boolean canBuildPole(int y, int x) {
        if(y == 0 || board[y - 1][x][0]) return true;
        if(!isOutBound(y, x - 1) && board[y][x - 1][1]) return true;
        if(board[y][x][1]) return true;
        
        return false;
    }
    
    public boolean canBuildPaper(int y, int x) {
        if(!isOutBound(y - 1, x) && board[y - 1][x][0]) return true;
        if(!isOutBound(y - 1, x + 1) && board[y - 1][x + 1][0]) return true;
        if(!isOutBound(y, x - 1) && !isOutBound(y, x + 1) 
            && board[y][x - 1][1] && board[y][x + 1][1]) return true;
        
        return false;
    }
    
    public boolean isOutBound(int y, int x) {
        return y < 0 || y >= board.length || x < 0 || x >= board.length;
    }
    
    public void pr() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                sb.append(Arrays.toString(board[i][j]));
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}