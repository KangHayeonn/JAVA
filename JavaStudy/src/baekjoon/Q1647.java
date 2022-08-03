// ���� ���� ��ȹ (���� ���4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1647 {
	static int[] parent;
	public static class Type {
		int a, b, c;
		public Type(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Type> pq = new PriorityQueue<>((x, y) -> x.c - y.c);
		parent = new int[N+1];
		int a, b, c; // a����, b����, c:������
		
		for(int i=1; i<N+1; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			pq.offer(new Type(a, b, c));
		}
		
		int parentA, parentB;
		int sum=0, max=-1;
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			
			parentA = find(t.a); // 1<-2<-5
			parentB = find(t.b);
			
			if(!isCycle(parentA, parentB)) { // ����Ŭ�� �Ȼ���� ���
				union(parentA, parentB);
				sum += t.c;
				if(max < t.c) max = t.c;
			}
		}
		
		System.out.println(sum - max);
	}
	private static int find(int x) {
		if(parent[x] == x) return x; 
		return parent[x] = find(parent[x]); // x�� �ֻ��� �θ� (x�� �θ��� �θ� ����)
	}
	private static void union(int a, int b) {
		if(a > b) {
			parent[a] = b;
		} else if(a < b) {
			parent[b] = a;
		}
		return;
	}
	private static boolean isCycle(int a, int b) {
		if(a==b) return true; // �̹� ����� ���
		return false;
	}
}
