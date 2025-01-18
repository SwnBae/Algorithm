import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		int n1 = n/5;
		int n2 = n/25;
		int n3 = n/125;
		
		bw.append((n1+n2+n3)+"");
		
		bw.flush();
		bw.close();
	}

}
