import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split("");
		
		for(String s : input) {
			char temp = s.charAt(0);
			if(s.matches("[A-Z]+")) {
				temp = (char)(((temp + 13 - 65) % 26) + 65);
			} else if(s.matches("[a-z]+")) {
				temp = (char)(((temp + 13-97) % 26) + 97);
			}
			bw.append(temp);
		}
		
		bw.flush();
		bw.close();

	}

}
