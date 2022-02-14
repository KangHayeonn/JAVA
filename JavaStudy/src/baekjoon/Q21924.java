// ���� �Ǽ� (���� 21924��)

// ũ�罺Į �˰��� - MST : ����ŬȮ�� (Union-Find �̿�)

/* [ �߰� �˰��� ]
 * 1. ��� ��带 ������ ������ ���� = ����� ���� - 1 ���� Ȯ��
 * 2. ��ü ��뿡�� - �ּ� ��� = �����ϴ� ���
 * 3. ������ int���� �Ѿ �� �����Ƿ� long Ÿ������ ��������� ����!
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q21924 {
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
		public int compareTo(Type t) {
			return this.weight - t.weight; // ����ġ�� �������� ��������
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ����
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		
		Queue<Type> pq = new PriorityQueue<Type>();
		parents = new int[N+1];
		long total = 0; // ��ü ���
		long minTotal = 0; // ����ϴ� �ּ� ���
		int count = 0; // ����� ������ ������ ī��Ʈ (�ּ� ����� ��)
		
		for(int i=0; i<M; i++ ) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Type(from, to, weight));
			total += weight;
		}
		
		// �θ� �ڽ��� �ε��� ������ �ʱ�ȭ
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			int rx = findParent(t.from);
			int ry = findParent(t.to);
			
			if(!isCycle(rx, ry)) {
				minTotal += t.weight;
				unionParent(t.from, t.to);
				count++;
			}
		}
		
		if(count == N-1) {
			System.out.print(total-minTotal);
		} else {
			System.out.print(-1);
		}
	}
	
	// �θ� ã��
	public static int findParent(int x) {
		if(parents[x] == x) {
			return x;
		}
		return findParent(parents[x]);
	}
	
	// �θ� ������ (�� ���� ������)
	public static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}
	
	// ����Ŭ Ȯ��
	public static boolean isCycle(int x, int y) {
		if(x==y) return true;
		return false;
	}
}
