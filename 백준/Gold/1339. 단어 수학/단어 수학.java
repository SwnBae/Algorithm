import java.io.*;
import java.util.*;

class Word{
    char word;
    int weight;

    public Word(char word, int weight) {
        this.word = word;
        this.weight = weight;
    }
}

public class Main {
    public static int n;
    public static String[] nums; // 계산할 수들 집합
    public static Map<Character, Integer> map;
    public static int max;

    public static void calculate() {
        int sum = change(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            sum += change(nums[i]);
        }

        max = sum;
    }

    public static int change(String n) {
        int result = 0;
        for(int i = n.length() - 1; i >= 0; i--) {
            result += map.get(n.charAt(i)) * Math.pow(10, n.length() - 1 - i);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = new String[n];
        map = new HashMap<>();
        max = 0;

        PriorityQueue<Word> priorityQueue = new PriorityQueue<Word>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return Integer.compare(o2.weight, o1.weight);
            }
        });

        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            nums[i] = input;

            for(int j = input.length() - 1; j >= 0; j--) {
                int weight = (int) Math.pow(10, input.length() - 1 - j);

                if(!map.containsKey(input.charAt(j))){
                    map.put(input.charAt(j), weight);
                } else{
                    map.put(input.charAt(j), map.get(input.charAt(j)) + weight);
                }
            }
        }

        for(char c : map.keySet()) {
            priorityQueue.add(new Word(c,map.get(c)));
        }

        int tmp = 9;

        while(!priorityQueue.isEmpty()){
            Word w = priorityQueue.poll();
            map.put(w.word, tmp--);
        }
        calculate();
        System.out.println(max);
    }
}
