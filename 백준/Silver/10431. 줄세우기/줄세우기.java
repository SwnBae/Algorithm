import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int p = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < p; t++) {
			String[] input = br.readLine().split(" ");
			
			int tc = Integer.parseInt(input[0]);
			int[] arr = new int[20];
			List<Integer> list = new ArrayList<>();
			int cnt = 0;
			
			for(int i = 0; i < 20; i++) {
				arr[i] = Integer.parseInt(input[i+1]);
			}
			
			list.add(arr[0]);
			
			for(int i = 1; i < arr.length; i++) {
				int tmpCnt = 0;
				int tmpIdx = i;
				
				for(int j = list.size() - 1; j >=0 ; j--) {
					if(list.get(j) > arr[i]) {
						tmpCnt++;
						tmpIdx = j;
					}
				}
				
				list.add(tmpIdx, arr[i]);
				cnt += tmpCnt;
			}
			
			System.out.println(tc + " " + cnt);
			
		}

	}

}
