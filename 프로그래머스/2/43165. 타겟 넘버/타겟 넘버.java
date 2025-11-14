class Solution {
    int[] numbers;
    int target;
    boolean[] op;
    int answer;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        op = new boolean[numbers.length];
        answer = 0;
        
        dfs(0);
        
        return answer;
    }
    
    void dfs(int depth) {
        if(depth == numbers.length) {
            isTarget();
            return;
        }
        
        op[depth] = true;
        dfs(depth + 1);
        
        op[depth] = false;
        dfs(depth + 1);
    }
    
    void isTarget() {
        int result = 0;
        for(int i = 0; i < numbers.length; i++) {
            if(op[i]) {
                result += numbers[i];
                continue;
            }
            result -= numbers[i];
        }
        
        if(result == target) answer++;
    }
}