package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q3584 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer>[] adjList = new LinkedList[N+1];
			int[] parent = new int[N+1];
			int[] depth = new int[N+1];
			boolean[] vertex = new boolean[N+1];

			for (int j = 1; j <= N; j++) {
				adjList[j] = new LinkedList<Integer>();
			}

			for(int j = 0; j < N-1; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				vertex[b] = true;

				adjList[a].add(b);
				adjList[b].add(a);
			}

			// 루트 노드 찾기 
			int root = 0;
			for(int j = 1; j <= N; j++) {
				if(vertex[j] == false) {
					root = j;
				}
			}
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());

			int v1 = Integer.parseInt(st1.nextToken());
			int v2 = Integer.parseInt(st1.nextToken());
			
			// DFS로 트리를 순회하며 각 노드의 깊이와 부모노드 배열에 저장 
			dfs(adjList, depth, parent, root, 0, -1);
			
			// 가장 가까운 공통 조상 구하기 
			lca(depth, parent, v1, v2);
		}
	}


	public static void dfs(LinkedList<Integer>[] adjList, int[] depth, int[] parent, int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;

		for(int next : adjList[cur]) {
			if(next != p) {
				dfs(adjList, depth, parent, next, d+1, cur);
			}
		}
	}
	
	public static void lca(int[] depth, int[] parent, int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
	
		// 두 노드의 깊이 같게 맞추기 
		while(depthA > depthB) {
			a = parent[a];
			depthA--;
		}
		
		while(depthB > depthA) {
			b = parent[b];
			depthB--;
		}
		
		// 같은 레벨(깊이)에서 위로 올라가며 공통 부모노드 찾기 
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		System.out.println(a);
	}
}