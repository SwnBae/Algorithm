import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");

		int n = Integer.parseInt(nm[0]); // 10
		int m = Integer.parseInt(nm[1]); // 8 세로

		
		String[][] space = new String[m][n];

		int count = Integer.parseInt(br.readLine());

		for (int t = 1; t <= count; t++) {
			String[] input = br.readLine().split(" ");
			int option = Integer.parseInt(input[0]);
			int num = Integer.parseInt(input[1]);

			if (option == 1) {
				for(int i = 0; i < m; i++) {
					for(int j = num; j < n; j++) {
						space[i][j] += t;
					}
				}
			} else {
				for(int i = num; i < m; i++) {
					for(int j = 0; j < n; j++) {
						space[i][j] += t;
					}
					
				}
			}
		}
		
		
		Map<String,Integer> countMap = new HashMap<>();
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(countMap.containsKey(space[i][j])) {
					countMap.put(space[i][j], countMap.get(space[i][j]) + 1);
				} else {
					countMap.put(space[i][j], 1);
				}
			}
		}
		
		int max = 0;
		
		for(String key : countMap.keySet()) {
			max = Math.max(max, countMap.get(key));
		}
		
		
		System.out.println(max);

	}

}
