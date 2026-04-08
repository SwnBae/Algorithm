import java.util.*;
class Solution {
    int N;
    int M;
    
    int minY;
    int minX;
    int maxY;
    int maxX;
    
    int[][] lock;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        this.lock = lock;
        
        minY = 20;
        minX = 20;
        maxY = -1;
        maxX = -1;
        
        int count = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(lock[i][j] == 1) continue;
                minY = Math.min(minY, i);
                minX = Math.min(minX, j);
                maxY = Math.max(maxY, i);
                maxX = Math.max(maxX, j);
                
                count++;
            }
        }
        
        if(maxX - minX > M || maxY - minY > M) return false;
        
        int[][] cur = key;
        for(int k = 0; k < 4; k++) {
            for(int i = maxY - M + 1; i <= minY; i++) {
                for(int j = maxX - M + 1; j <= minX; j++) {
                    if(canUnlock(cur, count, i, j)) return true;
                }
            }
            cur = rotate(cur);
        }
        
        return false;
    }
    
    public int[][] rotate(int[][] arr) {
        int[][] newArr = new int[M][M];
        for(int i = 0; i < M; i++)
            for(int j = 0; j < M; j++)
                newArr[j][M-1-i] = arr[i][j];
        return newArr;
    }
    
    public boolean canUnlock(int[][] newKey, int count, int y, int x) {
        int unlockCnt = 0;
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                if(isOutBound(y + i, x + j)) continue;
                if(newKey[i][j] + lock[y + i][x + j] != 1) return false;
                if(newKey[i][j] == 1) unlockCnt++;
            }
        }
        
        return count == unlockCnt;
    }
    
    public boolean isOutBound(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}