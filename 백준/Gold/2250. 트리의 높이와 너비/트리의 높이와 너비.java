import java.io.*;
import java.util.*;

class Node {
    int value;
    Node parent;
    Node left;
    Node right;
    int height;
    int idx;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", parent=" + parent.value +

                ", height=" + height +
                ", idx=" + idx +
                '}';
    }
}
// 중위순회 하면서 idx 채우기
public class Main {
    public static int n;
    public static Node[] tree;
    public static int max;
    public static int maxH;
    public static int idx;
    public static int root;

    public static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        Deque<Integer> deque = new ArrayDeque<>();

        queue.add(tree[root]);

        int hgt = 1;

        while(!queue.isEmpty()) {
            Node tmp = queue.poll();

            // 층 바뀌었는지 검사
            if(tmp.height != hgt && !deque.isEmpty()){
                hgt = tmp.height;
                //set에서 꺼내면서, 처음과 마지막 구하기
                int left = deque.remove();
                int right = -1;

                while(!deque.isEmpty()){
                    right = deque.remove();
                }
                if(right == -1){ //노드가 하나이므로, 너비는 1
                    if(max < 1){
                        max = 1;
                        maxH = tree[left].height;
                    }
                } else{
                    if(max < tree[right].idx - tree[left].idx + 1){
                        max = tree[right].idx - tree[left].idx + 1;
                        maxH = tree[right].height;
                    }
                }
            }

            deque.add(tmp.value);

            if(tmp.left != null) {
                queue.add(tmp.left);
            }

            if(tmp.right != null) {
                queue.add(tmp.right);
            }
        }

        if(!deque.isEmpty()){
            int left = deque.remove();
            int right = -1;

            while(!deque.isEmpty()){
                right = deque.remove();
            }

            if(right == -1){ //노드가 하나이므로, 너비는 1
                if(max < 1){
                    max = 1;
                    maxH = tree[left].height;
                }
            } else{
                if(max < tree[right].idx - tree[left].idx + 1){
                    max = tree[right].idx - tree[left].idx + 1;
                    maxH = tree[right].height;
                }
            }
        }
    }

    public static void setHeight(){
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(tree[root]);

        while(!queue.isEmpty()){
            Node tmp = queue.poll();

            if(tmp.left != null) {
                tmp.left.height = tmp.height + 1;

                queue.add(tmp.left);
            }

            if(tmp.right != null) {
                tmp.right.height = tmp.height + 1;
                queue.add(tmp.right);
            }
        }

    }

    public static void setIdx(Node node){
        if(node == null) return;

        setIdx(node.left);
        node.idx = idx++;
        //System.out.println("node: " + node.value + "idx: " + node.idx);
        setIdx(node.right);
    }

    public static int findRoot(Node node){
        int root = -1;
        Node tmp = node;

        while (tmp != null){
            if(tmp.parent == null){
                root = tmp.value;
                break;
            }

            tmp = tmp.parent;
        }

        tree[root].height = 1;

        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        tree = new Node[n+1];
        max = 0;
        idx = 1;

        for(int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");

            int p = Integer.parseInt(input[0]);
            int l = Integer.parseInt(input[1]);
            int r = Integer.parseInt(input[2]);

            if(tree[p] == null) {
                tree[p] = new Node(p);
                tree[p].parent = null;
            }

            if(l != -1) {
                if(tree[l] == null) {
                    tree[l] = new Node(l);
                }

                tree[l].parent = tree[p];
                tree[p].left = tree[l];
            }

            if(r != -1) {
                if(tree[r] == null){
                    tree[r] = new Node(r);
                }

                tree[r].parent = tree[p];
                tree[p].right = tree[r];
            }
        }

        root = findRoot(tree[1]);
        setHeight();

        setIdx(tree[root]); // 루트노드가 1이 아닐 수 있다...
        bfs();

        System.out.println(maxH + " " +max);

//        System.out.println("root" + root);
//
//        for(Node n : tree){
//            if(n == null || n.parent == null) continue;
//            System.out.println(n);
//            if(n.left != null) {
//                System.out.printf("left: " + n.left.value);
//            }
//            if(n.right != null) {
//                System.out.printf(" right: " + n.right.value);
//            }
//        }
    }
}