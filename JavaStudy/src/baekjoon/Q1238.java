// ��Ƽ (���� 1238��)

/* [ �˰��� ]
 * 
 * 1. Ư�� ��忡�� X�� ������ ���� �ִܰ�� (ex, 1 -> 2�� ���� �ִܰ��)
 * 2. X�� �������� Ư�� ���� ���� �ִܰ�� (ex, 2 -> 1�� ���� �ִܰ��)
 * 3. 1���� 2������ ���� ��ΰ��� ���ؼ� �� ��忡 �ش��ϴ� �ε����� �־��ش�.
 * 4. 3���� �ε������� ���� �ִ밪�� ��� (���� ���� �ɸ��� �ҿ�ð�)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1238 {
	static LinkedList<type>[] graph;
	public static class type implements Comparable<type>{
		private int node;
		private int weight; // graph���� ����ġ, queue���� Depth
		
		public type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
	    public int compareTo(type o) {
	        return weight - o.weight; // �ִܰ�찡 ª�� ������ ���� (���� ���� ��ΰ� �� ª�� ��� ����� �����Ƿ� ��ȯ)
	    }
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // �л��� ��(������ ��)
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		int X = Integer.parseInt(st.nextToken()); // X�� ����
		
		graph = new LinkedList[N+1];
		
		for(int i=0; i<=N; i++) {
			graph[i] = new LinkedList<type>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // u -> v ������ ����ġ
			graph[u].add(new type(v, w));
		}
		
		int[] stPath = new int[N+1]; // K���� �������� ��� �ִܰ�� �����ϴ� �迭
		Arrays.fill(stPath, Integer.MAX_VALUE);
		Boolean[] isVisited = new Boolean[N+1];
		dijkstra(graph, isVisited, stPath, N, X); // 2������ [ 0 , 4, 0, 1, 3 ]
		
		for(int i=1; i<=N; i++) {
			int[] shortPath = new int[N+1];
			Arrays.fill(shortPath, Integer.MAX_VALUE);
			isVisited = new Boolean[N+1];
			if(i!=X) {
				dijkstra(graph, isVisited, shortPath, N, i);
			}
			stPath[i] += shortPath[X];
		}
	
		int max = 0;
		for(int i=1; i<stPath.length; i++) {
			if(max < stPath[i] && i!=X) max=stPath[i];
		}
		System.out.println(max);
	}
	public static void dijkstra(LinkedList<type>[] graph, Boolean[] isVisited, int[] stPath, int N, int X) {
		PriorityQueue<type> queue = new PriorityQueue<>();
		
		queue.add(new type(X, 0));
		stPath[X] = 0;
		isVisited[X] = true;

		while(!queue.isEmpty()) {
			type q = queue.poll();
			int node = q.node;
			int weight = q.weight;
			
			if(isVisited[node]!=true) continue;
			
			for(type x : graph[node]) {
				int gNode = x.node;
				int gWeight = x.weight;
				
				if(stPath[gNode] > weight + gWeight) {
					stPath[gNode] = weight + gWeight;
					queue.add(new type(gNode, weight + gWeight));
					isVisited[gNode] = true;
				}
			}		
		}
	}
}
