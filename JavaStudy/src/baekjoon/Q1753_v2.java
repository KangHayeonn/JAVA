// 최단경로 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1753_v2 {
	static int[] stPath;
	public static class Type {
		int node, weight;
		public Type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine()); // 시작정점
		int INF  = Integer.MAX_VALUE;
		stPath = new int[V+1];
		Arrays.fill(stPath, INF);
		
		ArrayList<Type>[] graph = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<Type>();
		}
		
		int u, v, w; // u -> w
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Type(v, w));
		}
		
		stPath[K] = 0;
		dijkstra(graph, K);
		
		for(int i=1; i<=V; i++) {
			if(stPath[i] == INF) bw.write("INF\n");
			else bw.write(stPath[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	private static void dijkstra(ArrayList<Type>[] graph, int start) {
		PriorityQueue<Type> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
		// boolean[] isVisited = new boolean[stPath.length];
		pq.offer(new Type(start, 0));
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			
			// if(isVisited[t.node]) continue;
			// isVisited[t.node] = true;
			
			for(Type x : graph[t.node]) {
				int path = t.weight + x.weight;
				if(path < stPath[x.node]) {
					pq.offer(new Type(x.node, path));
					stPath[x.node] = path;
				}
			}
		}
	}
}
