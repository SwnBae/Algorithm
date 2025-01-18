import java.io.*;
import java.util.*;

public class Main {
	
	public static String change(int[] arr, int n) {
		Stack<Long> stack = new Stack<>();
		long temp = 0;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(int i = 0;i<=arr.length-1;i++) {
			temp += arr[(arr.length-1)-i]* pow(n,count);
			count++;
			
			if(count == 3 || i==arr.length-1) {
				//System.out.println(temp);
				stack.add(temp);
				count = 0;
				temp = 0;
			}
		}
				
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	public static long pow(int a, int b) {
		long result = 1;
		for(int i = 0; i<b; i++) {
			result *= a;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split("");
		int[] arr = new int[input.length];
		
		for(int i = 0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		System.out.println(change(arr,2));
	}

}
