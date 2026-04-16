import java.util.*;

class Node {
    int num;
    int y;
    int x;
    
    Node(int num, int y, int x) {
        this.num = num;
        this.y = y;
        this.x = x;
    }
}

class TreeNode{
    int num;
    TreeNode left, right;
    
    TreeNode(int num) {
        this.num = num;
    }
}

class Solution {
    PriorityQueue<Node>[] pq;
    int[][] answer;
    int count;
    
    public int[][] solution(int[][] nodeinfo) {
        pq = new PriorityQueue[1001];
        answer = new int[2][nodeinfo.length];
        
        for(int i = 0; i <= 1000; i++) {
            pq[i] = new PriorityQueue<>((o1, o2) -> {
                if(o1.y == o2.y) return Integer.compare(o1.x, o2.x);
                return Integer.compare(o2.y, o1.y);
            });
        }
        
        for(int i = 0; i < nodeinfo.length; i++) {
            pq[1000].add(new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]));
        }
        
        int curY = pq[1000].peek().y;
        int level = 0;
        
        while(!pq[1000].isEmpty()) {
            Node cur = pq[1000].poll();
            if(cur.y != curY) {
                level++;
                curY = cur.y;
            }
            
            pq[level].add(new Node(cur.num, level, cur.x));
        }
        
        TreeNode root = new TreeNode(-1);
        
        setTree(root, 0, -1, 100001);
        
        count = 0;
        preOrder(root);
        count = 0;
        postOrder(root);
        
        return answer;
    }
    
    public void setTree(TreeNode treeNode, int level, int l, int r) {
        if(pq[level].isEmpty() || (l > pq[level].peek().x || r < pq[level].peek().x)) return;
        
        Node cur = pq[level].poll();
        treeNode.num = cur.num;
        treeNode.left = new TreeNode(-1);
        treeNode.right = new TreeNode(-1);
        setTree(treeNode.left, level + 1, l, cur.x);
        setTree(treeNode.right, level + 1, cur.x, r);
    }
    
    public void preOrder(TreeNode treeNode) {
        answer[0][count++] = treeNode.num;
        
        
        if(treeNode.left != null && treeNode.left.num != -1) {
            preOrder(treeNode.left);
        }
        
        if(treeNode.right != null && treeNode.right.num != -1) {
            preOrder(treeNode.right);
        }
    }
    
    public void postOrder(TreeNode treeNode) {
        if(treeNode.left != null && treeNode.left.num != -1) {
            postOrder(treeNode.left);
        }
        
        if(treeNode.right != null && treeNode.right.num != -1) {
            postOrder(treeNode.right);
        }
        
        answer[1][count++] = treeNode.num;
    }
}