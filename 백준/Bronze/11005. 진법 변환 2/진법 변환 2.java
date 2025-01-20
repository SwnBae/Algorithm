import java.io.*;
import java.util.*;

public class Main {
	public static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] arr = new char[36];
		
		for(int i  = 10; i <= 35 ; i++) {
			arr[i] = (char) ('A' + (i-10));
		}
		
		String[] input = br.readLine().split(" ");
		
		int num = Integer.parseInt(input[0]);
		int notation = Integer.parseInt(input[1]);
		
		if(num == 0) {
			stack.add(num);
		}
		
		while(num >= notation) {
			stack.add(num%notation);
			num /= notation;
		}
		
		if(num != 0) {
			stack.add(num);
		}
		
		while(!stack.isEmpty()) {
			if(stack.peek()>=10) {
				sb.append(arr[stack.pop()]);
			} else {
				sb.append(stack.pop());
			}
		}

		System.out.println(sb);
	}

}
