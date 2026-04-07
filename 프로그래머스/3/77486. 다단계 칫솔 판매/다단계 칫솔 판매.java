import java.util.*;

class Solution {
    int[] parents;
    Map<String, Integer> nameIdx;
    int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        parents = new int[enroll.length + 1];
        nameIdx = new HashMap<>();
        
        int count = 1;
        
        for(int i = 1; i <= enroll.length; i++) {
            nameIdx.put(enroll[i - 1], i);
        }
        
        for(int i = 1; i <= enroll.length; i++) {
            if(referral[i - 1].equals("-")) continue;
            
            parents[i] = nameIdx.get(referral[i - 1]);
        }
        
        for(int i = 0; i < seller.length; i++) {
            calculate(nameIdx.get(seller[i]), amount[i] * 100);
        }
        
        return answer;
    }
    
    public void calculate(int idx, int amount) {
        int curAmount = amount;
        int curIdx = idx;
        
        while(curIdx != 0) {
            int give = curAmount / 10;
            answer[curIdx - 1] += curAmount - give;
            curAmount = give;
            
            curIdx = parents[curIdx];
        }
    }
}