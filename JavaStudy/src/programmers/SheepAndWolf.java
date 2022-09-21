package programmers;

import java.util.*;

public class SheepAndWolf {
	public static void main(String args[]) {
		SheepAndWolf s = new SheepAndWolf();
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
 		System.out.println(s.solution(info, edges));
	}
	public int solution(int[] info, int[][] edges) {
        Node[] nArr = new Node[info.length];
        for(int i=0; i<info.length; i++) {
            nArr[i] = new Node(-1, info[i]);
        }
        
        for(int i=0; i<edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            if(nArr[parent].left != null) {
                nArr[parent].setRight(nArr[child]);
            } else {
                nArr[parent].setLeft(nArr[child]);
            }
        }
        
        int[] arr = {0, 0};
        int[] resultArr = dfs(nArr[0], arr);
        
        return resultArr[0];
    }
    public static int[] dfs(Node node, int[] countArr) {
        if(node == null) return countArr;
        
        int[] tmpArr = copyArr(countArr);
        if(node.chk == 0) countArr[0] += 1;
        else countArr[1] += 1;
        
        if(countArr[0] == countArr[1]) return tmpArr;
        
        int[] tmpArr2 = copyArr(countArr);
        
        // 1
        int[] tmpArrLeft = dfs(node.left, tmpArr2);
        int[] resultArrRight = dfs(node.right, tmpArrLeft);
        
        tmpArr2 = copyArr(countArr);
        
        // 2
        int[] tmpArrRight = dfs(node.right, tmpArr2);
        int[] resultArrLeft = dfs(node.left, tmpArrRight);
        
        // 1번과 2번 중 양의 개수가 더 많은 로직의 Array를 return
        if(resultArrRight[0] > resultArrLeft[0]) {
        	if(resultArrRight[0] > tmpArr[0]) return resultArrRight;
        	
        	if(resultArrRight[1] > tmpArr[1]) return tmpArr;
        	else return resultArrRight;
        }
        else {
        	if(resultArrLeft[0] > tmpArr[0]) return resultArrLeft;
        	
        	if(resultArrLeft[1] > tmpArr[1]) return tmpArr;
        	else return resultArrLeft;
        }
    }
    public static int[] copyArr(int[] arr) {
        int[] tmpArr = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            tmpArr[i] = arr[i];
        }
        return tmpArr;
    }
}
class Node {
    Node left;
    Node right;
    int depth;
    int chk; // 1: 늑대, 0: 양
    
    public Node(int depth, int chk) {
        this.depth = depth;
        this.chk = chk;
    }
    
    public Node getLeft() {
        return left;
    }
    
    public void setLeft(Node left) {
        this.left = left;
    }
    
    public Node getRight() {
        return right;
    }
    
    public void setRight(Node right) {
        this.right = right;
    }
    
    public boolean isEnd() {
        if(left == null && right == null) return true;
        else return false;
    }
}
