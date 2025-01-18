import java.io.*;
import java.util.*;

public class Main {
    public static Stack<Double> numStack = new Stack<>();
    public static Queue<String> opQueue = new LinkedList<>();
    public static Map<String, Integer> map = new HashMap<>();

    public static void calBack(){
        double n2 = numStack.pop();
        double n1 = numStack.pop();
        String op = opQueue.poll();
        numStack.push(calOP(n1,n2,op));
    }

    public static double calOP(double n1, double n2,String op){
        switch(op){
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("");

        for (int i = 0; i < input.length; i++) {
            if(input[i].matches("[A-Z]+")) {
                if(!map.containsKey(input[i])) {
                    map.put(input[i], 0);
                }
            }
        }

        for(String key : map.keySet()) {
            int temp = Integer.parseInt(br.readLine());
            map.put(key, temp);
        }

        for (int i = 0; i < input.length; i++) {
            if(input[i].matches("[A-Z]+")) {
                numStack.add((double) map.get(input[i]));
            }else {
                opQueue.add(input[i]);
                calBack();
            }
        }

        bw.append(String.format("%.2f", numStack.pop()));
        bw.flush();
        bw.close();

    }
}