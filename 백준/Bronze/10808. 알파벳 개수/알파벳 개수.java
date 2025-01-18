import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static Map<String,Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 26; i++) { // 26개의 알파벳을 반복
            String letter = String.valueOf((char) ('a' + i)); // 'a'에 i를 더해 문자로 변환 후 String으로 변환
            map.put(letter, 0);
        }
		
		String[] input = br.readLine().split("");
		
		for(String s : input) {
			if(map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			}
		}
		
		for(String key : map.keySet()) {
			bw.append(map.get(key) + " ");
		}
		
		bw.flush();
		bw.close();

	}

}
