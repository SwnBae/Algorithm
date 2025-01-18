import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	
	public static Map<Integer,Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        int n = Integer.parseInt(br.readLine());
	        String[] input = br.readLine().split(" ");
	        
	        int[] arr = new int[n];
	        int[] result = new int[n];
	        
	        for(int i=0;i<n;i++) {
	        	int k = Integer.parseInt(input[i]);
	        	arr[i] = k;
	        	result[i] = -1;
	        	
	        	if(map.containsKey(k)) {
	        		map.replace(k, map.get(k)+1);
	        	} else {
	        		map.put(k, 1);
	        	}
	        }
	        
	        Stack<Integer> stack = new Stack<>();
	        
	        for(int i=0;i<n;i++) {
	        	while(!stack.isEmpty() && map.get(arr[i]) > map.get(arr[stack.peek()])) {
	        		int tempKey = stack.pop();
	        		result[tempKey] = arr[i];
	        	}
	        	stack.add(i);
	        }
	        
	        for (int i = 0; i < n; i++) {
	            bw.append(result[i] + " ");
	        }
	        
	        bw.flush();
	        bw.close();
	}

}
