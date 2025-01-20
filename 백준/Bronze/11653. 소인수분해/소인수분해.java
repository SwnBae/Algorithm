import java.io.*;
import java.util.*;

public class Main {
	
	public static boolean[] getDecimal(int n) {
		boolean[] arr = new boolean[n+1];
		
		arr[0] = arr[1] = false;
		
		for(int i = 2; i <= n; i++) {
			arr[i] = true;
		}
		
		for(int i  = 2; i*i <= n; i++) {
			if(arr[i]) {
				for(int j = i * i; j<=n; j += i) {
					arr[j] = false;
				}
			}
		}
		
		return arr;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		
		int input = Integer.parseInt(br.readLine());
		
		boolean[] decimal = getDecimal(input);
		
		while(input >= 2) {
			for(int i = 0; i<decimal.length; i++) {
				if(decimal[i] && input%i == 0) {
					System.out.println(i);
					input /= i;
					break;
				}
			}
		}

	}

}
