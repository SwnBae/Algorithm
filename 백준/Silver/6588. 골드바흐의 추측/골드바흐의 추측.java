import java.io.*;
import java.util.*;

public class Main {
//	public static List<Integer> primeList = new ArrayList<>();
	public static List<Integer> inputList = new ArrayList<>();
	
	public static boolean[] getPrime(int num) {
		boolean[] temp = new boolean[num + 1];
		
//		int count = 0;
		
		for(int i = 2; i<=num;i++) {
			temp[i] = true;
		}
		
		temp[0] = temp[1] = false;
		
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(temp[i]) {
				for(int j = i*i; j<=num; j+=i) {
					temp[j] = false;
				}
			}
		}
		
		temp[2] = false;
		return temp;
		
//		int c = 0;
//		
//		for(int i=0; i <=num; i++) {
//			if(temp[i]) {
//				count++;
//			}
//		}
		
//		int[] result = new int[count];
		
//		for(int i=3;i<=num;i++) {
//			if(temp[i]) {
//				primeList.add(i);
////				result[c] = i; && i%2 != 0
////				c++;
//			}
//		}
//		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int max = 0;
		int input;
		boolean gb;
		
		while((input = Integer.parseInt(br.readLine())) != 0) {
			inputList.add(input);
			if(input > max) max = Math.max(max, input);
		}
		
		boolean[] prime = getPrime(max);
		
//		System.out.println(primeList.toString());
		
		for(int i=0; i<inputList.size(); i++) { // n
			int temp = inputList.get(i);
			//int a;
			//int b;
			gb = false;
			
			for(int j = 3; j < prime.length; j++) { //n
				if(prime[j]) {
					if(prime[temp - j]) {
						gb = true;
						bw.append(temp + " = " + j + " + " + (temp -j) + "\n");
						break;
					}
				}
			}
			
//			for(int j = 0; j<primeList.size(); j++) {
//				int pr = primeList.get(j);
//				if(primeList.contains((temp - pr))) {
//					gb = true;
//					a = pr;
//					b = temp - pr;
//					bw.append(temp + " = " + a + " + " + b + "\n");
//					break;
//				}
//			}
			
			if(!gb) bw.append("Goldbach's conjecture is wrong.");
		}
		
		bw.flush();
		bw.close();
	}
}

// 배열로 유지하기 -> contain 시간복잡도 큼..
