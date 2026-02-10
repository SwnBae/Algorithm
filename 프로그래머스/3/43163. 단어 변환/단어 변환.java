import java.util.*;

class Word {
    int count;
    String word;
    
    Word (int count, String word) {
        this.count = count;
        this.word = word;
    }
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    int bfs(String begin, String target, String[] words) {
        Deque<Word> queue = new ArrayDeque<>();
        
        boolean[] visited = new boolean[words.length];
        
        queue.add(new Word(0, begin));
        
        while(!queue.isEmpty()) {
            Word cur = queue.poll();
            
            if(cur.word.equals(target)) return cur.count;
            
            for(int i = 0; i < words.length; i++) {
                if(visited[i]) continue;
                
                if(!canChange(cur.word, words[i])) continue;
                
                visited[i] = true;
                queue.add(new Word(cur.count + 1, words[i]));
            }
        }
        
        return 0;
    }
    
    boolean canChange (String str, String target) {
        int count = 0;
        
        for(int i = 0; i < str.length(); i++) {
            if(count > 1) return false;
            
            if(str.charAt(i) != target.charAt(i)) count++;
        }
        
        return count == 1;
    }
}