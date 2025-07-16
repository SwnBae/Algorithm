import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class Node{
    Map<Character, Node> child = new HashMap<>();
    boolean isEnd;
}

class Trie{
    Node root;

    Trie() {
        root = new Node();
    }

    void insert(String str){
        Node node = this.root;

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(!node.child.containsKey(c)) {
                node.child.put(c, new Node());
            }

            node = node.child.get(c);
        }

        node.isEnd = true;
    }

    int getCnt(String str){
        int cnt = 1;
        Node node = this.root.child.get(str.charAt(0));

        for(int i = 1; i < str.length(); i++){
            char c = str.charAt(i);

            if(node.child.size() != 1 || (node.child.size() == 1 && node.isEnd)) {
                cnt++;
            }

            node = node.child.get(c);
        }

//        System.out.println(str + ": " + cnt);
        return cnt;
    }
}

public class Main {

    public static String line;
    public static int num;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((line = br.readLine()) != null){
            double average = 0;
            double sum = 0;
            num = Integer.parseInt(line);

            Trie trie = new Trie();
            List<String> list = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("0.00");

            for(int i = 0; i < num; i++) {
                String input = br.readLine();
                trie.insert(input);
                list.add(input);
            }

            for(String str : list) {
                sum += trie.getCnt(str);
            }

            average = sum / num;
            System.out.println(df.format(average));


        }
    }
}
