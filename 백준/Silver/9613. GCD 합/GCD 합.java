import java.io.*;
import java.util.*;

public class Main {
	
	public static long getArrGCD(int[] arr) {
		if(arr.length == 1) return arr[0];
		
		long result = 0;
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				result += (getGCD(arr[i],arr[j]));
				//System.out.println(getGCD(arr[i],arr[j]));
			}
		}
		return result;
	}
	
	public static int getGCD(int a, int b) {
		while(b!=0) {
			int temp = b;
			b = a%b;
			a = temp;
		}
		
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int count = Integer.parseInt(br.readLine());
		
		for(int i=0; i<count; i++) {
			String[] input = br.readLine().split(" ");
			
			int n = Integer.parseInt(input[0]);
			int[] arr = new int[n];
			
			for(int j=1; j<input.length; j++) {
				arr[j-1] = Integer.parseInt(input[j]);
			}
			
			bw.append(getArrGCD(arr) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
