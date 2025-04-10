import java.io.*;

public class Main {
    public static char[] expression;
    public static int n;
    public static int max;

    public static void dfs(int idx, int sum){
        if(idx >= n){
            //System.out.println(sum);
            max = Math.max(max,sum);
            return;
        }

        int normal = calculate(sum, expression[idx + 1] - '0', expression[idx]);
        dfs(idx + 2, normal);

        if(idx + 2 < n){
            int p = calculate(expression[idx + 1] - '0', expression[idx + 3] - '0', expression[idx + 2]);
            dfs(idx + 4, calculate(sum,p,expression[idx]));
        }
    }

    public static int calculate(int a, int b, char op){
        switch (op){
            case '+':
                return a + b;
            case '-':
                return a - b;
            default:
                return a * b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        expression = new char[n];

        String input = br.readLine();

        for(int i = 0; i < n; i++){
            expression[i] = input.charAt(i);
        }

        int tmp = expression[0] - '0';

        for(int i = 1; i < n; i+=2){
            tmp = calculate(tmp, expression[i + 1] - '0', expression[i]);
        }

        max = tmp;
        dfs(1, expression[0] - '0');
        System.out.println(max);
    }
}