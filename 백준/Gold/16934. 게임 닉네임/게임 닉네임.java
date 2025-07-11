import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        Map<Character,Node> child = new HashMap<>();
        int endCnt;
    }

    static class Trie{
        Node root;

        Trie(){
            this.root = new Node();
        }

        String insert(String str){
            StringBuilder sb = new StringBuilder();
            boolean last = false;
            Node node = this.root;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(!node.child.containsKey(c)){
                    node.child.put(c,new Node());
                    if(!last){
                        sb.append(c);
                        last = true;
                    }
                }

                node = node.child.get(c);

                if(!last){
                    sb.append(c);
                }
            }

            node.endCnt++;

            if(node.endCnt > 1){
                sb.append(node.endCnt);
            }

            return sb.toString();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Trie trie = new Trie();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            sb.append(trie.insert(br.readLine()) + "\n");
        }

        System.out.println(sb.toString());
    }
}
