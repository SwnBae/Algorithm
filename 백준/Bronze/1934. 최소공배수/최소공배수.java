import java.io.*;
import java.util.*;

public class Main {
	
	public static int gcd(int a, int b) {
		while(b != 0) {
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
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			bw.append((a*b)/gcd(a,b) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
