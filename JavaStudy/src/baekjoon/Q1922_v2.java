// 네트워크 연결 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1922_v2 {
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
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		Type[] t = new Type[M];
		parent = new int[N+1];
		int a, b, c;
		
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			t[i] = new Type(a, b, c);
		}
		
		Arrays.sort(t, (x, y) -> x.c - y.c);
		
		int parentA, parentB, sum=0;
		
		for(int i=0; i<M; i++) {
			parentA = find(t[i].a);
			parentB = find(t[i].b);
			
			if(!isCycle(parentA, parentB)) {
				union(parentA, parentB);
				sum += t[i].c;
			}
		}
		
		System.out.println(sum);
	}
	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	private static boolean isCycle(int a, int b) {
		if(a==b) return true;
		else return false;
	}
	private static void union(int a, int b) {
		if(a > b) {
			parent[a] = b;
		} else if(a < b) {
			parent[b] = a;
		}
		return;
	}
}
