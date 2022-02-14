// ��Ʈ��ũ ���� (���� 1922��)

// ũ�罺Į �˰��� - MST : ����ŬȮ�� (Union-Find �̿�)

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
			return this.weight - o.weight; // ����� �������� ��������
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ��ǻ���� ��(����� ��)
		int M = Integer.parseInt(br.readLine()); // ������ �� �ִ� ���� ��(������ ��)
		
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
		
		// �θ�=�ڽ� ���� ó�� �ʱ�ȭ
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int total = 0;
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			int rx = findParent(t.from);
			int ry = findParent(t.to);
			
			// ����Ŭ�� �߻����� �ʴ� ��쿡�� ���տ� ����
			if(!isCycle(rx, ry)) {
				total += t.weight;
				unionParent(t.from, t.to);
			}
		}
		System.out.print(total);
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
		if(x == y) return true;
		return false;
	}
}
