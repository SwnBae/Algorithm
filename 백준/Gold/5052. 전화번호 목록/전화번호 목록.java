import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;

        public Map<Character, Node> getChild(){
            return child;
        }
    }

    static class Trie{
        Node root;

        Trie(){
            this.root = new Node();
        }

//        public void insert(String str){
//            Node node = this.root;
//
//            for(int i = 0; i < str.length(); i++){
//                char c = str.charAt(i);
//
//                if(!node.child.containsKey(c)){
//                    node.child.put(c, new Node());
//                }
//
//                node = node.child.get(c);
//            }
//
//            node.isEnd = true;
//        }

        public boolean insert(String str){
            Node node = this.root;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(!node.child.containsKey(c)){
                    node.child.put(c, new Node());
                }

                node = node.child.get(c);
                if(node.isEnd) return false;
            }

            node.isEnd = true;

            if(!node.child.isEmpty()) return false;

            return true;
        }

        public boolean search(String str){
            Node node = this.root;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(!node.child.containsKey(c)){
                    node = node.child.get(c);
                } else {
                    return false;
                }
            }

            return node.isEnd;
        }

        public boolean isConsistency(String str){
            Node node = this.root;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(node.child.containsKey(c)){
                   node =  node.child.get(c);
                }

                if(node.isEnd && node.child.size() > 1) return false;
            }

            return true;
        }

        public boolean delete(String str){
            return delete(this.root, str, 0);
        }

        private boolean delete(Node node, String str, int idx){
            char c = str.charAt(idx);

            if(!node.child.containsKey(c)) return false;

            Node cur = node.child.get(c);
            idx++;

            if(idx == str.length()){
                if(!cur.isEnd) return false;

                cur.isEnd = false;

                if(cur.child.isEmpty()) node.child.remove(c);
            } else{
                if(!this.delete(cur,str,idx)) return false;

                if(!cur.isEnd && cur.child.isEmpty()) node.child.remove(c);
            }
            return true;
        }
    }

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            int n = Integer.parseInt(br.readLine());
            boolean isConsistency = true;

            Trie trie = new Trie();

            for(int i = 0; i < n; i++){
                String input = br.readLine();

                if(!trie.insert(input)) {
                    isConsistency = false;
                }
            }

            System.out.println(isConsistency ? "YES" : "NO");
        }
    }
}
