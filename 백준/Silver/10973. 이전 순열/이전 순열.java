import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		String[] input = br.readLine().split(" ");
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}

		int pivot = -1;

		for (int i = n - 1; i > 0; i--) {
			if (arr[i - 1] > arr[i]) {
				pivot = i - 1;
				break;
			}
		}

		if (pivot == -1) { // 피봇이 없다면 이미 정렬이 되어 있음
			System.out.println(-1);
			return;
		}

		int idx = -1;

		for (int i = n - 1; i > pivot; i--) {
			if (arr[pivot] > arr[i]) {
				idx = i;
				break;
			}
		}

		int tmp = arr[idx];
		arr[idx] = arr[pivot];
		arr[pivot] = tmp;
		
		// 리버스 직접하기
		int left = pivot + 1;
        int right = n - 1;
        
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

		for (int i = 0; i < arr.length; i++) {
			if (i > 0)
				sb.append(" ");
			sb.append(arr[i]);
		}

		System.out.println(sb.toString());

	}

}
