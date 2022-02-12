// Kruskal Algorithm : ���� ���� ������� ��� ��带 ���� (MST)
// Union-Find (����Ŭ üũ�ϱ� ���� ���)

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
			return this.value - o.value; // ��������
		}
	}
	public static void main(String args[]) {
		n = 7;
		int[][] graph = {{1,2,29}, {1,5,75},{2,3,35},{2,6,34}, {3,4,7},{4,6,23},
				{4,7,13}, {5,6,53}, {6,7,25}};
		
		parents = new int[n+1];
		
		// ������ ������ �θ�� ó�� �ʱ�ȭ
		for(int i=1; i<n+1; i++) {
			parents[i] = i;
		}
		
		Queue<Node> pq = new PriorityQueue<>();
		
		for(int i=0; i<graph.length; i++) {
			int from = graph[i][0];
			int to = graph[i][1];
			int value = graph[i][2];
			
			// ������ �������� �������� ���ĵ�
			pq.add(new Node(from, to, value));
		}
		
		int size = pq.size();
		int total = 0;
		
		// ���� �ϳ��� ��ȸ (����� ���� ��������)
		
		for(int i=0; i<size; i++) {
			Node node = pq.poll();
			int rx = findParent(node.to);
			int ry = findParent(node.from);
			
			// ����Ŭ�� �߻����� �ʴ� ��쿡�� ���տ� ����
			if(!isCycle(rx, ry)) {
				total += node.value;
				unionParent(node.from, node.to);
			}
		}
		
		System.out.println(total);
	}
	
	// �ڽ��� ����� ����� ���� ã�� ������ �θ� ã���ֱ�
	static int findParent(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = findParent(parents[x]);
	}
	
	// �� ���� ���� �θ�� ���� (�θ� ��带 ��ħ)
	static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}
	
	// ����Ŭ���� �Ǵ� (���� �θ����� üũ)
	static boolean isCycle(int x, int y) {
		// int x = findParent(parent, x);
		// int y = findParent(parent, y);
		if(x == y) return true;
		return false;
	}
}
