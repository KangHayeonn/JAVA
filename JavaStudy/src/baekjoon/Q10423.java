// 전기가 부족해 (백준 골드2)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q10423 {
	static int[] parent,rank;
	public static class Type {
		int from, to, weight;
		
		public Type(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시 개수
		int M = Integer.parseInt(st.nextToken()); // 케이블 개수
		int K = Integer.parseInt(st.nextToken()); // 발전소 개수
		
		parent = new int[N+1];

		for(int i=1; i<N+1; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int n = Integer.parseInt(st.nextToken());
			parent[n] = -1;
		}
		
		PriorityQueue<Type> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		int from, to, weight;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Type(from, to, weight));
		}
		
		int x, y, sum=0;
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			x = findParent(t.from);
			y = findParent(t.to);
			
			if(!isCycle(x, y)) {
				unionParent(x, y);
				sum += t.weight;
			}
		}
		
		System.out.println(sum);
	}
	public static int findParent(int x) {
		if(parent[x] == -1) return -1;
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}
	public static void unionParent(int x, int y) {
		if(x==-1) {
			parent[y] = x;
		} else if(y==-1) {
			parent[x] = y;
		} else {
			if(x > y) {
				parent[x] = y;
			} else {
				parent[y] = x;
			}
		}
	}
	public static boolean isCycle(int x, int y) {
		if(x == y) return true;
		else return false;
	}
}
