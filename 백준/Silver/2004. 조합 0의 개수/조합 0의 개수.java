import java.io.*;

public class Main {
	
	public static int soin(int num, int div) {
		int temp = num;
		int count = 0;
		int result = 0;
		
		while(temp/div > 0) {
			temp /= div;
			count++;
			result += num/powInt(div,count);
			//System.out.println(num +" "+result);
		}
		
		return result;
	}
	
	public static int powInt(int a, int b) {
		int result = 1;
		for(int i = 0; i<b; i++) {
			result *= a;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		
		int n1_1 = soin(a,2);
		int n1_2 = soin(a,5);
		int n2_1 = soin(b,2);
		int n2_2 = soin(b,5);
		int n3_1 = soin(a-b,2);
		int n3_2 = soin(a-b,5);
		
		int r1 = n1_1 - n2_1 - n3_1;
		int r2 = n1_2 - n2_2 - n3_2;
		
		bw.append(Math.min(r1,r2) + "");
		bw.flush();
		bw.close();

	}

}
