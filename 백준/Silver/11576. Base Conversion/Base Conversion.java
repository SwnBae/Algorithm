import java.io.*;
import java.util.*;

public class Main {

	public static int pow(int a, int b) {
		int result = a;
		
		if(b == 0) return 1;
		
		for(int i = 1; i < b; i++) {
			result *= a;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		String[] input = br.readLine().split(" ");
		
		int noA = Integer.parseInt(input[0]);
		int noB = Integer.parseInt(input[1]);
		int tempNo = 0;
		int count = Integer.parseInt(br.readLine());
		
		String[] num = br.readLine().split(" ");
		
		for(int i = 0; i<count; i++) {
			tempNo += Integer.parseInt(num[(count-1)-i]) * pow(noA,i);
		}
		
		while(tempNo >= noB) {
			stack.add(tempNo % noB);
			tempNo /= noB;
		}
		
		if(tempNo != 0) {
			stack.add(tempNo);
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
		System.out.println(sb.toString());
	}

}
