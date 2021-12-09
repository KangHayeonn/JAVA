// Ÿ�Ӹӽ� (���� 11657��)

/* [ �˰��� ]
 * 
 * - ����-���� �˰��� �̿� ( ���� ���� ���� )
 * - ���� ������ ��ȯ Ȯ�� ���� V �ܰ迡�� �ִ� �Ÿ� ���� �Ǵ��� Ȯ�� ( ���ŵǸ� ��ȯ O, ���ŵ��� ������ ��ȯ X ) 
 */

// ����
// ������ ������ 500��, ������ ������ 6000��, �ּ� ����ġ�� -10000�̶�� ����� underflow�� �߻��� �� ����
// (-500 * 6000 * 10000 = -3 * 10^10, Integer.MIN_VALUE = �� -2*10^9)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q11657 {
	public static class type {
		private int node;
		private int weight;
		
		public type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // ����(����)�� ����
		int E = Integer.parseInt(st.nextToken()); // ���� �뼱(����)�� ����
		
		LinkedList<type>[] graph = new LinkedList[V+1];
		for(int i=0; i<=V; i++) {
			graph[i] = new LinkedList<type>();
		}
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // ���۵���
			int B = Integer.parseInt(st.nextToken()); // ��������
			int C = Integer.parseInt(st.nextToken()); // ������ Ÿ�� �̵��ϴµ� �ɸ��� �ð�
			
			graph[A].add(new type(B, C)); // ���� �׷���
		}
		
		if(graph[1].size()==0) {
			for(int i=2; i<=V; i++) {
				System.out.println(-1);
			}
			return;
		}
		bellman_ford(graph, V, E, 1);
	}
	public static void bellman_ford(LinkedList<type>[] graph, int V, int E, int start) {
		long[] distance = new long[V+1];  // ����ʰ� ������ long Ÿ������ �ٲ� (���� ���� ��ȯ�� ���� ����÷ο� �߻��� ���ɼ� ����)
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;  // start->start�� ���� ��� 0���� �ʱ�ȭ
		boolean check = false;
		
		for(int i=0; i<V; i++) {
			for(int j=1; j<=V; j++) {
				for(type t: graph[j]) {
					int from = j;
					int to = t.node;
					long weight = t.weight;
					
					if(distance[from] == Integer.MAX_VALUE) continue; // �񱳹����� �ڷ����� ������ �ʰ��� �� ����
					
					if(distance[to] > distance[from] + weight) {
						distance[to] = distance[from] + weight;
						
						if(i == V-1) check = true; // ���� ���� ��ȯ Ȯ��
					}
				}
			}
		}

		if(check == true) {
			 System.out.print(-1);
		} else {
			for(int i=2; i<=V; i++) {
				if(distance[i] == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else {
					System.out.println(distance[i]);
				}
			}
		}
	}
}
