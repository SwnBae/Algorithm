import java.io.*;

public class Main {
	
	public static long calnum(String input) {
		long result = 0;
		int count = 0;
		
		for(int i = input.length()-1; i >= 0; i--) {
			result += (input.charAt(count) - '0') * (long)Math.pow(10, i);
			//System.out.println(input.charAt(count)- '0');
			//System.out.println((int)input.charAt(count));
			count++;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		String num1 = input[0] + input[1];
		String num2 = input[2] + input[3];
		
		long num = calnum(num1) + calnum(num2);
		
		bw.append(num + "");
		bw.flush();
		bw.close();
	}

}
