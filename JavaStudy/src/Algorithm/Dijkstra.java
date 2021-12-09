package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
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
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호
		
		LinkedList<type>[] graph = new LinkedList[V+1];
		
		for(int i=0; i<=V; i++) {
			graph[i] = new LinkedList<type>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // u -> v 간선의 가중치
			graph[u].add(new type(v, w));
		}
		
		int[] stPath = new int[V+1]; // K에서 시작했을 경우 최단경로 저장하는 배열
		Arrays.fill(stPath, Integer.MAX_VALUE);
		Boolean[] isVisited = new Boolean[V+1];
		dijkstra(graph, isVisited, stPath, V, K);
	}
	public static void dijkstra(LinkedList<type>[] graph, Boolean[] isVisited, int[] stPath, int V, int K) {
		// Queue<type> queue = new LinkedList<type>(); // Queue를 사용할 경우 시간초과 뜸 (TLE = Time Limit Exceeded)
		PriorityQueue<type> queue = new PriorityQueue<>();
		
		queue.add(new type(K, 0));
		stPath[K] = 0;
		isVisited[K] = true;

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
		for(int i=1; i<stPath.length; i++) {
			if(stPath[i]==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(stPath[i]);
		}
	}
}
