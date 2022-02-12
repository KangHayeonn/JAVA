// Kruskal Algorithm : 가장 적은 비용으로 모든 노드를 연결 (MST)
// Union-Find (사이클 체크하기 위해 사용)

package Algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

public class Kruskal {
	private static int n;
	private static int[] parents;
	
	public static class Node implements Comparable<Node>{
		private int from;
		private int to;
		private int value;
		
		public Node(int from, int to, int value) {
			this.from = from;
			this.to = to;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.value - o.value; // 오름차순
		}
	}
	public static void main(String args[]) {
		n = 7;
		int[][] graph = {{1,2,29}, {1,5,75},{2,3,35},{2,6,34}, {3,4,7},{4,6,23},
				{4,7,13}, {5,6,53}, {6,7,25}};
		
		parents = new int[n+1];
		
		// 본인을 본인의 부모로 처음 초기화
		for(int i=1; i<n+1; i++) {
			parents[i] = i;
		}
		
		Queue<Node> pq = new PriorityQueue<>();
		
		for(int i=0; i<graph.length; i++) {
			int from = graph[i][0];
			int to = graph[i][1];
			int value = graph[i][2];
			
			// 간선의 비용순으로 오름차순 정렬됨
			pq.add(new Node(from, to, value));
		}
		
		int size = pq.size();
		int total = 0;
		
		// 간선 하나씩 조회 (비용이 작은 간선부터)
		
		for(int i=0; i<size; i++) {
			Node node = pq.poll();
			int rx = findParent(node.to);
			int ry = findParent(node.from);
			
			// 사이클이 발생하지 않는 경우에만 집합에 포함
			if(!isCycle(rx, ry)) {
				total += node.value;
				unionParent(node.from, node.to);
			}
		}
		
		System.out.println(total);
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
		// int x = findParent(parent, x);
		// int y = findParent(parent, y);
		if(x == y) return true;
		return false;
	}
}
