// 파티 (백준 1238번)

/* [ 알고리즘 ]
 * 
 * 1. 특정 노드에서 X번 마을로 가는 최단경로 (ex, 1 -> 2로 가는 최단경로)
 * 2. X번 마을에서 특정 노드로 가는 최단경로 (ex, 2 -> 1로 가는 최단경로)
 * 3. 1번과 2번에서 나온 경로값을 더해서 각 노드에 해당하는 인덱스에 넣어준다.
 * 4. 3번의 인덱스에서 제일 최대값을 출력 (가장 오래 걸리는 소요시간)
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
		private int weight; // graph에선 가중치, queue에선 Depth
		
		public type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
	    public int compareTo(type o) {
	        return weight - o.weight; // 최단경우가 짧은 순으로 정렬 (새로 들어온 경로가 더 짧을 경우 양수가 나오므로 교환)
	    }
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생의 수(마을의 수)
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		int X = Integer.parseInt(st.nextToken()); // X번 마을
		
		graph = new LinkedList[N+1];
		
		for(int i=0; i<=N; i++) {
			graph[i] = new LinkedList<type>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // u -> v 간선의 가중치
			graph[u].add(new type(v, w));
		}
		
		int[] stPath = new int[N+1]; // K에서 시작했을 경우 최단경로 저장하는 배열
		Arrays.fill(stPath, Integer.MAX_VALUE);
		Boolean[] isVisited = new Boolean[N+1];
		dijkstra(graph, isVisited, stPath, N, X); // 2번에서 [ 0 , 4, 0, 1, 3 ]
		
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
