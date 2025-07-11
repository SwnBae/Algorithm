import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        Map<String, Node> child = new TreeMap<>();
        boolean isEnd;
    }

    static class Trie{
        Node root;

        Trie(){
            this.root = new Node();
        }

        void insert(String[] arr){
            Node node = this.root;

            for(int i = 1; i <= Integer.parseInt(arr[0]); i++){
                String str = arr[i];

                if(!node.child.containsKey(str)){
                    node.child.put(str, new Node());
                }

                node = node.child.get(str);
            }

            node.isEnd = true;
        }
    }

    public static StringBuilder sb;

    public static void dfs(Node node, int depth){
        for(String str : node.child.keySet()){
            sb.append("--".repeat(depth));
            sb.append(str + "\n");
            dfs(node.child.get(str), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();
        Trie trie = new Trie();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            trie.insert(br.readLine().split(" "));
        }

        dfs(trie.root, 0);

        System.out.println(sb.toString());
    }
}
