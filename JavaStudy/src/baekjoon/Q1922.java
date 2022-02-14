// 네트워크 연결 (백준 1922번)

// 크루스칼 알고리즘 - MST : 사이클확인 (Union-Find 이용)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1922 {
	static private int[] parents;
	
	public static class Type implements Comparable<Type> {
		private int from;
		private int to;
		private int weight;
		
		public Type(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Type o) {
			return this.weight - o.weight; // 비용을 기준으로 오름차순
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수(노드의 수)
		int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수(간선의 수)
		
		Queue<Type> pq = new PriorityQueue<Type>();
		StringTokenizer st = new StringTokenizer("");
		parents = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Type(from, to, weight));
		}
		
		// 부모=자신 으로 처음 초기화
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int total = 0;
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			int rx = findParent(t.from);
			int ry = findParent(t.to);
			
			// 사이클이 발생하지 않는 경우에만 집합에 포함
			if(!isCycle(rx, ry)) {
				total += t.weight;
				unionParent(t.from, t.to);
			}
		}
		System.out.print(total);
	}
	
	// 자신이 연결된 노드의 끝을 찾아 본질의 부모를 찾아주기
	static int findParent(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = findParent(parents[x]);
	}
	
	// 더 작은 값을 부모로 선정 (부모 노드를 합침)
	static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}
	
	// 사이클인지 판단 (같은 부모인지 체크)
	static boolean isCycle(int x, int y) {
		if(x == y) return true;
		return false;
	}
}
