import java.io.*;
import java.util.*;

public class Main {
	public static Map<Character, Integer> map = new HashMap<>();
	
	public static int pow(int a, int b) {
		int result = a;
		
		if(b == 0) return 1;
		
		for(int i = 1; i < b; i++) {
			result *= a;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int mapNum = 10;
		
		for(char c = 'A'; c <= 'Z'; c++) {
			map.put(c, mapNum);
			mapNum++;
		}
		
		String[] num = input[0].split(""); 
		int notation = Integer.parseInt(input[1]); // 곱하기
		
		int result = 0;
		
		int size = num.length - 1;
		
		for(int i = 0; i<=size; i++) {
			int temp = 0;
			if(map.containsKey(num[size - i].charAt(0))) {
				temp = map.get(num[size - i].charAt(0));
			} else {
				temp = Integer.parseInt(num[size - i]);
			}
			
			temp = temp * pow(notation,i);
			result += temp;
		}
		
		System.out.println(result);
		

	}

}
