import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		int[] idx = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			idx[i] = Integer.parseInt(input[i-1]);
		}
		
		list.add(0, 1);
		
		for(int i = 2; i <= n; i++) {
			list.add((i-1) - idx[i],i);
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(i > 0) sb.append(" ");
			sb.append(list.get(i));
		}

		System.out.println(sb.toString());
	}

}
