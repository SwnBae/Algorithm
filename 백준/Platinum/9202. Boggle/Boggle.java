import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};
    public static int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};

    static class Node{
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
        String word;
    }

    static class Trie{
        Node root;
        char[][] currentBoard;

        Trie(){
            this.root = new Node();
        }

        void insert(String str){
            Node node = this.root;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(!node.child.containsKey(c)){
                    node.child.put(c, new Node());
                }

                node = node.child.get(c);
            }

            node.word = str;
            node.isEnd = true;
        }

        void search(char[][] board){
            currentBoard = board;
            checked = new boolean[w];

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    Node node = this.root;
                    if(!node.child.containsKey(currentBoard[i][j])) continue;

                    visited = new boolean[4][4];
                    visited[i][j] = true;
                    node = node.child.get(currentBoard[i][j]);
                    search(i,j,node,0);
                }
            }
        }

        void search(int y, int x, Node node, int depth){
            if(node.isEnd && !checked[index.get(node.word)]){
                addScore(node.word);
                checked[index.get(node.word)] = true;
            }

            if(depth == 8) return;

            for(int i = 0; i < 8; i++){
                int tmpY = y + dy[i];
                int tmpX = x + dx[i];

                if(outBound(tmpY,tmpX) || visited[tmpY][tmpX] || !node.child.containsKey(currentBoard[tmpY][tmpX])) continue;

                visited[tmpY][tmpX] = true;
                search(tmpY,tmpX, node.child.get(currentBoard[tmpY][tmpX]), depth + 1);
                visited[tmpY][tmpX] = false;
            }
        }

        void addScore(String str){
            int score;

            if(str.length() <= 2){
                score = 0;
            } else if (str.length() <= 4) {
                score = 1;
            } else if (str.length() <= 5) {
                score = 2;
            } else if (str.length() <= 6) {
                score = 3;
            } else if (str.length() <= 7) {
                score = 5;
            } else{
                score = 11;
            }

            totalCnt++;
            totalScore += score;

            if(str.length() > maxWord.length()) {
                maxWord = str;
            } else if(str.length() == maxWord.length()) {
                if(str.compareTo(maxWord) < 0) maxWord = str;
            }
        }
    }

    public static List<char[][]> boards;

    public static int w;

    public static int totalCnt;
    public static String maxWord;
    public static int totalScore;

    public static boolean[][] visited;
    public static Map<String, Integer> index;
    public static boolean[] checked;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= 4 || x < 0 || x >= 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        w = Integer.parseInt(br.readLine());
        boards = new ArrayList<>();
        index = new HashMap<>();
        int idx = 0;

        Trie trie = new Trie();

        for(int i = 0; i < w; i++){
            String input = br.readLine();
            index.put(input, idx++);
            trie.insert(input);
        }

        br.readLine();

        int b = Integer.parseInt(br.readLine());

        for(int i = 0; i < b; i++){
            char[][] board = new char[4][4];
            for(int j = 0; j < 4; j++){
                String input = br.readLine();
                for(int k = 0; k < 4; k++){
                    board[j][k] = input.charAt(k);
                }
            }
            boards.add(board);

            if(i != b - 1) br.readLine();
        }

        for(char[][] cb : boards){
            totalCnt = 0;
            totalScore = 0;
            maxWord = "";
            trie.search(cb);
            System.out.println(totalScore + " " + maxWord + " " + totalCnt);
        }
    }
}
