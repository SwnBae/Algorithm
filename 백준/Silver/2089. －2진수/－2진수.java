import java.io.*;
import java.util.*;

public class Main {
	
	public static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		if(n == 0) {
			stack.add(n);
		}
		
		while(n != 1 && n != 0) {
			int rm = n % (-2);
			int qt = n / (-2);
			
			if(rm < 0) {
				qt++;
				rm = 1;
			}
			
			n = qt;
			stack.add(rm);
		}
		
		if(n == 1) {
			stack.add(n);
		}
		
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb.toString());
	}
}
