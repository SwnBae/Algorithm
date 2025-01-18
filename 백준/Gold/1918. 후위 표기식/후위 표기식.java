import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static List<String> result = new ArrayList<>();
    public static Stack<String> op = new Stack<>();
    
    public static int checkOP(String token) {
    	if(token.equals("*") || token.equals("/"))return 2;
    	if(token.equals("+") || token.equals("-"))return 1;
    	return 0; // 괄호
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split("");

        for (int i = 0; i < input.length; i++) {
            String token = input[i];
            if(token.matches("[A-Z]+")) {
                result.add(token);
            }else if(token.equals("(")) {
                op.push(token);
            } else if(token.equals(")")) {
                while (!op.isEmpty() && !op.peek().equals("(")) {
                    result.add(op.pop());
                }
                op.pop();
            } else { // 연산자 우선순위 생각하기 */ > +-, 스택 내부에 있는 연산자가 현재 만난 연산자보다 우선 순위가 높은 경우
            	while (!op.isEmpty() && checkOP(op.peek())>= checkOP(token)) {
                    result.add(op.pop());
                }
                op.push(token);                  
            }
        }
        

        while (!op.empty()) {
            result.add(op.pop());
        }

        for (String s : result) {
            bw.write(s);
        }
        bw.flush();
        bw.close();
    }
}