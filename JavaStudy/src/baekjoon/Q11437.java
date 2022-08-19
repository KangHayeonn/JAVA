// LCA (백준 골드3)
package baekjoon;

import java.util.*;
import java.io.*;

public class Q11437 {
	static int d, N;
	static int[] depthArr; // 노드 별 깊이를 저장
	static int[][] parentArr; // Sparse Table (dp)
	static ArrayList<Integer>[] tree;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		StringTokenizer st;
		
		for(int i=1; i<=N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		int from, to;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			tree[from].add(to);
			tree[to].add(from);
		}
		
		d = getMaxDepth(); // 편향된 트리일 경우 최대 높이
		depthArr = new int[N+1]; // 노드 별 깊이를 저장
		parentArr = new int[d+1][N+1]; // Sparse Table (dp)
		
		init(1, 0, 0);
		getParent();
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(LCA(a, b) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	public static int getMaxDepth() {
		int x = 1;
		int t = 0;
		
		while(x < N) {
			x *= 2;
			t += 1;
		}
		
		return t;
	}
	public static void init(int curr, int depth, int parent) {
		depthArr[curr] = depth;
		
		for(int n : tree[curr]) {
			if(n != parent) {
				init(n, depth+1, curr);
				parentArr[0][n] = curr;
			}
		}
	}
	public static void getParent() {
		for(int r=1; r<=d; r++) {
			for(int c=1; c<N+1; c++) {
				parentArr[r][c] = parentArr[r-1][parentArr[r-1][c]];
			}
		}
	}
	public static int LCA(int a, int b) {
		int depthA = depthArr[a];
		int depthB = depthArr[b];
		
		if(depthA < depthB) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for(int i=d; i>=0; i--) {
			if(Math.pow(2, i) <= depthArr[a] - depthArr[b]) { // 깊이를 맞추기
				a = parentArr[i][a];
			}
		}
		
		if(a==b) return a;
		
		for(int i=d; i>=0; i--) {
			if(parentArr[i][a] != parentArr[i][b]) {
				a = parentArr[i][a];
				b = parentArr[i][b];
			}
		}
		
		return parentArr[0][a];
	}
}
