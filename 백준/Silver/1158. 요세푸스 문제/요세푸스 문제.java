import java.util.*;

public class Main {
	
	public static List<Integer> table = new ArrayList<>();
	public static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]); //(idx + (k-1)) % table.size()
		int idx = 0;
		
		for(int i=0;i<n;i++) {
			table.add(i+1);
		}
		
		while(!table.isEmpty()) {
			idx = (idx + (k-1)) % table.size();
			result.add(table.remove(idx));
		}
		
		String st = "<";
		
		for(int i = 0; i<n; i++) {
			if(i == n-1) {
				st += result.get(i) + ">";
				break;
			}
			st += result.get(i) + ", ";
		}
		
		System.out.println(st);
	}

}
