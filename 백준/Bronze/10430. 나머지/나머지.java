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
		int c = Integer.parseInt(input[2]);
		
		int r1 = (a+b)%c;
		int r2 = ((a%c) + (b%c))%c;
		int r3 = (a*b)%c;
		int r4 = ((a%c) * (b%c))%c;
		
		bw.append(r1 +"\n");
		bw.append(r2 +"\n");
		bw.append(r3 +"\n");
		bw.append(r4 +"\n");
		
		bw.flush();
		bw.close();

	}

}
