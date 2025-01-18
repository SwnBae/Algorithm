import java.io.*;
import java.util.*;

public class Main {
	
	public static int fac(int num) {
		int result = 1;
		
		if(num == 0) return 1;
		
		for(int i = num; i>0; i--) {
			result *= i;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub\
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		bw.append(fac(n) + "");
		bw.flush();
		bw.close();
	}

}
