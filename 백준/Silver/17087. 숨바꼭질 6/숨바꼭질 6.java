import java.io.*;

public class Main {
	public static int getArrGCD(int[] arr) {
		if(arr.length == 1) return arr[0];
	
		int result = getGCD(arr[0],arr[1]);
		
		for(int i=2;i<arr.length;i++) {
			result = getGCD(result, arr[i]);
		}
		
		return result;
	}
	
	public static int getGCD(int a, int b) {
		while(b!=0) {
			int temp = b;
			b = a%b;
			a = temp;
		}
		return a;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp1 = br.readLine().split(" ");
		String[] temp2 = br.readLine().split(" ");
		
		int t = Integer.parseInt(temp1[0]);
		int present = Integer.parseInt(temp1[1]);
		
		int[] arr = new int[t];
		
		for(int i=0;i<t;i++) {
			arr[i] = Math.abs(Integer.parseInt(temp2[i]) - present);
		}
		
		//Arrays.sort(arr);
		
		bw.append(getArrGCD(arr) + "");
		bw.flush();
		bw.close();
	}

}
