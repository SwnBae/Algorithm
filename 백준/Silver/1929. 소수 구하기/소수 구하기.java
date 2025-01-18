import java.io.*;
import java.util.*;

public class Main {
	
	public static boolean[] check(int num) {
		boolean[] temp = new boolean[num+1];
		
		for(int i=2;i<=num;i++) {
			temp[i] = true;
		}
		
		temp[0] = temp[1] = false;
		
		for(int i=2;i<=Math.sqrt(num); i++) {
			if(temp[i]) {
				for(int j = i*i; j<=num; j+=i) {
					temp[j] = false;
				}
			}
		}
		return temp;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		String[] input = br.readLine().split(" ");
		
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		boolean[] result = check(b);
		
		for(int i=a; i<=b; i++) {
			if(result[i]) bw.append(i + "\n");
		}
		
		bw.flush();
		bw.close();

	}

}
