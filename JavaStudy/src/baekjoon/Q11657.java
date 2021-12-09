// 타임머신 (백준 11657번)

/* [ 알고리즘 ]
 * 
 * - 벨만-포드 알고리즘 이용 ( 음수 간선 존재 )
 * - 음수 간선의 순환 확인 위해 V 단계에서 최단 거리 갱신 되는지 확인 ( 갱신되면 순환 O, 갱신되지 않으면 순환 X ) 
 */

// 주의
// 정점의 개수가 500개, 간선의 개수가 6000개, 최소 가중치가 -10000이라면 충분히 underflow가 발생할 수 있음
// (-500 * 6000 * 10000 = -3 * 10^10, Integer.MIN_VALUE = 약 -2*10^9)

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
		int V = Integer.parseInt(st.nextToken()); // 도시(정점)의 개수
		int E = Integer.parseInt(st.nextToken()); // 버스 노선(간선)의 개수
		
		LinkedList<type>[] graph = new LinkedList[V+1];
		for(int i=0; i<=V; i++) {
			graph[i] = new LinkedList<type>();
		}
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 시작도시
			int B = Integer.parseInt(st.nextToken()); // 도착도시
			int C = Integer.parseInt(st.nextToken()); // 버스를 타고 이동하는데 걸리는 시간
			
			graph[A].add(new type(B, C)); // 방향 그래프
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
		long[] distance = new long[V+1];  // 출력초과 때문에 long 타입으로 바꿈 (음수 간선 순환에 의한 언더플로우 발생할 가능성 있음)
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;  // start->start로 가는 비용 0으로 초기화
		boolean check = false;
		
		for(int i=0; i<V; i++) {
			for(int j=1; j<=V; j++) {
				for(type t: graph[j]) {
					int from = j;
					int to = t.node;
					long weight = t.weight;
					
					if(distance[from] == Integer.MAX_VALUE) continue; // 비교문에서 자료형의 범위를 초과할 수 있음
					
					if(distance[to] > distance[from] + weight) {
						distance[to] = distance[from] + weight;
						
						if(i == V-1) check = true; // 음수 간선 순환 확인
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
