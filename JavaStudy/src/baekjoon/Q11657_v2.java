// 타임머신 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q11657_v2 {
	static int[] stPath;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Type>[] graph = new ArrayList[N+1];
		int INF = 6000 * 10000;
		stPath = new int[N+1];
		Arrays.fill(stPath, INF);
		stPath[1] = 0;
		
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int a, b, c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Type(b, c));
		}
		
		if(bellmanFord(graph, 1, N)) {
			System.out.println(-1);
			return;
		};
		
		for(int i=2; i<=N; i++) {
			if(stPath[i] == INF) bw.write(-1 + "\n");
			else bw.write(stPath[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	public static boolean bellmanFord(ArrayList<Type>[] graph, int start, int N) {
		boolean chk = false;
		for(int i=0; i<N; i++) {
			for(int j=1; j<=N; j++) {
				for(Type t: graph[j]) {
					int now = stPath[t.node];
				
					stPath[t.node] = Math.min(stPath[j] + t.weight, stPath[t.node]); 
					
					if(i==N-1) {
						if(stPath[t.node] != now) chk = true;
					}
				}
			}
		}
		
		return chk;
	}
	static class Type {
		int node, weight; 
		
		Type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
}
