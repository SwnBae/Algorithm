import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		
		int r1 = 1;
		int r2 = 1;
		
		for(int i = 1; i<=Math.min(a, b); i++) {
			if(a%i == 0 && b%i == 0) {
				r1 = Math.max(r1, i);
			}
		}
		
		while(true) {
			if(r2 % a == 0&& r2 % b == 0) {
				break;
			}
			r2++;
		}
		
		bw.append(r1 + "\n" + r2);
		bw.flush();
		bw.close();
		

	}

}
