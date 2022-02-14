// 도시 건설 (백준 21924번)

// 크루스칼 알고리즘 - MST : 사이클확인 (Union-Find 이용)

/* [ 추가 알고리즘 ]
 * 1. 모든 노드를 연결한 간선의 개수 = 노드의 개수 - 1 인지 확인
 * 2. 전체 비용에서 - 최소 비용 = 절약하는 비용
 * 3. 조건이 int형을 넘어갈 수 있으므로 long 타입으로 선언해줘야 정답!
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
			return this.weight - t.weight; // 가중치를 기준으로 오름차순
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		Queue<Type> pq = new PriorityQueue<Type>();
		parents = new int[N+1];
		long total = 0; // 전체 비용
		long minTotal = 0; // 사용하는 최소 비용
		int count = 0; // 연결된 간선의 개수를 카운트 (최소 비용일 때)
		
		for(int i=0; i<M; i++ ) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Type(from, to, weight));
			total += weight;
		}
		
		// 부모를 자신의 인덱스 값으로 초기화
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
	
	// 부모를 찾음
	public static int findParent(int x) {
		if(parents[x] == x) {
			return x;
		}
		return findParent(parents[x]);
	}
	
	// 부모를 합쳐줌 (더 작은 값으로)
	public static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}
	
	// 사이클 확인
	public static boolean isCycle(int x, int y) {
		if(x==y) return true;
		return false;
	}
}
