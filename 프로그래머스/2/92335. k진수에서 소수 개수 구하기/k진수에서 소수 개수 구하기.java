class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String num = Integer.toString(n, k);
        String[] nums = num.split("0");
        
        // max가 배열로 만들기 너무 크면 에라토스테네스 못 씀
        // 근데 문제 제한상 그냥 소수 판별 함수로 처리
        for (String number : nums) {
            if (number.equals("")) continue;
            long val = Long.parseLong(number);
            if (isPrime(val)) answer++;
        }
        
        return answer;
    }
    
    private boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}