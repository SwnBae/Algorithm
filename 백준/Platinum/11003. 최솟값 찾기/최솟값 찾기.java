import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static int n;
    public static int l;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] init = br.readLine().split(" ");
        n = Integer.parseInt(init[0]);
        l = Integer.parseInt(init[1]);
        arr = new int[n + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        String[] input = br.readLine().split(" ");

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(input[i - 1]);
        }

        for(int i = 1; i <= n; i++) {
            if(i > 1) sb.append(" ");

            while(!deque.isEmpty() &&  i - l + 1 > deque.peekFirst()) {
                deque.pollFirst();
            }

            while(!deque.isEmpty() && arr[deque.peekLast()] > arr[i]){
                deque.pollLast();
            }

            deque.offer(i);

            sb.append(arr[deque.peekFirst()]);
        }

        System.out.println(sb);
    }
}
