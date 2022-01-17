// 효율적인 해킹 (백준 1325번)

/* [ 알고리즘 ]
 * 
 * 1. A가 B를 신뢰할 경우 (B->A로 가는 단방향 그래프를 만든다)
 * 2. DFS를 이용해 시작 지점에서 갈 수 있는 최대 노드를 저장한다.
 * 3. 모든 지점에서 2번과 같이 돌린다.
 * 4. 모든 지점 중 값이 가장 큰 노드를 찾고 동일한 값인 노드가 여러개 있을 경우 오름차순으로 출력한다.
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1325 {
	static ArrayList<Integer>[] list;
	static int[] count;
	static boolean[] isVisited;
	static int cnt;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 노드 갯수
		int M = Integer.parseInt(st.nextToken()); // 간선 갯수
		
		list = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// A가 B를 신뢰한다.
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list[B].add(A);
		}
		
		count = new int[N+1];
		for(int i=1; i<=N; i++) {
			isVisited = new boolean[N+1];
			isVisited[i] = true;
			dfs(N, M, i, i);
		}
		
		int Max = 0;
		for(int i=1; i<=N; i++) {
			if(Max < count[i]) Max = count[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(Max==count[i]) sb.append(i+" ");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int N, int M, int V, int start) {
		if(V > N) return;
		
		for(Integer i : list[V]) {
			if(!isVisited[i]) {
				dfs(N, M, i, start);
				isVisited[V] = true;
				count[start]++;
			}
		}
	}
}
