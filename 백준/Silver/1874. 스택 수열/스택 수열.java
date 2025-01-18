import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Stack<Integer> stack = new Stack<>();
        int[] answers = new int[n];
        int ansIdx = 0;
        List<String> pm = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(sc.nextLine());
            answers[i] = temp;
        }

        for(int i = 1; i <= n; i++) {
            stack.push(i);
            pm.add("+");
            for(int j = 1; j <= i; j++) {
                if(!stack.empty() && stack.peek() == answers[ansIdx]) {
                    stack.pop();
//                   System.out.println(stack.pop());
                    pm.add("-");
                    ansIdx++;
                } else {
                    break;
                }
            }
        }

        if(ansIdx == n) {
            for(String s : pm) {
                System.out.println(s);
            }
        } else {
            System.out.println("NO");
        }
    }
}