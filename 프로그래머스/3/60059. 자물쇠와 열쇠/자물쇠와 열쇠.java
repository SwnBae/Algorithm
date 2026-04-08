import java.util.*;

class Solution {
    int N;
    int M;
    
    int minY;
    int minX;
    int maxY;
    int maxX;
    
    int[][] lock;
    double mid;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        this.lock = lock;
        mid = (M - 1) / 2.0;
        
        
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
        
        for(int k = 0; k < 4; k++) {
            int[][] newKey = getRotationKey(key, k);
            
            // for(int i = 0; i < M; i++) {
            //     System.out.println(Arrays.toString(newKey[i]));
            // }
            
            for(int i = maxY - M + 1; i <= minY; i++) {
                for(int j = maxX - M + 1; j <= minX; j++) {
                    if(canUnlock(newKey, count, i, j)) return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean canUnlock(int[][] newKey, int count, int y, int x) {
        int unlockCnt = 0;
        
        // System.out.println(y + ", " + x);
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                if(isOutBound(y + i, x + j)) continue;
                if(newKey[i][j] + lock[y + i][x + j] != 1) return false;
                if(newKey[i][j] == 1) unlockCnt++;
            }
        }
        
        // System.out.println(unlockCnt);
        
        return count == unlockCnt;
    }
    
    public boolean isOutBound(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
    
    public int[][] getRotationKey(int[][] key, int dir) {
        if(dir == 0) return key;
        
        int[][] newKey = new int[M][M];
        
        if(dir == 1) {
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M; j++) {
                    if(key[i][j] == 0) continue;
                    
                    int newY = (int)(mid - (mid - j));
                    int newX =(int)(mid + (mid - i));
                    newKey[newY][newX] = key[i][j];
                }
            }
        } else if(dir == 2) {
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M; j++) {
                    if(key[i][j] == 0) continue;
                    
                    int newY = (int)(mid + (mid - i));
                    int newX = (int)(mid + (mid - j));
                    newKey[newY][newX] = key[i][j];
                }
            }
        } else {
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M; j++) {
                    if(key[i][j] == 0) continue;
                    
                    int newY = (int)(mid + (mid - j));
                    int newX = (int)(mid - (mid - i));
                    newKey[newY][newX] = key[i][j];
                }
            }
        }
        
        return newKey;
    }
}