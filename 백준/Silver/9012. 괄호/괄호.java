import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String[] input = br.readLine().split("");
			Stack<String> stack = new Stack<>();
			boolean end = false;
			
			for(int p = 0; p < input.length; p++) {
				if(input[p].equals("(")) {
					stack.add(input[p]);
				} else if(input[p].equals(")")) {
					if(stack.isEmpty()) {
						System.out.println("NO");
						end = true;
						break;
					} else {
						stack.pop();
					}
				}
			}
			
			if(!end) {
				if(stack.isEmpty()) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
			
			
		}

	}

}
