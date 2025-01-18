import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		
		String[] result = new String[input.length()];
		
		for(int i = 0; i<input.length(); i++) {
			result[i] = (String) input.substring(i, input.length());
		}
		
		Arrays.sort(result);
		
		for(String s : result) {
			bw.append(s + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
