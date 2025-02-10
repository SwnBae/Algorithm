import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int num = Integer.parseInt(input);
		
		int[] arr = new int[input.length()]; //이전 자릿수까지 계산
		
		arr[0] = 0;
		int sum = 0;
		
		for(int i = 1; i < arr.length; i++) {
			arr[i] = 10*arr[i-1] + 9;
			sum += ((arr[i] - arr[i-1]) * i);
		}
		
		sum += arr.length * (num - arr[arr.length-1]);
		
		System.out.println(sum);
	}
}
