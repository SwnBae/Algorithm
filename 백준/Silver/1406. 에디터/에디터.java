import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split("");
		
		LinkedList<String> list = new LinkedList<>();
		
		for(int i=0;i<input.length;i++) {
			list.add(input[i]);
		}
		
		ListIterator<String> iter = list.listIterator();
		
		int n = Integer.parseInt(br.readLine());
		
		while(iter.hasNext()) {
			iter.next();
		}
		
		for(int i=0;i<n;i++) {
			String[] mtd = br.readLine().split(" ");
			String option = mtd[0];
			
			switch (option) {
				case "L":
					if(iter.hasPrevious()) {
						iter.previous();
					}
					break;
					
				case "D":
					if(iter.hasNext()) {
						iter.next();
					}
					break;
				
				case "B":
					if(iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
					break;
					
				case "P":
					String plus = mtd[1];
					iter.add(plus);
					}
		}
		
		for(String s : list) {
			bw.write(s);
		}
		
		bw.flush(); // 남아있는 데이터를 모두 출력시킴
		bw.close(); // 스트림을 닫음
	}
}
